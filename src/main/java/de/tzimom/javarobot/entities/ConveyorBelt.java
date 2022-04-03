package de.tzimom.javarobot.entities;

import de.tzimom.javarobot.config.ConveyorBeltConfig;

import java.util.Queue;

public interface ConveyorBelt {
    Ball takeBall();
    Queue<Ball> getBallQueue();

    ConveyorBeltConfig getConfig();
}
