package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

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
        }
    }

    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }
}
