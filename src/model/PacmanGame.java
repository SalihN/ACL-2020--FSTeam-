package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import engine.Cmd;
import engine.Game;
import model.game.Maze;
import views.GameScreen;
import views.MazeScreen;
import views.MenuScreen;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private GameScreen currentScreen;
	private Maze maze;
	public static int cpt;
	public enum GameState{
		Maze,MainMenu,Pause,Victory,Lost,Quit
	}
	private GameState currentState;


	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame() throws IOException {
		currentScreen = new MenuScreen(this);
		currentState = GameState.MainMenu;
		/*maze = new Maze();
		cpt = 0;
		maze.generate();*/
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande commande reçu par le clavier
	 */
	@Override
	public void evolve(Cmd commande) throws IOException {
		currentScreen.update(commande);
	}

	/**
	 * Affichage de l'écran en cours
	 * @param im frame buffer
	 */
	public void draw(BufferedImage im) {
		currentScreen.display(im);
		//maze.draw(im);
		//Graphics2D crayon = (Graphics2D) im.getGraphics();
	}

	/**
	 * verifie si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return currentState == GameState.Quit;
	}

	/**
	 *
	 * @return état courant du jeu
	 */
	public GameState getCurrentState() {
		return currentState;
	}

	/**
	 *
	 * @param currentState permet de changer l'état courrant du jeu
	 */
	public void setCurrentState(GameState currentState) throws IOException {
		if(currentState == GameState.Maze && this.currentState == GameState.MainMenu)
			loadMaze();
		this.currentState = currentState;
	}

	private void loadMaze() throws IOException {
		currentScreen = new MazeScreen(this);
	}

}
