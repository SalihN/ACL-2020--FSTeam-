package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import engine.Cmd;
import engine.Game;
import model.game.Hero;
import model.game.Maze;
import model.game.floor.MagicalFloor;

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
	private int time;
	private TimerTask decount;

	private int sizeOfPolice = 24;
	Font font = new Font("TimesRoman", Font.PLAIN, sizeOfPolice);

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame(String source) throws IOException {
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
		time = 60;
		Timer timer = new Timer();
		decount = new TimerTask() {
			@Override
			public void run() {
				countDown();
			}
		};
		timer.schedule(decount, 100, 1000);
	}

	private void countDown(){
		time--;
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
				if(check(0, -hero.getStats().getSpeed())) {
					hero.move(0, -hero.getStats().getSpeed());
				}
				break;
			case DOWN:
				if(check(0, hero.getStats().getSpeed())) {
					hero.move(0, hero.getStats().getSpeed());
				}
				break;
			case LEFT:
				if(check(-hero.getStats().getSpeed(), 0)) {
					hero.move(-hero.getStats().getSpeed(), 0);
				}
				break;
			case RIGHT:
				if(check(hero.getStats().getSpeed(),0)) {
					hero.move(hero.getStats().getSpeed(), 0);
				}
				break;
		}
	}

	public void draw(BufferedImage im) throws IOException {
		maze.draw(im);
		hero.draw(im);
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.red);
		crayon.setFont(font);
		crayon.drawLine(0,maze.getHeight()/2,maze.getWidth(),maze.getHeight()/2);
		crayon.drawLine(maze.getWidth()/2,0,maze.getWidth()/2,maze.getHeight());
		crayon.drawString(Integer.toString(time), maze.getWidth()-((sizeOfPolice+maze.WIDTH)/2), (sizeOfPolice/2 + maze.HEIGHT)/2);
		update();
	}

	private void update() throws IOException {
		if(maze.getFloor(hero.getPosition().x, hero.getPosition().y).isMagicalFloor()){
			MagicalFloor magicalFloor = (MagicalFloor) maze.getFloor(hero.getPosition().x, hero.getPosition().y);
			if(!magicalFloor.isActivate()){
				magicalFloor.activate(hero);
			}
		}
	}

	/***
	 * check the collision to a wall where the player is going
	 * @param x amount of x added by the move
	 * @param y amount of y added by the move
	 * @return
	 */
	private boolean check(int x, int y){
			//LEFT
		if(x < 0 && y == 0) {
			return (!maze.isAWall(
					hero.getPosition().x + x - hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2
			) &&!maze.isAWall(
					hero.getPosition().x + x - hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2
			));
			//RIGHT
		}else if(x > 0 && y == 0 ){
			return (!maze.isAWall(
					hero.getPosition().x + x + hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2
			) &&!maze.isAWall(
					hero.getPosition().x + x + hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2
			));
			//DOWN
		}else if(x == 0 && y > 0){
			return !maze.isAWall(
					hero.getPosition().x + x,
					hero.getPosition().y + y + hero.getHeight()/2
			);
			//UP
		}else{
			return !maze.isAWall(
					hero.getPosition().x + x ,
					hero.getPosition().y + y - hero.getHeight()/2
			);
		}
	}

	/**
	 * verifier si le jeu est fini
	 */
	@Override
	public boolean isFinished() {
		if(maze.getFloor(hero.getPosition().x, hero.getPosition().y).isTreasorFloor() || time == 0){
			return true;
		}else{
			return false;
		}
	}

	/////////////////
	//Getter&Setter//
	/////////////////

	public Maze getMaze() {
		return maze;
	}
}
