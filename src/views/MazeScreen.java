package views;

import engine.Cmd;
import model.PacmanGame;
import model.game.Maze;

import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * @author Goetz Alexandre
 * version 1.0.0
 */

/**
 * Ecran d'une partie de jeu
 * Le labyrinthe et le héros sont représentés ici
 */
public class MazeScreen implements GameScreen {
    private Maze maze;
    private PacmanGame game;

    /**
     * display the maze screen
     * @throws IOException
     */
    public MazeScreen(PacmanGame game) throws IOException {
        maze = new Maze();
        maze.generate();
        this.game = game;
    }

    /**
     * affiche l'écran de jeu dans le labyrinthe
     * @param im
     */
    @Override
    public void display(BufferedImage im) {
        maze.draw(im);
    }

    /**
     * met à jour les états de la partie en labyrinthe
     * @param cmd
     * @throws IOException
     */
    @Override
    public void update(Cmd cmd) throws IOException {
        maze.update(cmd);
        if(maze.getTime() <= 0 || maze.getHero().isDead()) {
            this.game.setCurrentState(PacmanGame.GameState.Lost);
            maze.kill();
        }
        if(maze.isVictory())
            this.game.setCurrentState(PacmanGame.GameState.Victory);
    }
    /**
     *
     * @return Labyrinthe en cours de partie
     */
    public Maze getMaze() {
        return maze;
    }
}
