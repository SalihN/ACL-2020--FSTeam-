package model.game.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Emanuel Gady, Alexis Richer
 * @version 1.0.1
 *
 * Trésor du labyrinthe
 */
public class TreasureFloor extends Floor {

    public TreasureFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/treasurefloor.png"));
    }

    @Override
    public boolean isTreasorFloor() {
        return true;
    }
}
