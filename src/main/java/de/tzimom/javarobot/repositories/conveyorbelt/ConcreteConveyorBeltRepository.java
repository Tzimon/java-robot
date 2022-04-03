package de.tzimom.javarobot.repositories.conveyorbelt;

import de.tzimom.javarobot.entities.ConveyorBelt;

import java.util.Collection;

public class ConcreteConveyorBeltRepository implements ConveyorBeltRepository {
    private final Collection<ConveyorBelt> conveyorBelts;

    public ConcreteConveyorBeltRepository(Collection<ConveyorBelt> conveyorBelts) {
        this.conveyorBelts = conveyorBelts;
    }

    public Collection<ConveyorBelt> getAllConveyorBelts() {
        return conveyorBelts;
    }
}
