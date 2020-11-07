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

    /***
     * Check if there's no wall in the direction we're going according to x and y values
     * True if there's no wall
     * False if there is a wall
     * @param x x value we want to reach
     * @param y y value we want to reach
     * @param maze current maze
     * @return
     */

    public boolean checkWall(int x, int y,Maze maze){
        //LEFT
        if(x < 0 && y == 0) {
            return (
                    // LEFT UP
                    !maze.isAWall(
                            this.getPosition().x + x - this.getWidth() /2,
                            this.getPosition().y + y - this.getHeight()/2)
                            //LEFT DOWN
                            &&!maze.isAWall(
                            this.getPosition().x + x - this.getWidth() /2 ,
                            this.getPosition().y + y + this.getHeight()/2)
            );
            //RIGHT
        }else if(x > 0 && y == 0 ){
            return (
                    //RIGHT UP
                    !maze.isAWall(
                            this.getPosition().x + x + this.getWidth() /2,
                            this.getPosition().y + y - this.getHeight()/2
                    )
                            //RIGHT DOWN
                            &&!maze.isAWall(
                            this.getPosition().x + x + this.getWidth() /2 ,
                            this.getPosition().y + y + this.getHeight()/2)
            );
            //DOWN
        }else if(x == 0 && y > 0){
            return (
                    //DOWN RIGHT
                    !maze.isAWall(
                            this.getPosition().x + x +  this.getWidth() /2,
                            this.getPosition().y + y + this.getHeight()/2)
                            //DOWN LEFT
                            &&!maze.isAWall(
                            this.getPosition().x + x - this.getWidth() /2 ,
                            this.getPosition().y + y + this.getHeight()/2)
            );
            //UP
        }else{
            return (
                    //UP LEFT
                    !maze.isAWall(
                            this.getPosition().x + x -  this.getWidth() /2,
                            this.getPosition().y + y - this.getHeight()/2)
                            //UP RIGHT
                            &&!maze.isAWall(
                            this.getPosition().x + x + this.getWidth() /2 ,
                            this.getPosition().y + y - this.getHeight()/2)
            );
        }
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
