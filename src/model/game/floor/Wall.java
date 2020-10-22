package model.game.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Wall extends Floor {
    public Wall(Point p, int w, int h) {
        super(p, w, h);
    }

    @Override
    public void draw(BufferedImage im) throws IOException {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        im = ImageIO.read(new File("resources/images/brick.png"));
        crayon.drawImage(im,position.x,position.y,width,height,null);
    }
}
