package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import engine.Cmd;
import engine.Game;
import model.game.Maze;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private Maze maze;
	public static int cpt;


	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame() throws IOException {
		maze = new Maze();
		cpt = 0;
		maze.generate();
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande commande reçu par le clavier
	 */
	@Override
	public void evolve(Cmd commande) throws IOException {
		maze.update(commande);
	}

	/**
	 * Affichage de l'écran en cours
	 * @param im frame buffer
	 */
	public void draw(BufferedImage im){
		maze.draw(im);
		Graphics2D crayon = (Graphics2D) im.getGraphics();
	}

	/**
	 * verifie si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		return maze.getTime() == 0;
	}

	/**
	 *
	 * @return Labyrinthe en cours de partie
	 */
	public Maze getMaze() {
		return maze;
	}
}
