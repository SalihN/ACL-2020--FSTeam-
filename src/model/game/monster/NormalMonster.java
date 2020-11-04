package model.game.monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Emanuel Gady
 * @version 1.0.0
 *
 * Monstre de base
 */
public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) throws IOException{
        super(point, width, height);
        speed = 3;
        im = ImageIO.read(new File("resources/images/normalmonster.png"));
    }
}
