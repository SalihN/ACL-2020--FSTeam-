package model.game.monster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) {
        super(point, width, height);
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.yellow);
        crayon.fillOval(position.x, position.y, width, height);
    }
}
