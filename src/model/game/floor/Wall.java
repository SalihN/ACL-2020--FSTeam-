package model.game.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Wall extends Floor {
    private BufferedImage im;
    public Wall(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/brick.png"));
    }

    @Override
    public void draw(BufferedImage im) throws IOException {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x,position.y,width,height,null);
    }
}
