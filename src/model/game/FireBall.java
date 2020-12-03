package model.game;


import model.game.monster.Monster;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gady Emanuel, Richer Alexis
 * @version 2.0.0
 *
 * Classe qui représente une boule de feu tirée par le héros
 */
public class FireBall extends MovingObject{
    private Hero hero;
    protected boolean destroyed;
    private Hero.Orientation orientation;

    public FireBall(Hero hero ,int x, int y) throws IOException {
        this.hero = hero;
        im = ImageIO.read(new File("resources/images/fireball.png"));
        position = new Point(x,y);
        width = hero.getWidth();
        height = hero.getHeight();
        stats = new Stats(0,5);
        destroyed = false;
        orientation = hero.getOrientation();

        this.nbAnimation = 29;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                swapanime();
            }
        },0,20);
    }

    /**
     * Permet de faire se deplacer une boule de feu dans le labyrinthe
     * @param maze Labyrinthe dans lequel le joueur évolue
     */
    public void move(Maze maze) throws IOException {
        int x = 0, y = 0;

        // RIGHT
        if (orientation == Hero.Orientation.RIGHT) {
            x += stats.getSpeed();
        }
        // LEFT
        else if (orientation == Hero.Orientation.LEFT) {
            x -= stats.getSpeed();
        }
        // UP
        else if (orientation == Hero.Orientation.UP) {
            y -= stats.getSpeed();
        }
        // DOWN
        else if (orientation == Hero.Orientation.DOWN) {
            y += stats.getSpeed();
        }

        if (!this.checkWall(x, y, maze)) {
            destroyed = true;
        }
        else {
            moveTo(x,y,maze);
        }

        for(Monster monster : maze.getListMonsters()){
            if (monster.checkCollision(this) && !hero.isCatched()){
                monster.setAlive(false);
                destroyed = true;
                hero.addScore(10);
                Maze.sound("monster_death.wav");
            }
        }
    }

    protected void swapanime(){
            currentAnimation = (currentAnimation + 1)%nbAnimation;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
