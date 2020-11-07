package model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import engine.Cmd;
import engine.Game;
import model.game.Hero;
import model.game.Maze;
import model.game.floor.ActivateFloor;
import model.game.monster.Monster;

import javax.imageio.ImageIO;

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
	public static int cpt;

	private BufferedImage life;

	/**
	 * constructeur avec fichier source pour le help
	 * 
	 */
	public PacmanGame() throws IOException {
		hero = new Hero();
		maze = new Maze(hero);
		cpt = 0;
		maze.generate();
		life = ImageIO.read(new File("resources/images/lifebar.png"));
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

	public void draw(BufferedImage im) throws IOException {
		maze.draw(im);
		hero.draw(im);

		Graphics2D crayon = (Graphics2D) im.getGraphics();

		//Affiche la barre de vie juste en dessous du heros
		float ratioVieVieMax = (float) hero.getStats().getHp() / (float) hero.getStats().getHpMax();
		crayon.drawImage(life,hero.getPosition().x - hero.getWidth()/2 ,hero.getPosition().y + hero.getHeight()/2 + 2,(int)(hero.getWidth() * ratioVieVieMax),hero.getHeight()/4,null);

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
				System.out.println("aie");
			}
		}
		if(hero.isDead()){
			//todo Retourner un menu du jeu
			hero.getStats().setSpeed(0); //Actuellement, une fois le heros mort il ne peut plus bouger
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
		if(maze.getTime() == 0){
			return true;
		}else{
			return false;
		}
	}

	public Maze getMaze() {
		return maze;
	}
}
