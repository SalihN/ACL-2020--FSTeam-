package model.game.monster;

import model.game.Hero;
import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @author Goetz Alexandre
 * @version 1.0
 *
 * Monstre qui ramène le heros au debut du labyrinthe
 */
public class KidnapMonster extends Monster {
    private final Point startingPos;
    private boolean isTracking,isReturning;
    public KidnapMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        startingPos = new Point(point);
        stats.setSpeed(2);
        isTracking = true;
        isReturning = false;
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
            // Arrivé au départ du héro
            if(position.equals(maze.getHero().getHeroStartingPos())){
                isReturning = true;
                maze.getHero().setCatched(false);
            }
        }
        // Retour à la case de départ du Kidnappeur
        if(!isTracking && isReturning){
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
        if(isTracking){
            isTracking = false;
            hero.setCatched(true);
        }
    }
}
