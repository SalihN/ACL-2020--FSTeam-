package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import engine.Cmd;
import engine.Game;
import model.game.Hero;
import model.game.Maze;

/**
 * @author Horatiu Cirstea, Vincent Thomas
 *
 *         Version avec personnage qui peut se deplacer. A completer dans les
 *         versions suivantes.
 * 
 */
public class PacmanGame implements Game {
	private Hero hero;
	private Maze maze;
	private int speed = 2;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) {
		hero = new Hero();
		maze = new Maze();
		BufferedReader helpReader;
		try {
			helpReader = new BufferedReader(new FileReader(source));
			String ligne;
			while ((ligne = helpReader.readLine()) != null) {
				maze.generate(ligne);
			}
			helpReader.close();
		} catch (IOException e) {
			System.out.println("Help not available");
		}
		hero.setPosition(new Point(maze.getWidth()/2, maze.getHeight()/2));
	}

	/**
	 * faire evoluer le jeu suite a une commande
	 * 
	 * @param commande
	 */
	@Override
	public void evolve(Cmd commande) {
		switch (commande){
			case UP:
				if(check(0, -speed)) {
					hero.move(0, -speed);
				}
				break;
			case DOWN:
				if(check(0, speed)) {
					hero.move(0, speed);
				}
				break;
			case LEFT:
				if(check(-speed, 0)) {
					hero.move(-speed, 0);
				}
				break;
			case RIGHT:
				if(check(speed,0)) {
					hero.move(speed, 0);
				}
				break;
		}
	}

	public void draw(BufferedImage im) throws IOException {
		maze.draw(im);
		hero.draw(im);
	}

	private boolean check(int x, int y){
		//LEFT
		if(x < 0 && y == 0) {
			return !maze.isAWall(hero.getPosition().x + x, hero.getPosition().y + y + hero.getHeight()/2);
			//RIGHT
		}else if(x > 0 && y == 0 ){
			return !maze.isAWall(hero.getPosition().x + x +hero.getWidth(), hero.getPosition().y + y + hero.getHeight()/2);
			//DOWN
		}else if(x == 0 && y > 0){
			return !maze.isAWall(hero.getPosition().x + x +hero.getWidth()/2, hero.getPosition().y + y + hero.getHeight());
			//UP
		}else{
			return !maze.isAWall(hero.getPosition().x + x +hero.getWidth()/2, hero.getPosition().y + y);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		// le jeu n'est jamais fini
		return false;
	}
}
