package model.game.monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * @author Emanuel Gady, Goetz Alexandre
 * @version 1.1.0
 *
 * Monstre de base
 */
public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) throws IOException{
        super(point, width, height);
        im = ImageIO.read(new File("resources/images/normalmonster.png"));
    }

}
