package model.game.floor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class NormalFloor extends Floor {
    private BufferedImage im;

    public NormalFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x,position.y,width,height,null);
    }
}
