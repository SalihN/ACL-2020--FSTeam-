package views;

import engine.Cmd;

import java.awt.image.BufferedImage;
import java.io.IOException;



/**
 * @author Goetz Alexandre
 * @version 1.0.0
 */
/**
 * Différents écrans de jeu
 */
public interface GameScreen {
    public void display(BufferedImage im);
    public void update(Cmd cmd) throws IOException;
}
