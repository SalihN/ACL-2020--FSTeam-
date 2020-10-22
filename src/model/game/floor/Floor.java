package model.game.floor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Floor {
    protected Point position;
    protected int width;
    protected int height;

    public Floor(Point p, int w, int h){
        position = p;
        width = w;
        height = h;
    }

    public abstract void draw(BufferedImage im) throws IOException;

    public Point getPosition() {
        return position;
    }
}
