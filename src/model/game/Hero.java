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
    private enum Orientation{UP,DOWN,RIGHT,LEFT}
    private Orientation orientation;
    private double animeCap;
    public static int score;

    private int timeOfInvincibility; //En millisecondes

    public Hero() throws IOException {
        stats = new Stats(5,5);
        heroStartingPos = new Point(0,0);
        position = new Point(0,0);
        width = 20;
        height = 20;
        score = 0;
        nbAnimation=16;
        orientation = Orientation.DOWN;
        animeCap = System.currentTimeMillis();

        isInvincible = false;
        isCatched = false;

        timeOfInvincibility = 500;

        image = new File("resources/images/newhero.png");
        image2 = new File("resources/images/newheroinvincible.png");

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

        if(commande == Cmd.UP) {
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
        if(System.currentTimeMillis() - animeCap > 1000/12) {
            updateAnimation(commande);
            animeCap = System.currentTimeMillis();
        }

        if(this.checkWall(x,y,maze) && !isCatched){
            moveTo(x,y,maze);
        }
    }

    private void updateAnimation(Cmd commande){
        if(commande == Cmd.UP){
            orientation = Orientation.UP;
            if(currentAnimation == 10)
                currentAnimation = 11;
            else
                currentAnimation = 10;
        }
        if(commande == Cmd.DOWN){
            orientation = Orientation.DOWN;
            if(currentAnimation == 1)
                currentAnimation = 2;
            else
                currentAnimation = 1;
        }
        if(commande == Cmd.LEFT){
            if(currentAnimation == 7)
                currentAnimation = 8;
            else
                currentAnimation = 7;
        }
        if(commande == Cmd.RIGHT){
            orientation = Orientation.RIGHT;
            if(currentAnimation == 4)
                currentAnimation = 5;
            else
                currentAnimation = 4;
        }
        if(commande == Cmd.IDLE){
            if(orientation == Orientation.UP)
                currentAnimation = 9;
            if(orientation == Orientation.DOWN)
                currentAnimation = 0;
            if(orientation == Orientation.LEFT)
                currentAnimation = 6;
            if(orientation == Orientation.RIGHT)
                currentAnimation = 5;

        }
    }

    /**
     * Ajoute du score
     * @param score
     */
    public void addScore(int score) {
        this.score = Math.max(0,this.score + score);
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
