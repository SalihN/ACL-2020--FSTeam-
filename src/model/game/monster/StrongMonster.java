package model.game.monster;

import model.game.Stats;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre
 * @version 1.0
 *
 * Un normal monster plus rapide et avec plus de point de vie
 */

public class StrongMonster extends Monster {

    public StrongMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        stats = new Stats(3,5);
        im = ImageIO.read(new File("resources/images/strongmonster.png"));

    }

}
