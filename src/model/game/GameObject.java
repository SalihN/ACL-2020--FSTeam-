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
        crayon.drawImage(this.im,position.x,position.y,width,height,null);
    }
}
