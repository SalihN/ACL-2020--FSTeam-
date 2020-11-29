package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Richer Alexis
 * @version 1.0.0
 */
public class ScoreFloor extends ActivateFloor {
    public ScoreFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/scorefloor.png"));
    }

    @Override
    public void activate(Hero hero, Maze maze) throws IOException {
        if(!isActivate) {
            isActivate = true;
            desactivate();
            Hero.score += 5;
            Maze.sound("coin.wav");
        }
    }

    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }
}
