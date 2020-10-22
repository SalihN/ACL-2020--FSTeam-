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
	protected static int WIDTH = 750;
	protected static int HEIGHT = 500;

	/**
	 * appelle constructeur parent
	 */
	public PacmanPainter(PacmanGame game) {
		this.game = game;
		this.WIDTH = this.game.getMaze().getWidth();
		this.HEIGHT = this.game.getMaze().getHeight();
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
