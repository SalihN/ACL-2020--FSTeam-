package model.game.floor;

import model.game.Hero;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Emanuel Gady,
 * @version 1.0.0
 *
 * Case magique du labyrinthe
 */
public class MagicalFloor extends Floor{

    private boolean isActivate;
    private TimerTask decount;

    public MagicalFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = ImageIO.read(new File("resources/images/magicalfloor.png"));

        isActivate = false;
    }

    /**
     * Fonction qui lance aléatoirement un effet lorsque le heros marche dessus
     * @param hero
     */
    public void activate(Hero hero) throws IOException {
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
        System.out.println(hero.getStats().getHp()+" / "+hero.getStats().getHpMax());
    }

    /**
     * Fonction qui permet de desactiver une MagicalFloor pendant un certain temps
     * @throws IOException
     */
    private void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/magicalflooractivate.png"));
    }

    /**
     * Fonction qui permet de réactiver une MagicalFloor
     * @throws IOException
     */
    private void reactivate() throws IOException {
        isActivate = false;
        im = ImageIO.read(new File("resources/images/magicalfloor.png"));
    }

    @Override
    public boolean isMagicalFloor() {
        return true;
    }

    /////////////////
    //Getter&Setter//
    /////////////////

    public boolean isActivate() {
        return isActivate;
    }
}

