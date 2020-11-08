package model.game.monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author
 * @version
 *
 * Monstre qui ramene le heros au debut du labyrinthe
 */
public class KidnapMonster extends Monster {

    public KidnapMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);

        im = ImageIO.read(new File("resources/images/kidnapmonster.png"));
    }

}
