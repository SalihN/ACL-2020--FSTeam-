package model.game.floor;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Gady Emanuel
 * @version 1.0.0
 * M1 Informatique
 *
 * Teleporte le heros d'une case à l'autre
 */


public class TeleportFloor extends ActivateFloor {

    private TeleportFloor teleportFloor;

    public TeleportFloor(Point p, int w, int h, TeleportFloor teleportFloor) throws IOException {
        super(p, w, h);
        this.teleportFloor = teleportFloor;
        im = ImageIO.read(new File("resources/images/teleportfloor.png"));
    }

    @Override
    public void activate(Hero hero, Maze maze) throws IOException {

        if(!isActivate) {
            isActivate = true;
            teleportFloor.activate(hero,maze);
            Point position = new Point();
            position.x = teleportFloor.position.x;
            position.y = teleportFloor.position.y;
            hero.setPosition(position);
            Maze.sound("heal.wav");
            desactivate();
            Timer timer = new Timer();
            TimerTask decount = new TimerTask() {
                @Override
                public void run() {
                    try {
                        reactivate(hero);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(decount, 2000);
        }
    }
    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/teleportflooractivate.png"));
    }
    /**
     * Fonction qui permet de réactiver un TeleportFloor
     * @throws IOException Image cannot be loaded
     */
    private void reactivate(Hero hero) throws IOException {
        if (!hero.checkCollision(this) && !hero.checkCollision(teleportFloor)){

            isActivate = false;
            im = ImageIO.read(new File("resources/images/teleportfloor.png"));
        } else {
            Timer timer = new Timer();
            TimerTask decount = new TimerTask() {
                @Override
                public void run() {
                    try {
                        reactivate(hero);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(decount, 100);
        }

    }

    /**
     * Permet de relier un téléporteur à un autre
     * @param floor
     */
    public void relayTP(TeleportFloor floor) {
        teleportFloor = floor;
    }
}
