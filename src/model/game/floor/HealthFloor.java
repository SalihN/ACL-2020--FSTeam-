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
 * @author Emanuel Gady, Alexis Richer
 * @version 2.0.1
 *
 * Case magique du labyrinthe
 */
public class HealthFloor extends ActivateFloor {

    private TimerTask decount;

    public HealthFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/healthfloor.png"));
    }

    /**
     * Fonction qui heal le héros quand il marche dessus
     */
    public void activate(Hero hero, Maze maze) throws IOException {
        if(!isActivate) {
            isActivate = true;
            hero.getStats().heal(1);
            desactivate();
            Timer timer = new Timer();
            decount = new TimerTask() {
                @Override
                public void run() {
                    try {
                        reactivate();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(decount, 10000);
        }
    }

    /**
     * Fonction qui permet de desactiver un HealthFloor pendant un certain temps
     * @throws IOException
     */
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/healthflooractivate.png"));
    }

    /**
     * Fonction qui permet de réactiver une HealthFloor
     * @throws IOException
     */
    private void reactivate() throws IOException {
        isActivate = false;
        im = ImageIO.read(new File("resources/images/healthfloor.png"));
    }
}

