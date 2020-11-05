package model.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Alexis Richer
 * @version 1.0.0
 *
 * Représente un objet graphique dans le jeu
 */

public abstract class GameObject {
    protected Point position;
    protected int width;
    protected int height;
    protected BufferedImage im;

    /**
     * Affiche l'image de la case du labyrinthe
     * @param im
     */
    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(
                this.im,
                position.x-(width/2),position.y-(height/2),
                width,height,
                null);
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
