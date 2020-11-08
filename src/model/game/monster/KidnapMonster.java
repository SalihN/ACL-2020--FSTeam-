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
    public KidnapMonster(Point point, int width, int height) throws IOException {
        super(point, width, height);
        startingPos = new Point(point);
        stats.setSpeed(2);
        im = ImageIO.read(new File("resources/images/kidnapmonster.png"));
    }

    @Override
    public void move(Maze maze) {
        int x=0,y=0;
        if(position.x > maze.getHero().getPosition().x){
            x -= getStats().getSpeed();
        }
        if(position.x < maze.getHero().getPosition().x){
            x += getStats().getSpeed();
        }
        if(position.y > maze.getHero().getPosition().y){
            y -= getStats().getSpeed();
        }
        if(position.y < maze.getHero().getPosition().y){
            y += getStats().getSpeed();
        }
        position.x += x;
        position.y += y;
    }

    @Override
    public void action(Hero hero) {
        position = new Point(startingPos);
        hero.setPosition(new Point(hero.getHeroStartingPos()));
    }
}
