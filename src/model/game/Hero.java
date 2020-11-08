package model.game;

import engine.Cmd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexis Richer, Emanuel Gady
 * @version 1.0.1
 *
 * Heros present dans le labyrinthe
 */
public class Hero extends SolidObject {

    private Point heroStartingPos;
    private boolean isInvincible;
    private File image;
    private File image2;

    private int timeOfInvincibility; //En millisecondes

    public Hero() throws IOException {
        stats = new Stats(5,5);
        heroStartingPos = new Point(0,0);
        position = new Point(0,0);
        width = 20;
        height = 20;

        isInvincible = false;
        timeOfInvincibility = 500;

        image = new File("resources/images/hero.png");
        image2 = new File("resources/images/heroInvincible.png");

        im = ImageIO.read(image);
    }

    /**
     * Déplacement du héro en fonction de la commande donnée dans le labyrinth
     * @param commande commande reçu par le clavier
     * @param maze labyrinthe dans lequel le héro évolue
     */
    public void move(Cmd commande, Maze maze){
        int x=0;
        int y=0;
        if(commande == Cmd.UP){
            y -= this.getStats().getSpeed();
        }
        if(commande == Cmd.DOWN){
            y += this.getStats().getSpeed();
        }
        if(commande == Cmd.LEFT){
            x -= this.getStats().getSpeed();
        }
        if(commande == Cmd.RIGHT){
            x +=  this.getStats().getSpeed();
        }
        if(this.checkWall(x,y,maze)){
            position.x += x;
            position.y += y;
        }
    }

    /**
     *
     * @return La position à laquelle le héro commence le labyrinthe
     */
    public Point getHeroStartingPos() {
        return heroStartingPos;
    }

    /**
     *
     * @param heroStartingPos position à laquelle le héro commence le labyrinthe
     */
    public void setHeroStartingPos(Point heroStartingPos) {
        this.heroStartingPos = heroStartingPos;
    }

    /**
     * Permet de savoir sur le heros est mort ou non
     * @return retourne 0 si les points de vie du héro son inférieur ou égal à 0
     */
    public boolean isDead(){
        return stats.getHp() <= 0;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) throws IOException {
        isInvincible = invincible;
        if(invincible == true){
            im = ImageIO.read(image2);
        } else {
            im = ImageIO.read(image);
        }
    }

    public int getTimeOfInvincibility() {
        return timeOfInvincibility;
    }
}
