package model.game.monster;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre, Gady Emanuel
 * @version 1.0.1
 *
 * Monstre qui ramène le heros au debut du labyrinthe
 */
public class KidnapMonster extends Monster {
    private final Point startingPos;
    private boolean isTracking,isReturning;
    private int heroPreviousSpeed;
    public KidnapMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        startingPos = new Point(point);
        stats.setSpeed(2);
        isTracking = true;
        isReturning = false;
        heroPreviousSpeed = 0 ;
        im = ImageIO.read(new File("resources/images/kidnapmonster.png"));
    }

    /**
     * Fonction de traque du héro
     * @param maze Labyrinthe dans lequel le monstre évolue
     */
    @Override
    public void move(Maze maze) {
        // traque du héro
        if(isTracking && !isReturning) {
            goTo(maze.getHero().getPosition());
        }
        //  Le héro est ramené à sa case départ
        if(!isTracking && !isReturning){
            goTo(maze.getHero().getHeroStartingPos());
            maze.getHero().goTo(position);
            // Arrivé au départ du héro retour à sa case initiale
            if(position.equals(maze.getHero().getHeroStartingPos())){
                isReturning = true;
                maze.getHero().setCatched(false);
            }
        }
        // Retour à la case de départ du Kidnappeur
        if(!isTracking && isReturning){

            if(stats.getSpeed() != 2) {
                stats.setSpeed(2);
                maze.getHero().getStats().setSpeed(heroPreviousSpeed);
            }

            goTo(startingPos);
            // Arrivé à sa case, retour en traque
            if(position.equals(startingPos)){
                isReturning = false;
                isTracking = true;
            }
        }
    }

    @Override
    public void action(Hero hero) {
        if(isTracking && stats.getSpeed() > 0){
            isTracking = false;
            int catchedSpeed = 6;

            stats.setSpeed(catchedSpeed);
            heroPreviousSpeed = hero.getStats().getSpeed();
            hero.getStats().setSpeed(catchedSpeed);
            hero.setCatched(true);
            hero.addScore(-1);
        }
    }
}
