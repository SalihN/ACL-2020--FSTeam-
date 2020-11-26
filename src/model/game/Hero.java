package model.game;

import engine.Cmd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexis Richer, Emanuel Gady, Goetz Alexandre
 * @version 1.1.0
 *
 * Heros present dans le labyrinthe
 */
public class Hero extends MovingObject {

    private Point heroStartingPos;
    private boolean isInvincible;
    private boolean isCatched;
    private final File image;
    private final File image2;

    public static int score;

    private int timeOfInvincibility; //En millisecondes

    public Hero() throws IOException {
        stats = new Stats(5,5);
        heroStartingPos = new Point(0,0);
        position = new Point(0,0);
        width = 20;
        height = 20;
        score = 0;

        isInvincible = false;
        isCatched = false;

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
        if(this.checkWall(x,y,maze) && !isCatched){
            moveTo(x,y,maze);
        }
    }

    /**
     * Ajoute du score
     * @param score
     */
    public void addScore(int score) {
        this.score += score;
    }

    /**
     * Réinitialise le compteur de score
     */
    public void resetScore(){
        this.score = 0;
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

    /**
     *
     * @return l'état invicible du héro
     */
    public boolean isInvincible() {
        return isInvincible;
    }

    /**
     * change le sprite du héro quand il est en invincibilité
     * @param invincible nouvel état
     * @throws IOException Impossible de lire l'image
     */
    public void setInvincible(boolean invincible) throws IOException {
        isInvincible = invincible;
        if(invincible){
            im = ImageIO.read(image2);
        } else {
            im = ImageIO.read(image);
        }
    }

    /**
     *
     * @return temps d'invincibilité du héro
     */
    public int getTimeOfInvincibility() {
        return timeOfInvincibility;
    }

    /**
     *
     * @return Héro attrapé par un monstre ou non
     */
    public boolean isCatched() {
        return isCatched;
    }

    /**
     * Permet de changer l'état d'attrapé à non attrapé
     * @param catched nouvelle valeur pour isCatched
     */
    public void setCatched(boolean catched) {
        isCatched = catched;
    }

    public int getScore() {
        return score;
    }
}
