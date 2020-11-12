package model.game;


import java.awt.*;

/**
 * @author Alexis Richer, Goetz Alexandre
 * @version 1.1.0
 *
 * Représente un objet ayant des collisions physiques dans le jeu
 */

public abstract class SolidObject extends GameObject {
    protected Stats stats;

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
