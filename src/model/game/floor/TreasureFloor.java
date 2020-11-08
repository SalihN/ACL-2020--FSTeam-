package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Emanuel Gady, Alexis Richer
 * @version 1.0.1
 *
 * Trésor du labyrinthe
 */
public class TreasureFloor extends ActivateFloor {

    public TreasureFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/treasurefloor.png"));
    }

    @Override
    public void activate(Hero hero, Maze maze) throws IOException {
        if(!isActivate) {
            isActivate = true;
            desactivate();
            maze.nextLevel();
        }
    }

    @Override
    public void desactivate()  {

    }

    @Override
    public boolean isTreasorFloor() {
        return true;
    }
}
