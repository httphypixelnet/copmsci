package compsci.labs.critters.server;

import compsci.labs.critters.shared.Animal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GlobalState {
    private static final HashMap<UUID, AnimalManager> managerMap = new HashMap<>();
    private static final List<AnimalManager> availableManagers = new ArrayList<>();
    public static AnimalManager getManager(UUID animalId) {
        return managerMap.get(animalId);
    }
    public static AnimalManager assignManager(Animal animal) {
        if (animal == null) return null;
        // If there are no managers yet, create one
        if (availableManagers.isEmpty()) {
            AnimalManager m = createManager();
            managerMap.put(animal.id, m);
            m.add(animal);
            return m;
        }
        Class<?> type = animal.getClass();
        // Find manager with the minimum count of this animal type.
        AnimalManager best = availableManagers.stream()
                .min(Comparator.comparingInt(m -> m.filter(a -> a.getClass() == type).size()))
                .orElseGet(GlobalState::createManager);
        // register mapping and add animal to manager
        managerMap.put(animal.id, best);
        best.add(animal);
        return best;
    }
    public static AnimalManager createManager() {
        AnimalManager m = new AnimalManager();
        availableManagers.add(m);
        return m;
    }
    public static AnimalManager getOrAssignManager(Animal animal) {
        AnimalManager manager = managerMap.get(animal.id);
        if (manager == null) {
            manager = assignManager(animal);
        }
        return manager;
    }

    public static void startManaging(Animal animal, AnimalManager manager) {
        if (animal == null || manager == null) {
            return;
        }
        managerMap.put(animal.id, manager);
        if (!availableManagers.contains(manager)) {
            availableManagers.add(manager);
        }
    }
}