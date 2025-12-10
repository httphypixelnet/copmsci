package compsci.labs.critters.client;

import compsci.labs.critters.shared.Critter;
import java.util.UUID;
import compsci.labs.critters.shared.CritterInfo;

public class ClientInfo implements CritterInfo {

    private final Critter.Neighbor[] neighbors;
    private final Critter.Direction direction;
    private final boolean[] neighborThreats;
    private final UUID ownerId;
    public ClientInfo(Critter.Neighbor[] neighbors, Critter.Direction d, boolean[] neighborThreats, UUID ownerId) {
        this.neighbors = neighbors;
        this.direction = d;
        this.neighborThreats = neighborThreats;
        this.ownerId = ownerId;
    }

    public Critter.Neighbor getFront() {
        return neighbors[0];
    }

    public Critter.Neighbor getBack() {
        return neighbors[2];
    }

    public Critter.Neighbor getLeft() {
        return neighbors[3];
    }

    public Critter.Neighbor getRight() {
        return neighbors[1];
    }

    public Critter.Direction getDirection() {
        return direction;
    }

    public UUID getOwnerId() {
        return this.ownerId;
    }

    public boolean frontThreat() {
        return neighborThreats[0];
    }

    public boolean backThreat() {
        return neighborThreats[2];
    }

    public boolean leftThreat() {
        return neighborThreats[3];
    }

    public boolean rightThreat() {
        return neighborThreats[1];
    }

    public static CritterInfo deserialize(String s) {
        // reverse

        String[] parts = s.split(";");

        String neighborNames = parts[0];
        String directionName = parts[1];
        String[] neighborThreats = parts[2].split(",");
        UUID ownerId = UUID.fromString(parts[3]);
        Critter.Direction direction = Critter.Direction.valueOf(directionName);
        Critter.Neighbor[] neighbors = new Critter.Neighbor[4];

        for (int i = 0; i < 4; i++) {
            neighbors[i] = Critter.Neighbor.valueOf(neighborNames.split(",")[i]);
        }
        boolean[] neighborThreatsBool = new boolean[4];
        for (int i = 0; i < neighborThreats.length; i++) {
            neighborThreatsBool[i] = neighborThreats[i].charAt(0) == '1';
        }
        return new ClientInfo(neighbors, direction, neighborThreatsBool, ownerId);
    }
}
