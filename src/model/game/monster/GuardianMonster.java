package model.game.monster;

import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GuardianMonster extends Monster {

    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);

        im = ImageIO.read(new File("resources/images/guardianmonster.png"));
    }

    @Override
    public void move(Maze maze) {
        int x=0,y=0;

        position.x += x;
        position.y += y;
    }
}
