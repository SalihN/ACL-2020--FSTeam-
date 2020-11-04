package model.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Alexis Richer
 * @version 1.0.0
 *
 * Représente un objet ayant des collisions physiques dans le jeu
 */

public abstract class SolidObject extends GameObject {
    protected Stats stats;

    public void draw(BufferedImage im){
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(this.im,position.x-(width/2),position.y-(height/2),width,height,null);
    }
}
