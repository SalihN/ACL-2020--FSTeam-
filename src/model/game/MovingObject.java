package model.game;


import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * @author Alexis Richer, Goetz Alexandre
 * @version 1.1.0
 *
 * Représente un objet ayant des collisions physiques dans le jeu
 */

public abstract class MovingObject extends GameObject {
    protected Stats stats;
    protected int currentAnimation;
    protected int nbAnimation;
    public MovingObject() {
        currentAnimation = 0;
        nbAnimation = 1;
    }

    @Override
    public void draw(BufferedImage im) {
        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.drawImage(
                this.im.getSubimage((this.im.getWidth()/ nbAnimation) * currentAnimation,0,this.im.getWidth() / nbAnimation,this.im.getHeight()),
                position.x-(width/2),position.y-(height/2),
                width,height,
                null);
    }

    public int getCurrentAnimation() {
        return currentAnimation;
    }

    public void setCurrentAnimation(int currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public Stats getStats() {
        return stats;
    }

    /**
     * Déplace l'objet vers le Point p
     * @param p La destination souhaité
     */
    public void goTo(Point p){
        int x=0,y=0;
        //  à droite de la destination
        if (position.x > p.x) {
            x -= getStats().getSpeed();
        }
        // à gauche de la destination
        if (position.x < p.x) {
            x += getStats().getSpeed();
        }
        // en dessous de la destination
        if (position.y > p.y) {
            y -= getStats().getSpeed();
        }
        // au dessus de la destination
        if (position.y < p.y) {
            y += getStats().getSpeed();
        }
        position.x += x;
        position.y += y;

        moveCorrection(p);
    }

    /**
     * Réctification à faire quand le déplacement est plus petit que la vitesse
     * @param p Point à atteindre
     */
    protected void moveCorrection(Point p){
        if(Math.abs(position.x - p.x) < getStats().getSpeed() || Math.abs(position.x + p.x) < getStats().getSpeed() ){
            position.x = p.x;
        }
        if(Math.abs(position.y - p.y) < getStats().getSpeed() || Math.abs(position.y + p.y) < getStats().getSpeed() ){
            position.y = p.y;
        }
    }

    protected void moveTo(int x, int y, Maze maze){
        position.x += Math.min(x,maze.tileWidth);
        position.y += Math.min(y,maze.tileHeight);
    }

}
