package model.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Emanuel Gady,
 * @version 1.0.0
 *
 * Heros present dans le labyrinthe
 */
public class Hero {
    private Stats stats;
    private Point position;
    private int width;
    private int height;
    private BufferedImage im;


    public Hero() throws IOException {
        stats = new Stats(5,5);
        position = new Point(0,0);
        width = 20;
        height = 20;

        im = ImageIO.read(new File("resources/images/hero.png"));
    }

    /**
     * Affiche l'image du Heros
     * @param im
     */
    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x-(width/2),position.y-(height/2),width,height,null);
    }

    /**
     * Permet de deplacer le Heros
     * @param x
     * @param y
     */
    public void move(int x, int y){
        position.x += x;
        position.y += y;
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

    public Stats getStats() {
        return stats;
    }
}
