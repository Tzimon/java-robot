package de.tzimom.javarobot.repositories.conveyorbelt;

import de.tzimom.javarobot.entities.ConveyorBelt;

import java.util.Collection;

public interface ConveyorBeltRepository {
    Collection<ConveyorBelt> getAllConveyorBelts();
}
