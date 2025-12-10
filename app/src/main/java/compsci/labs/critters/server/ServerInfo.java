package compsci.labs.critters.server;

import compsci.labs.critters.shared.Critter;
import compsci.labs.critters.shared.CritterInfo;

class ServerInfo implements CritterInfo {
        private Critter.Neighbor[] neighbors;
        private Critter.Direction direction;
        private boolean[] neighborThreats;

        public ServerInfo(Critter.Neighbor[] neighbors, Critter.Direction d,
                    boolean[] neighborThreats) {
            this.neighbors = neighbors;
            this.direction = d;
            this.neighborThreats = neighborThreats;
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

        public String serialize() {
            // serialize EVERYTHING
            StringBuilder sb = new StringBuilder();
            sb.append(neighbors[0].name());
            sb.append(",");
            sb.append(neighbors[1].name());
            sb.append(",");
            sb.append(neighbors[2].name());
            sb.append(",");
            sb.append(neighbors[3].name());
            sb.append(";");
            sb.append(direction.name());
            sb.append(";");
            for (boolean b : neighborThreats) {
                sb.append(b ? '1' : '0');
                sb.append(";");
            }
            return sb.toString();
        }

}
