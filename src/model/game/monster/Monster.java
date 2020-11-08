package model.game.monster;

import model.game.Maze;
import model.game.SolidObject;
import model.game.Stats;

import java.awt.*;
import java.util.Random;


/**
 * @author Alexis Richer, Goetz Alexandre
 * @version 1.1
 *
 * Monstre present dans le labyrinthe
 */
public abstract class Monster extends SolidObject {
    protected int moveValue = 1;
    protected boolean canMove;

    public Monster(Point point, int width, int height){
        this.stats = new Stats(1,3);
        this.position = point;
        this.width = width;
        this.height = height;
        canMove = true;
    }

    /**
     * Permet de faire se deplacer un monstre dans le labyrinthe
     * @param maze
     */

    public void move(Maze maze){
        int x=0,y=0;
        Random rand = new Random();

        // RIGHT
        if (moveValue == 1) {
            x += stats.getSpeed();
        }
        // LEFT
        else if (moveValue == 2) {
            x -= stats.getSpeed();
        }
        // UP
        else if (moveValue == 3) {
            y -= stats.getSpeed();
        }
        // DOWN
        else if (moveValue == 4) {
            y += stats.getSpeed();
        }

        if(!this.checkWall(x,y,maze)) {
            int currentMoveValue = moveValue;
            while(moveValue == currentMoveValue){
                moveValue = rand.nextInt(4-1+1) + 1;
            }
            x = -x;
            y = -y;

        }
        position.x += x;
        position.y += y;
    }



    public void freeze(){
        canMove = false;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

}
