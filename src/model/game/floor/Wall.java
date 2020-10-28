package model.game.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Emanuel Gady,
 * @version 1.0.0
 *
 * Mur du labyrinthe
 */
public class Wall extends Floor {

    public Wall(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/brick.png"));
    }

    @Override
    public boolean isWall() {
        return true;
    }
}
