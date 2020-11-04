package model.game.monster;

import model.game.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


/**
 * @author Alexis Richer,
 * @version 1.0.1
 *
 * Monstre present dans le labyrinthe
 */
public abstract class Monster {
    protected Point position;
    protected int width;
    protected int height;
    protected int speed;
    protected BufferedImage im;
    private int moveValue = 1;
    protected boolean canMove;

    public Monster(Point point, int width, int height){
        this.position = point;
        this.width = width;
        this.height = height;
        canMove = true;
    }

    /**
     * Affiche l'image du monstre
     * @param im
     * @throws IOException
     */
    public void draw(BufferedImage im) throws IOException{

    }

    /**
     * Permet de faire se deplacer un monstre dans le labyrinthe
     * @param maze
     * @param wallWidth
     * @param wallHeight
     */
    public void move(Maze maze, int wallWidth, int wallHeight){
            // RIGHT
        if(moveValue == 1) {
            if(!maze.isAWall(position.x + speed + wallWidth/2, position.y)){
                position.x += speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 3;
            }
        }   // DOWN
        else if(moveValue == 2) {
            if(!maze.isAWall(position.x, position.y + speed + wallHeight/2)){
                position.y += speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 1;
            }
        }   // LEFT
        else if(moveValue == 3) {
            if(!maze.isAWall(position.x - speed - wallWidth/2, position.y)){
                position.x -= speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 4;
            }
        }   // UP
        else if(moveValue == 4) {
            if(!maze.isAWall(position.x, position.y - speed - wallHeight/2)){
                position.y -= speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 2;
            }
        }
    }

    public void freeze(){
        canMove = false;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }
}
