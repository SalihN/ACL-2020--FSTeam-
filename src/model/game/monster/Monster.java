package model.game.monster;

import model.game.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public abstract class Monster {
    protected Point position;
    protected int width;
    protected int height;
    protected int speed;
    private int moveValue = 1;

    public Monster(Point point, int width, int height){
        this.position = point;
        this.width = width;
        this.height = height;
    }

    abstract public void draw(BufferedImage im) throws IOException;

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
            if(!maze.isAWall(position.x - speed + wallWidth/2, position.y)){
                position.x -= speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 4;
            }
        }   // UP
        else if(moveValue == 4) {
            if(!maze.isAWall(position.x, position.y - speed + wallHeight/2)){
                position.y -= speed;
            }
            else {
                Random rand = new Random();
                moveValue = rand.nextInt(4-1+1) + 1;
//                moveValue = 2;
            }
        }
    }

}
