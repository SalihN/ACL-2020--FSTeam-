package model.game.floor;

import model.game.Hero;
import model.game.Maze;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class SpikeFloor extends ActivateFloor {

    private final BufferedImage  imSpikeDown=ImageIO.read(new File("resources/images/floorspikedown.png"));
    private final BufferedImage  imSpikeUp=ImageIO.read(new File("resources/images/floorspikeup.png"));
    private int delay;

    /**
     *
     * @param p position dans le labyrinthe
     * @param w largeur dans la frame
     * @param h hauteur dans la frame
     * @throws IOException erreur chargement image
     */
    public SpikeFloor(Point p, int w, int h) throws IOException {
        super(p, w, h);
        im = imSpikeUp;
       this.delay = 3000;
        Timer t = new Timer();
        t.schedule( new TimerTask() {
        @Override
        public void run() {
            if(im == imSpikeDown){
                im = imSpikeUp;
            }
            else{
                im = imSpikeDown;
            }
        }},0,this.delay);
    }

    /**
     * Fait des dégats aux héro si les piques sont sorties
     * @param hero héro à qui faire des dégats
     * @param maze labyrinthe dans lequel les piques évoluent
     * @throws IOException
     */
    @Override
    public void activate(Hero hero, Maze maze) throws IOException {
        if(im == imSpikeUp){

            if(!hero.isInvincible() && !hero.isCatched()) {
                hero.getStats().hit(1);
                hero.addScore(-20);
                hero.setInvincible(true);
                Maze.sound("ouf.wav");
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
    }

    /**
     * desactive le piège
     * @throws IOException image non chargée
     */
    @Override
    public void desactivate() throws IOException {
        im = ImageIO.read(new File("resources/images/normalfloor.png"));
    }

    /**
     *
     * @return temps entre la sortie et la rentré des piques
     */
    public int getDelay() {
        return delay;
    }

    /**
     *
     * @param delay interval de temps pour sortir et rentrer les pique
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }
}
