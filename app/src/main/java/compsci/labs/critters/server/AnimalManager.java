package compsci.labs.critters.server;

import compsci.labs.critters.shared.Animal;

import java.util.*;
import java.util.function.Predicate;

class AnimalManager {
    private final Map<UUID, Animal> animals;
    public AnimalManager() {
        this.animals = new HashMap<>();
    }
    public UUID add(Animal a) {
        this.animals.put(a.id, a);
        GlobalState.startManaging(a, this);
        return a.id;
    }
    public Animal get(UUID id) {
        return this.animals.get(id);
    }
    public Set<UUID> getAnimals() {
        return this.animals.keySet();
    }
    public Map<UUID, Animal> remove(UUID id) {
        this.animals.remove(id);
        return this.animals;
    }
    public int size() {
        return this.animals.size();
    }
    public boolean isEmpty() {
        return this.animals.isEmpty();
    }
    public void clear() {
        this.animals.clear();
    }
    public AnimalManager copy() {
        AnimalManager copy = new AnimalManager();
        copy.animals.putAll(this.animals);
        return copy;
    }
    public void update(AnimalManager other) {
        this.animals.putAll(other.animals);
    }
    public Map<UUID, Animal> removeAll(Set<UUID> ids) {
        Map<UUID, Animal> removed = new HashMap<>();
        for (UUID id : ids) {
            removed.put(id, this.animals.remove(id));
        }
        return removed;
    }
    public AnimalManager filter(Predicate<Animal> filter) {
        AnimalManager filtered = new AnimalManager();
        for (Animal animal : animals.values()) {
            if (filter.test(animal)) {
                filtered.animals.put(animal.id, animal);
            }
        }
        return filtered;
    }
}
