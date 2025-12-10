package compsci.labs.critters.client;

import compsci.labs.critters.shared.CritterInfo;
import compsci.labs.critters.shared.Animal;

import java.awt.*;

public class Bear extends Animal {
    private final boolean isPolar;
    private boolean left = true;
    public Bear(boolean polar) {
        this.isPolar = polar;
    }
    @Override
    public Action getMove(CritterInfo info) {
        return switch (info.getFront()) {
            case OTHER -> super.getMove(info);

            case EMPTY -> Action.HOP;
            default -> Action.LEFT;
        };
    }
    @Override
    public Color getColor() {
        return isPolar ? Color.WHITE : Color.BLACK;
    }

    @Override
    public String toString() {
        left = !left;
        return left ? "\\" : "/";
    }
}
