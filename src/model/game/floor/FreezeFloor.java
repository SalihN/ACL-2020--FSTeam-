package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexis Richer,
 * @version 1.0.0
 *
 * Case magique du labyrinthe qui freeze les monstres
 */

public class FreezeFloor extends ActivateFloor {

    private boolean isActivate;

    public FreezeFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/freezefloor.png"));
    }

    @Override
    public void activate(Hero hero, Maze maze) throws IOException {
        if(!isActivate) {
            isActivate = true;
            desactivate();
            maze.freezeMonsters(5);
        }
    }

    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }
}
