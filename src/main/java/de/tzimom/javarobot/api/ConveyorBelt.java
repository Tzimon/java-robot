package de.tzimom.javarobot.api;

import de.tzimom.javarobot.config.ConveyorBeltConfig;

public interface ConveyorBelt {
    Ball takeBall();

    ConveyorBeltConfig getConfig();
}
