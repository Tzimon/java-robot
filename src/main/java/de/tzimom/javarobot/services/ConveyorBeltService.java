package de.tzimom.javarobot.services;

import de.tzimom.javarobot.api.Ball;
import de.tzimom.javarobot.api.ConveyorBelt;

import java.util.List;
import java.util.Optional;

public class ConveyorBeltService {
    private final List<ConveyorBelt> conveyorBelts;

    public ConveyorBeltService(List<ConveyorBelt> conveyorBelts) {
        this.conveyorBelts = conveyorBelts;
    }

    public Optional<Ball> provideBall(float angle) {
        return conveyorBelts.stream()
                .filter(conveyorBelt -> angle == conveyorBelt.getConfig().relativeAngle())
                .findFirst()
                .map(ConveyorBelt::takeBall);
    }
}
