package model.game;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Gady Emanuel
 * @version 1.0.0
 *
 * Classe qui représente une boule de feu tirée par le héros
 */
public class FireBall extends MovingObject{

    private Hero hero;
    private boolean destroyed;
    private Hero.Orientation orientation;

    public FireBall(Hero hero ,int x, int y) throws IOException {
        this.hero = hero;
        im = ImageIO.read(new File("resources/images/fireball.png"));
        position = new Point(x,y);
        width = hero.getWidth()/4;
        height = hero.getHeight()/4;
        stats = new Stats(0,5);
        destroyed = false;
        orientation = hero.getOrientation();

    }

    /**
     * Permet de faire se deplacer un monstre dans le labyrinthe
     * @param maze Labyrinthe dans lequel le monstre évolue
     */
    public void move(Maze maze) {
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
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
