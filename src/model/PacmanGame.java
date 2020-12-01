package model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import engine.Cmd;
import engine.Game;
import engine.GameController;
import views.*;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private GameScreen currentScreen;
	public enum GameState{
		Maze,MainMenu, OptionMenu, Pause,Victory,Lost,Quit
	}
	private GameState currentState;
	private PacmanController.KeyboardMode chosenMode;
	private OptionScreen optionScreen;


	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame() throws IOException {
		currentScreen = new MenuScreen(this);
		currentState = GameState.MainMenu;
		chosenMode = PacmanController.KeyboardMode.AZERTY;
		optionScreen = new OptionScreen(this);
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

		swapScreen(currentState);
		this.currentState = currentState;
	}

	/**
	 * Change l'écran à afficher
	 * @param currentState nouvel état du jeu
	 * @throws IOException Chargement d'images non réussit
	 */
	private void swapScreen(GameState currentState) throws IOException {
		if(currentState == GameState.Maze)
			currentScreen = new MazeScreen(this);
		if(currentState == GameState.Lost)
			currentScreen = new EndScreen(this,"Lost");
		if(currentState == GameState.Victory)
			currentScreen = new EndScreen(this,"Victory");
		if(currentState == GameState.MainMenu)
			currentScreen = new MenuScreen(this);
		if(currentState == GameState.OptionMenu)
			currentScreen = optionScreen;
	}

	/**
	 * Mode actuel des contrôles en ZQSD
	 */
	public void azertyMode(){
		chosenMode = PacmanController.KeyboardMode.AZERTY;
	}

	/**
	 * Mode actuel des contrôles en WASD
	 */
	public void qwertyMode(){
		chosenMode = PacmanController.KeyboardMode.QWERTY;
	}

	@Override
	public GameController.KeyboardMode getChosenMode() {
		return chosenMode;
	}

}
