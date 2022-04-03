package de.tzimom.javarobot.services;

import de.tzimom.javarobot.entities.Ball;
import de.tzimom.javarobot.entities.ConveyorBelt;
import de.tzimom.javarobot.repositories.conveyorbelt.ConveyorBeltRepository;

import java.util.Optional;

public class ConveyorBeltService {
    private final ConveyorBeltRepository conveyorBeltRepository;

    public ConveyorBeltService(ConveyorBeltRepository conveyorBeltRepository) {
        this.conveyorBeltRepository = conveyorBeltRepository;
    }

    public Optional<Ball> provideBall(float angle) {
        return conveyorBeltRepository.getAllConveyorBelts().stream()
                .filter(conveyorBelt -> angle == conveyorBelt.getConfig().relativeAngle())
                .findFirst()
                .map(ConveyorBelt::takeBall);
    }
}
