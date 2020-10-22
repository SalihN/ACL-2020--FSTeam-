package model.game.monster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Monster {
    protected Point position;
    protected int width;
    protected int height;

    public Monster(Point point, int width, int height){
        this.position = point;
        this.width = width;
        this.height = height;
    }

    abstract public void draw(BufferedImage im) throws IOException;
}
