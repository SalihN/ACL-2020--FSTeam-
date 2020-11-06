package model.game;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Alexis Richer, Goetz Alexandre
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

    /**
     * check collision between this object and another one
     * @param objectToCheck
     * @return
     */
    public boolean checkCollision(GameObject objectToCheck){
        boolean right,top,bottom,left;

        bottom = this.position.y + this.height/2  < objectToCheck.position.y - objectToCheck.height/2  ;
        top = this.position.y - this.height/2  > objectToCheck.position.y + objectToCheck.height/2;
        right = this.position.x + this.width/2  < objectToCheck.position.x - objectToCheck.width/2;
        left = this.position.x - this.width/2 > objectToCheck.position.x + objectToCheck.width/2;

        if(top || right || left || bottom ){
            return false;
        }
        else{
           return true;
        }
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
