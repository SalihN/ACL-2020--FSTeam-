package model.game.monster;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Richer Alexis
 * @version 1.0.0
 */

public class FireMonster extends Monster {
    private ArrayList<FireBallOcto> listFireBalls;
    private int cpt;

    public FireMonster(Hero hero, Point point, int width, int height) throws IOException {
        super(point, width, height);
        im = ImageIO.read(new File("resources/images/octorok.png"));
        listFireBalls = new ArrayList<>();
        cpt = 0;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(isAlive() && canMove)
                    launchFireBall(hero);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        },0,1500);
    }
    protected void swapanime(){
        if(canMove && cpt%2 == 0) {
            currentAnimation = (currentAnimation + 1) % nbAnimation;
        }
        cpt++;
    }


    private void launchFireBall(Hero hero) throws IOException {
        listFireBalls.add(new FireBallOcto(hero, this.position.x, this.position.y+getHeight()/2));
    }

    @Override
    public void move(Maze maze) throws IOException {
        Iterator iterator = listFireBalls.iterator();
        while (iterator.hasNext()){
            FireBallOcto f = (FireBallOcto) iterator.next();
            if(!f.isDestroyed()) {
                f.move(maze);
            } else {
                iterator.remove();
            }
        }
    }

    @Override
    public void draw(BufferedImage im) {
        super.draw(im);
        for (FireBallOcto fireBall: listFireBalls) {
            fireBall.draw(im);
        }
    }
}