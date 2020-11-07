package model.game.monster;

import model.game.Maze;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @author Emanuel Gady
 * @version 1.0.0
 *
 * Monstre de base
 */
public class NormalMonster extends Monster {

    public NormalMonster(Point point, int width, int height) throws IOException{
        super(point, width, height);
        im = ImageIO.read(new File("resources/images/normalmonster.png"));
    }

    /**
     * Permet de faire se deplacer un monstre dans le labyrinthe
     * @param maze
     */
    public void move(Maze maze){
        int x=0,y=0;
        Random rand = new Random();

        // RIGHT
        if (moveValue == 1) {
            x += stats.getSpeed();
        }
        // LEFT
        else if (moveValue == 2) {
            x -= stats.getSpeed();
        }
        // UP
        else if (moveValue == 3) {
            y -= stats.getSpeed();
        }
        // DOWN
        else if (moveValue == 4) {
            y += stats.getSpeed();
        }

        if(!this.checkWall(x,y,maze)) {
            int currentMoveValue = moveValue;
            while(moveValue == currentMoveValue){
                moveValue = rand.nextInt(4-1+1) + 1;
            }
            x = -x;
            y = -y;

        }
        position.x += x;
        position.y += y;
    }
}
