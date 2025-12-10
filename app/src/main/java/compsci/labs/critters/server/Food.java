// PREMADE
package compsci.labs.critters.server;// This defines a simple class of critters that sit around waiting to be
// taken over by other critters.

import compsci.labs.critters.shared.Critter;
import compsci.labs.critters.shared.CritterInfo;

import java.awt.*;

public class Food extends Critter {
    public Action getMove(CritterInfo info) {
        return Action.INFECT;
    }

    public Color getColor() {
        return Color.GREEN;
    }

    public String toString() {
        return "F";
    }
}
