package model.game;

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
     * Permet de deplacer le Heros
     * @param x
     * @param y
     */
    public void move(int x, int y){
        position.x += x;
        position.y += y;
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


    /////////////////
    //Getter&Setter//
    /////////////////

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Stats getStats() {
        return stats;
    }
}
