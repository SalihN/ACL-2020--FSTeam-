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
    private final Point initialPosition;
    private boolean movingClockwise;
    public GuardianMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        initialPosition = new Point(point);
        getStats().setSpeed(4);
        movingClockwise = true;
        im = ImageIO.read(new File("resources/images/guardianmonster.png"));
    }

    @Override
    public void move(Maze maze) {
        int x=0,y=0;

        if(movingClockwise) {
            // en haut à gauche, vers la droite
            if (position.x <= initialPosition.x + 2 * maze.tileWidth && position.y <= initialPosition.y)
                x += getStats().getSpeed();
            // en bas à droite, vers la gauche
            if (position.x >= initialPosition.x && position.y >= initialPosition.y + maze.tileHeight * 2)
                x -= getStats().getSpeed();
            //en bas à gauche, vers le haut
            if (position.x >= initialPosition.x + maze.tileWidth * 2 && position.y <= initialPosition.y + maze.tileHeight * 2)
                y += getStats().getSpeed();
            //en bas à droite, vers la gauche
            if (position.x <= initialPosition.x && position.y >= initialPosition.y)
                y -= getStats().getSpeed();
        }
        else{
            // en bas à gauche, vers la droite
            if (position.y >= initialPosition.y + 2 * maze.tileHeight && position.x <= initialPosition.x + 2 * maze.tileWidth)
                x += getStats().getSpeed();
            // en haut à droite, vers la gauche
            if (position.y <= initialPosition.y  && position.x >= initialPosition.x)
                x -= getStats().getSpeed();
            // en haut à gauche ,vers le bas
            if (position.x <= initialPosition.x && position.y <= initialPosition.y + 2 * maze.tileHeight)
                y += getStats().getSpeed();
            // en bas à droite, vers le haut
            if (position.x >= initialPosition.x + 2 * maze.tileWidth && position.y >= initialPosition.y)
                y -= getStats().getSpeed();
        }
        // changement d'orientation si on rencontre un mur
        if(!checkWall(x,y,maze)){
            movingClockwise = !movingClockwise;
            x = -x;
            y = -y;
        }

        position.x += x;
        position.y += y;
    }
}
