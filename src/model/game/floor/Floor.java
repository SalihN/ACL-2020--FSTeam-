package model.game.floor;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Emanuel Gady,
 * @version 1.0.0
 *
 * Ensemble des cases du labyrinthe
 */
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

    /**
     * Affiche l'image de la case du labyrinthe
     * @param im
     */
    public void draw(BufferedImage im){
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
