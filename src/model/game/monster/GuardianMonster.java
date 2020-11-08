package model.game.monster;

import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre
 * @version 1.0
 *
 * Monstre qui garde le trésor
 */

public class GuardianMonster extends Monster {
    private Point initialPosition;
    private boolean movingClockwise;
    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        initialPosition = new Point(point);
        movingClockwise = true;
        im = ImageIO.read(new File("resources/images/guardianmonster.png"));
    }

    @Override
    public void move(Maze maze) {
        int x=0,y=0;

        if(position.x <= initialPosition.x +  2 * getWidth() && position.y <= initialPosition.y)
            x += getStats().getSpeed();
        if(position.x >= initialPosition.x  && position.y >= initialPosition.y + getHeight() * 2)
            x -= getStats().getSpeed();
        if(position.x >= initialPosition.x + getWidth() * 2 && position.y <= initialPosition.y + getHeight() * 2)
            y += getStats().getSpeed();
        if(position.x <= initialPosition.x && position.y >= initialPosition.y)
            y -= getStats().getSpeed();

        position.x += x;
        position.y += y;
    }
}
