<h1 align="center">Java Robot</h1>

> A simple library to help students with playfully learning object-oriented programming in java.

## ✨ Setup

*This library uses [java 1.8](https://www.oracle.com/de/java/technologies/javase/javase8-archive-downloads.html) so make sure you have that installed*

To start using this library you have to configure a few things:

### RenderColors

The library uses the interface [`RenderColor`](./src/main/java/de/tzimom/javarobot/entities/RenderColor.java):

```java
public interface RenderColor {
    Color getValue();
    boolean isBright();
}
```

It is used for the graphics engine to render balls and buckets in specific colors

There is no standard implementation, however, the recommended way is to use an enum:

```java
public enum RenderColorImpl implements RenderColor {
    RED(Color.RED, true),
    BLUE(Color.BLUE, false),
    GREEN(Color.GREEN, true);

    private final Color value;
    private final boolean bright;

    RenderColorImpl(Color value, boolean bright) {
        this.value = value;
        this.bright = bright;
    }

    public static Collection<RenderColor> getAllColors() {
        return Set.of(values());
    }

    public Color getValue() {
        return value;
    }

    public boolean isBright() {
        return bright;
    }
}
```

This way you have full control over which colors are allowed, and it is easier to implement algorithms that sort by color etc

### [BallRepository](./src/main/java/de/tzimom/javarobot/repositories/ball/BallRepository.java)

The repository the conveyor belts use to generate new balls

> The library provides a default implementation: [`RandomBallRepository`](./src/main/java/de/tzimom/javarobot/repositories/ball/RandomBallRepository.java)

### Creating the robot

Use the class [`VisualRobotBuilder`](./assistant/src/main/java/de/tzimom/javarobot/assistant/VisualRobotBuilder.java) as a builder to create the robot

```java
public class ExampleRobot {
    public static void main(String[] args) {
        BallRepository ballRepository = new RandomBallRepository(Arrays.asList(RenderColorImpl.values()));
        Robot robot = new VisualRobotBuilder(ballRepository)
                .addConveyorBelts(new ConveyorBeltConfig(10, 90))
                .addBuckets(new BucketConfig(0, 8, RenderColorImpl.RED))
                .build();
    }
}
```

Additionally, you can configure
- the display with `VisualRobotBuilder.setDisplayConfig(DisplayConfig)`
- the robot animation with `VisualRobotBuilder.setAnimatedRobotConfig(AnimatedRobotConfig)`

## 🚀 Usage

Once done with the setup, you can play around with the robot

This is the robot interface:

```java
public interface Robot {
    Optional<Ball> grabBall() throws IllegalRobotStateException;
    Optional<Bucket> dropBall() throws IllegalRobotStateException;

    void turnTo(float angle);
    
    float getCurrentAngle();
    Optional<Ball> getCurrentBall();
}
```

As you can see, the main functionality consists of turning the robot, grabbing and dropping balls from conveyor belts into buckets and turning between them.
