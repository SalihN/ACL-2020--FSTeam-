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
 * Case ralentissant les monstres
 */

public class SlowFloor extends ActivateFloor {

    public SlowFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/slowfloor.png"));
    }

    @Override
    public void activate(Hero hero, Maze maze) throws IOException {
        if(!isActivate && !hero.isCatched()) {
            isActivate = true;
            desactivate();
            maze.slowMonsters(10, 2);
        }
    }

    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }
}
