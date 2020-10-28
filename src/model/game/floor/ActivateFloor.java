package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import java.awt.*;
import java.io.IOException;

/**
 * @author Alexis Richer,
 * @version 1.0.0
 *
 * Case représentant les sols activables
 */

public abstract class ActivateFloor extends Floor {

    protected boolean isActivate;

    public ActivateFloor(Point p, int w, int h) {
        super(p, w, h);
        isActivate = false;
    }

    public abstract void activate(Hero hero, Maze maze) throws IOException;


    @Override
    public boolean isActivateFloor() {
        return true;
    }

    public boolean isActivate() {
        return isActivate;
    }

    public abstract void desactivate() throws IOException;
}
