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
     * @param im frame buffer
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
     * Regarde si il y a collision entre l'objet et un autre objet donné
     * @param objectToCheck Objet avec lequel on cherche à vérifie si il y a collision
     * @return vrai si il y a effectivement une collision, faux sinon
     */
    public boolean checkCollision(GameObject objectToCheck){
        boolean right,top,bottom,left;

        bottom = this.position.y + this.height/2  < objectToCheck.position.y - objectToCheck.height/2  ;
        top = this.position.y - this.height/2  > objectToCheck.position.y + objectToCheck.height/2;
        right = this.position.x + this.width/2  < objectToCheck.position.x - objectToCheck.width/2;
        left = this.position.x - this.width/2 > objectToCheck.position.x + objectToCheck.width/2;

        return !top && !right && !left && !bottom;
    }

    /***
     * Vérification qu'il n'y a pas de mur dans la direction (x,y) vers laquel l'objet se dirige
     * @param x déplacement en x
     * @param y déplacement en y
     * @param maze labyrinthe dans lequel l'objet évolue
     * @return retourne vrai si il n'y a pas de mur
     */
    // TODO: gérer le cas où le déplacement est plus grand qu'une tuile
    public boolean checkWall(int x, int y,Maze maze){
        // out of bound check
        if(
                this.getPosition().x + x + this.getWidth()> maze.getWidth()
                || this.getPosition().y  + y + this.getHeight() > maze.getHeight()
                ||this.getPosition().x + x < 0
                || this.getPosition().y + y < 0
        ){
            return false;
        }
        //LEFT
        if(x < 0 && y == 0) {
            return (
                    // LEFT UP
                    !maze.isAWall(
                            this.getPosition().x + x - this.getWidth() / 2,
                            this.getPosition().y + y - this.getHeight() / 2)
                            //LEFT DOWN
                            && !maze.isAWall(
                            this.getPosition().x + x - this.getWidth() / 2,
                            this.getPosition().y + y + this.getHeight() / 2)
            );
            //RIGHT
        }else if(x > 0 && y == 0 ){
            return (
                    //RIGHT UP
                    !maze.isAWall(
                            this.getPosition().x + x + this.getWidth() / 2,
                            this.getPosition().y + y - this.getHeight() / 2
                    )
                            //RIGHT DOWN
                            && !maze.isAWall(
                            this.getPosition().x + x + this.getWidth() / 2,
                            this.getPosition().y + y + this.getHeight() / 2)
            );
            //DOWN
        }else if(x == 0 && y > 0){
            return (
                    //DOWN RIGHT
                    !maze.isAWall(
                            this.getPosition().x + x + this.getWidth() / 2,
                            this.getPosition().y + y + this.getHeight() / 2)
                            //DOWN LEFT
                            && !maze.isAWall(
                            this.getPosition().x + x - this.getWidth() / 2,
                            this.getPosition().y + y + this.getHeight() / 2)
            );
            //UP
        }else{
            return (
                    //UP LEFT
                    !maze.isAWall(
                            this.getPosition().x + x - this.getWidth() / 2,
                            this.getPosition().y + y - this.getHeight() / 2)
                            //UP RIGHT
                            && !maze.isAWall(
                            this.getPosition().x + x + this.getWidth() / 2,
                            this.getPosition().y + y - this.getHeight() / 2)
            );
        }
    }

    /**
     *
     * @param width largeur souhaité pour l'objet
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     *
     * @param height hauteur souhaité pour l'objet
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return position de l'objet
     */
    public Point getPosition() {
        return position;
    }

    /**
     *
     * @param position position où mettre l'objet
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     *
     * @return largeur de l'objet
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return hauteur de l'objet
     */
    public int getHeight() {
        return height;
    }
}
