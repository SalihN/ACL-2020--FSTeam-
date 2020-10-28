package model.game.floor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Floor {
    protected Point position;
    protected int width;
    protected int height;
    protected BufferedImage im;

    public Floor(Point p, int w, int h){
        position = p;
        width = w;
        height = h;
    }

    public void draw(BufferedImage im) throws IOException{
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x,position.y,width,height,null);
    }

    public Point getPosition() {
        return position;
    }

    public boolean isTreasorFloor(){
        return false;
    }

    public boolean isMagicalFloor() {
        return false;
    }
}
