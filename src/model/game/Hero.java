package model.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Hero {
    private Stats stats;
    private Point position;
    private int width;
    private int height;
    private BufferedImage im;

    public Hero() throws IOException {
        stats = new Stats();
        position = new Point(0,0);
        width = 20;
        height = 20;

        im = ImageIO.read(new File("resources/images/hero.png"));
    }

    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x,position.y,width,height,null);
    }

    public void move(int x, int y){
        position.x += x;
        position.y += y;
    }

    public void heal (int heal){
        stats.heal(heal);
    }

    public void hit (int damage){
        stats.hit(damage);
    }

    /////////////////
    //Getter&Setter//
    /////////////////

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

    public int getHP(){
        return stats.getHP();
    }
}
