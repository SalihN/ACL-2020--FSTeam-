package model.game.monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) {
        super(point, width, height);
    }

    @Override
    public void draw(BufferedImage im) throws IOException {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        im = ImageIO.read(new File("resources/images/normalmonster.png"));
        crayon.drawImage(im,position.x,position.y,width,height,null);
    }
}
