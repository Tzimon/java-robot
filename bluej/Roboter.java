import de.tzimom.javarobot.config.BucketConfig;
import de.tzimom.javarobot.config.ConveyorBeltConfig;
import de.tzimom.javarobot.controllers.BucketController;
import de.tzimom.javarobot.controllers.ConveyorBeltController;
import de.tzimom.javarobot.controllers.RobotController;
import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.entities.ConveyorBelt;
import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.animated.AnimatedRobot;
import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.graphics.config.RobotViewConfig;
import de.tzimom.javarobot.graphics.rendering.animation.TrackingAnimator;
import de.tzimom.javarobot.graphics.rendering.animation.Curve;
import de.tzimom.javarobot.repositories.ball.BallRepository;
import de.tzimom.javarobot.repositories.ball.RandomBallRepository;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.bucket.ConcreteBucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConcreteConveyorBeltRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;
import de.tzimom.javarobot.services.BucketService;
import de.tzimom.javarobot.services.ConveyorBeltService;
import de.tzimom.javarobot.graphics.RobotView;
import java.util.Optional;

import de.tzimom.javarobot.exceptions.IllegalRobotStateException;

import java.awt.Dimension;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Roboter {
    private static final List<ConveyorBeltConfig> CONVEYOR_BELT_CONFIGS = List.of(
            new ConveyorBeltConfig(10, 90),
            new ConveyorBeltConfig(10, -45)
    );

    private static final List<BucketConfig> BUCKET_CONFIGS = List.of(
            new BucketConfig(-10, 8, RoboterFarbe.ROT),
            new BucketConfig(10, 8, RoboterFarbe.GELB)
    );

    private static final DisplayConfig DISPLAY_CONFIG = new DisplayConfig(
            new Dimension(900, 600),
            "Java Roboter",
            DisplayConfig.CloseOperation.EXIT
    );
    
    private static final int ACTION_DURATION = 250;

    private final AnimatedRobot robot;

    public Roboter() {
        var ballRepository = new RandomBallRepository(RoboterFarbe.alleFarben());
        var conveyorBeltRepository = createConveyorBeltRepository(ballRepository);
        var bucketRepository = createBucketRepository();

        robot = createRobot(conveyorBeltRepository, bucketRepository);

        createRobotView(conveyorBeltRepository, bucketRepository).startRendering(60);
    }

    private ConveyorBeltRepository createConveyorBeltRepository(BallRepository ballRepository) {
        Set<ConveyorBelt> conveyorBelts = CONVEYOR_BELT_CONFIGS.stream()
                .map(config -> new ConveyorBeltController(config, ballRepository))
                .collect(Collectors.toSet());

        return new ConcreteConveyorBeltRepository(conveyorBelts);
    }

    private BucketRepository createBucketRepository() {
        Set<Bucket> buckets = BUCKET_CONFIGS.stream()
                .map(BucketController::new)
                .collect(Collectors.toSet());

        return new ConcreteBucketRepository(buckets);
    }

    private AnimatedRobot createRobot(ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) {
        var conveyorBeltService = new ConveyorBeltService(conveyorBeltRepository);
        var bucketService = new BucketService(bucketRepository);

        var robot = new RobotController(conveyorBeltService, bucketService);
        var turnAnimator = new TrackingAnimator(250, Curve.EASE_IN_OUT_QUAD, () -> (double) robot.getCurrentAngle());

        return new AnimatedRobot(robot, turnAnimator);
    }

    private RobotView createRobotView(ConveyorBeltRepository conveyorBeltRepository, BucketRepository bucketRepository) {
        var robotViewConfig = new RobotViewConfig(DISPLAY_CONFIG, robot, conveyorBeltRepository, bucketRepository);
        return new RobotView(robotViewConfig);
    }
    
    private void pause() {
        try {
            Thread.sleep(ACTION_DURATION);
        } catch (InterruptedException ignored) {}
    }

    public void drehenUm(float winkel) {
        if (winkel == 0) return;
        
        drehenZu(winkel + robot.getCurrentAngle());
    }
    
    public void drehenZu(float winkel) {
        robot.turnTo(winkel);
        pause();
    }

    public Ball greifen() {
        try {
            Optional<Ball> grabbedBall = robot.grabBall();
            pause();
            return grabbedBall.orElse(null);
        } catch (IllegalRobotStateException ignored) {
            return null;
        }
    }

    public void loslassen() {
        try {
            robot.dropBall();
            pause();
        } catch (IllegalRobotStateException ignored) {}
    }

    public Ball ballGeben() {
        return robot.getCurrentBall().orElse(null);
    }

    public float winkelGeben() {
        return robot.getCurrentAngle();
    }
}
