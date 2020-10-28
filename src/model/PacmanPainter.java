package model;


import java.awt.image.BufferedImage;
import java.io.IOException;

import engine.Game;
import engine.GamePainter;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 * afficheur graphique pour le game
 * 
 */
public class PacmanPainter implements GamePainter {
	private PacmanGame game;

	/**
	 * la taille des cases
	 */
	protected static int WIDTH;
	protected static int HEIGHT;

	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(PacmanGame game) {
		this.game = game;
		WIDTH = this.game.getMaze().getWidth();
		HEIGHT = this.game.getMaze().getHeight();
	}

	/**
	 * methode  redefinie de Afficheur retourne une image du jeu
	 */
	@Override
	public void draw(BufferedImage im) throws IOException {
		game.draw(im);

	}

	@Override
	public int getWidth() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
