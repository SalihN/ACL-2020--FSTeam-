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
import model.game.floor.ActivateFloor;
import model.game.monster.Monster;

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
		maze.generate(source);
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
		int x=0;
		int y=0;
		if(commande == Cmd.UP){
			y -= hero.getStats().getSpeed();
		}
		if(commande == Cmd.DOWN){
			y += hero.getStats().getSpeed();
		}
		if(commande == Cmd.LEFT){
			x -= hero.getStats().getSpeed();
		}
		if(commande == Cmd.RIGHT){
			x +=  hero.getStats().getSpeed();
		}
		if(check(x,y)){
			hero.move(x,y);
		}

	}
	// TODO Maze = Niveau, pourquoi le compteur serait-il sur l'entièreté du jeu et pas dans le draw du niveau ?
	public void draw(BufferedImage im) throws IOException {
		maze.draw(im);
		hero.draw(im);
		Graphics2D crayon = (Graphics2D) im.getGraphics();
		crayon.setColor(Color.red);
		crayon.setFont(font);
		crayon.drawString(Integer.toString(time), maze.getWidth()-((sizeOfPolice+maze.WIDTH)/2), (sizeOfPolice/2 + maze.HEIGHT)/2);
		update();
	}

	/**
	 * Mets à jour les sols activables
	 */
	private void update() throws IOException {
		if(maze.getFloor(hero.getPosition().x, hero.getPosition().y).isActivateFloor()){
			ActivateFloor activateFloor = (ActivateFloor) maze.getFloor(hero.getPosition().x, hero.getPosition().y);
			activateFloor.activate(hero, maze);
		}
		for(Monster monster : getMaze().getListMonsters()){
			if(hero.checkCollision(monster)){
				hero.getStats().hit(1);
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
			return (
					// LEFT UP
					!maze.isAWall(
					hero.getPosition().x + x - hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2)
					//LEFT DOWN
					&&!maze.isAWall(
					hero.getPosition().x + x - hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2)
			);
			//RIGHT
		}else if(x > 0 && y == 0 ){
			return (
					//RIGHT UP
					!maze.isAWall(
					hero.getPosition().x + x + hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2
			)
					//RIGHT DOWN
					&&!maze.isAWall(
					hero.getPosition().x + x + hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2)
			);
			//DOWN
		}else if(x == 0 && y > 0){
			return (
					//DOWN RIGHT
					!maze.isAWall(
					hero.getPosition().x + x +  hero.getWidth() /2,
					hero.getPosition().y + y + hero.getHeight()/2)
					//DOWN LEFT
					&&!maze.isAWall(
					hero.getPosition().x + x - hero.getWidth() /2 ,
					hero.getPosition().y + y + hero.getHeight()/2)
			);
			//UP
		}else{
			return (
					//UP LEFT
					!maze.isAWall(
					hero.getPosition().x + x -  hero.getWidth() /2,
					hero.getPosition().y + y - hero.getHeight()/2)
					//UP RIGHT
					&&!maze.isAWall(
					hero.getPosition().x + x + hero.getWidth() /2 ,
					hero.getPosition().y + y - hero.getHeight()/2)
			);
		}
	}

	/**
	 * verifie si le jeu est fini
	 */
	@Override

	public boolean isFinished() {
		if(maze.getFloor(hero.getPosition().x, hero.getPosition().y).isTreasorFloor() || time == 0){
			return true;
		}else{
			return false;
		}
	}

	public Maze getMaze() {
		return maze;
	}
}
