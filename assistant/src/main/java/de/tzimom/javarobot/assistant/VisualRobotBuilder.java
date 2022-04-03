package de.tzimom.javarobot.assistant;

import de.tzimom.javarobot.config.BucketConfig;
import de.tzimom.javarobot.config.ConveyorBeltConfig;
import de.tzimom.javarobot.controllers.BucketController;
import de.tzimom.javarobot.controllers.ConveyorBeltController;
import de.tzimom.javarobot.controllers.RobotController;
import de.tzimom.javarobot.entities.Bucket;
import de.tzimom.javarobot.entities.ConveyorBelt;
import de.tzimom.javarobot.entities.Robot;
import de.tzimom.javarobot.graphics.RobotView;
import de.tzimom.javarobot.graphics.animated.AnimatedRobot;
import de.tzimom.javarobot.graphics.config.AnimatedRobotConfig;
import de.tzimom.javarobot.graphics.rendering.View;
import de.tzimom.javarobot.graphics.rendering.animation.Animator;
import de.tzimom.javarobot.graphics.rendering.animation.EasingCurve;
import de.tzimom.javarobot.graphics.rendering.animation.TrackingAnimator;
import de.tzimom.javarobot.graphics.rendering.config.DisplayConfig;
import de.tzimom.javarobot.repositories.ball.BallRepository;
import de.tzimom.javarobot.repositories.bucket.BucketRepository;
import de.tzimom.javarobot.repositories.bucket.ConcreteBucketRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConcreteConveyorBeltRepository;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;
import de.tzimom.javarobot.services.BucketService;
import de.tzimom.javarobot.services.ConveyorBeltService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class VisualRobotBuilder {
    private final BallRepository ballRepository;

    private final Collection<ConveyorBeltConfig> conveyorBelts = new ArrayList<>();
    private final Collection<BucketConfig> buckets = new ArrayList<>();

    private DisplayConfig displayConfig = new DisplayConfig(800, 500, "", DisplayConfig.CloseOperation.EXIT);
    private AnimatedRobotConfig animatedRobotConfig = new AnimatedRobotConfig(60, 250, EasingCurve.EASE_IN_OUT_QUAD);

    public VisualRobotBuilder(BallRepository ballRepository) {
        this.ballRepository = ballRepository;
    }

    public VisualRobotBuilder addConveyorBelts(ConveyorBeltConfig... configs) {
        conveyorBelts.addAll(Arrays.asList(configs));
        return this;
    }

    public VisualRobotBuilder addBuckets(BucketConfig... configs) {
        buckets.addAll(Arrays.asList(configs));
        return this;
    }

    public VisualRobotBuilder setDisplayConfig(DisplayConfig displayConfig) {
        this.displayConfig = displayConfig;
        return this;
    }

    public VisualRobotBuilder setAnimatedRobotConfig(AnimatedRobotConfig animatedRobotConfig) {
        this.animatedRobotConfig = animatedRobotConfig;
        return this;
    }

    public Robot build() {
        RobotRepositoryCollection repositories = createRepositories();
        RobotServiceCollection services = createServices(repositories);

        Robot robot = createRobot(services);
        AnimatedRobot animatedRobot = new AnimatedRobot(robot, createAnimator(robot));
        View robotView = createRobotView(repositories, animatedRobot);

        robotView.startRendering(animatedRobotConfig.fps());

        return robot;
    }

    private RobotRepositoryCollection createRepositories() {
        Collection<ConveyorBelt> conveyorBelts = this.conveyorBelts.stream()
                .map(config -> new ConveyorBeltController(config, ballRepository))
                .collect(Collectors.toSet());
        Collection<Bucket> buckets = this.buckets.stream()
                .map(BucketController::new)
                .collect(Collectors.toSet());

        ConveyorBeltRepository conveyorBeltRepository = new ConcreteConveyorBeltRepository(conveyorBelts);
        BucketRepository bucketRepository = new ConcreteBucketRepository(buckets);

        return new RobotRepositoryCollection(ballRepository, conveyorBeltRepository, bucketRepository);
    }

    private RobotServiceCollection createServices(RobotRepositoryCollection repositories) {
        ConveyorBeltService conveyorBeltService = new ConveyorBeltService(repositories.getConveyorBeltRepository());
        BucketService bucketService = new BucketService(repositories.getBucketRepository());

        return new RobotServiceCollection(conveyorBeltService, bucketService);
    }

    private Robot createRobot(RobotServiceCollection services) {
        return new RobotController(services.getConveyorBeltService(), services.getBucketService());
    }

    private Animator createAnimator(Robot robot) {
        return new TrackingAnimator(animatedRobotConfig.animationDuration(), animatedRobotConfig.curve(), () -> (double) robot.getCurrentAngle());
    }

    private View createRobotView(RobotRepositoryCollection repositories, Robot robot) {
        return new RobotView(displayConfig, repositories.getConveyorBeltRepository(), repositories.getBucketRepository(), robot);
    }
}
