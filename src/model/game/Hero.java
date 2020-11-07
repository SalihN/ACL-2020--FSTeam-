package model.game;

import engine.Cmd;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Alexis Richer, Emanuel Gady
 * @version 1.0.1
 *
 * Heros present dans le labyrinthe
 */
public class Hero extends SolidObject {

    public Hero() throws IOException {
        stats = new Stats(5,5);
        position = new Point(0,0);
        width = 20;
        height = 20;

        im = ImageIO.read(new File("resources/images/hero.png"));
    }

    /**
     * Déplacement du héro en fonction de la commande donnée dans le labyrinth
     * @param commande
     * @param maze
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
     * Permet de savoir sur le heros est mort ou non
     * @return
     */
    public boolean isDead(){
        if(stats.getHp() == 0){
            return true;
        } else {
            return false;
        }
    }

    public Stats getStats() {
        return stats;
    }

}
