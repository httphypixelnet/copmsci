package compsci.labs.critters.shared;

import java.util.UUID;

public class Animal extends Critter {
    public UUID id = UUID.randomUUID();
    public Animal() {
        super();
    }
    // must be final for AnimalManager to work properly
    @Override
    public final int hashCode() {
        return id.hashCode();
    }
}
