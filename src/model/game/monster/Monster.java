package model.game.monster;

import model.game.Hero;
import model.game.Maze;
import model.game.SolidObject;
import model.game.Stats;

import java.awt.*;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author Alexis Richer, Goetz Alexandre, Emanuel Gady
 * @version 1.2
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
     * @param maze Labyrinthe dans lequel le monstre évolue
     */
    public void move(Maze maze) {
        int x = 0, y = 0;
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

        if (!this.checkWall(x, y, maze)) {
            int currentMoveValue = moveValue;
            while (moveValue == currentMoveValue) {
                moveValue = rand.nextInt(4 - 1 + 1) + 1;
            }
        }
        else {
            moveTo(x,y,maze);
        }
    }

    /**
     * Effets appliqués sur le heros lorsque le monstre entre  en collision avec le Heros
     * @param hero héro avec lequel le monstre est entré en collision
     */
    public void action(Hero hero) throws IOException {
        if(!hero.isInvincible() && !hero.isCatched()) {
            hero.getStats().hit(1);
            hero.setInvincible(true);

            Timer timer = new Timer();
            TimerTask decount = new TimerTask() {
                @Override
                public void run() {
                    try {
                        hero.setInvincible(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(decount, hero.getTimeOfInvincibility());

        }
    }

    /**
     * Fige l'ennemi sur place
     */
    public void freeze(){
        canMove = false;
    }

    /**
     *
     * @return True si le monstre peut bouger
     */
    public boolean isCanMove() {
        return canMove;
    }

    /**
     *
     * @param canMove figé ou non
     */
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

}
