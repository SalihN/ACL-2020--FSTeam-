package model.game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Hero {
    private Stats stats;
    private Point position;
    private int width;
    private int height;

    public Hero(){
        stats = new Stats();
        position = new Point(0,0);
        width = 20;
        height = 20;
    }

    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.red);
        crayon.fillOval(position.x, position.y, width, height);
    }

    public void move(int x, int y){
        position.x += x;
        position.y += y;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
