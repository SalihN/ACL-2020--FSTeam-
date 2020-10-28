package model.game.monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) throws IOException{
        super(point, width, height);
        speed = 3;
        im = ImageIO.read(new File("resources/images/normalmonster.png"));

    }

    @Override
    public void draw(BufferedImage im) throws IOException {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x-(width/2),position.y-(height/2),width,height,null);
    }


}
