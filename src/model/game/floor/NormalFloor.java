package model.game.floor;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalFloor extends Floor {
    public NormalFloor(Point p, int w, int h) {
        super(p, w, h);
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.green);
        crayon.fillRect(position.x, position.y, width, height);
    }
}
