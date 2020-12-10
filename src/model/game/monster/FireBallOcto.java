package model.game.monster;

import model.game.FireBall;
import model.game.Hero;
import model.game.Maze;
import model.game.Stats;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Richer Alexis
 * @version 1.0.0
 */

public class FireBallOcto extends FireBall {

    public FireBallOcto(Hero hero, int x, int y) throws IOException {
        super(hero, x, y);
        im = ImageIO.read(new File("resources/images/fireball.png"));
        position = new Point(x,y);
        stats = new Stats(0,5);
        destroyed = false;

        this.nbAnimation = 29;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                swapanime();
            }
        },0,20);
        Maze.sound("shoot.wav");
    }

    @Override
    public void move(Maze maze) throws IOException {
        int x = 0;
        int y = 0;
        y += stats.getSpeed();

        if (!this.checkWall(x, y, maze)) {
            destroyed = true;
        }
        else {
            moveTo(x,y,maze);
        }

        if(this.checkCollision(maze.getHero())){
            maze.getHero().takeDammages();
            destroyed = true;
        }
    }
}
