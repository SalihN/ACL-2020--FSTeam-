package model.game;

import model.game.floor.*;
import model.game.monster.Monster;
import model.game.monster.NormalMonster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Alexis Richer
 * @version 2.2.1
 *
 * Labyrinthe du jeu
 */
public class Maze {
    private Collection<Floor> listFloor;
    private Collection<Monster> listMonsters;
    private int ligne, colonne;
    public final int WIDTH = 32;
    public final int HEIGHT = 32;

    public Maze() throws IOException {
        listFloor = new ArrayList<>();
        listMonsters = new ArrayList<>();
        listMonsters.add(new NormalMonster(new Point(100,100),20,20));
        ligne = 0;
    }

    /**
     * Permet de creer un labyrinthe à partir d'un fichier texte
     * @param string
     * @throws IOException
     */
    public void generate(String string) throws IOException {
        colonne = 0;
        for (char ch: string.toCharArray()) {
            switch(ch){
                case 'w' :
                    listFloor.add(new Wall(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'n' :
                    listFloor.add(new NormalFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 't' :
                    listFloor.add(new TreasureFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'h' :
                    listFloor.add(new HealthFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 'f' :
                    listFloor.add(new FreezeFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
                case 's' :
                    listFloor.add(new SlowFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
            }
            colonne += WIDTH;
        }
        ligne += HEIGHT;
    }

    /**
     * Permet de dessiner le labyrinthe ainsi que les monstres present dans ce labyrinthe
     * @param im
     * @throws IOException
     */
    public void draw(BufferedImage im) throws IOException {
        for (Floor floor : listFloor) {
            floor.draw(im);
        }
        for(Monster monster : listMonsters){
            if(monster.isCanMove()) {
                monster.move(this, WIDTH, HEIGHT);
            }
            monster.draw(im);
        }
    }

    /**
     * Permetde savoir si une case est un mur ou non
     * @param x
     * @param y
     * @return
     */
    public boolean isAWall(int x, int y){
        if(getFloor(x, y).isWall()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Permet d'empêcher les monstres de bouger un certain temps
     */
    public void freezeMonsters(int time) {
        for(Monster monster : listMonsters){
            monster.freeze();
        }
        Timer timer = new Timer();
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                defreeze();
            }
        };
        timer.schedule(decount, time*1000);
    }

    /**
     * Permet aux monstres de rebouger
     */
    private void defreeze() {
        for(Monster monster : listMonsters){
            monster.setCanMove(true);
        }
    }

    /**
     * Permet de ralentir les monstres un certain temps
     */
    public void slowMonsters(int time, int slow) {
        for(Monster monster : listMonsters){
            monster.setSpeed(monster.getSpeed() - slow);
        }
        Timer timer = new Timer();
        TimerTask decount = new TimerTask() {
            @Override
            public void run() {
                deslow(slow);
            }
        };
        timer.schedule(decount, time*1000);
    }

    /**
     * Permet aux monstres de rebouger normalement
     */
    private void deslow(int slow) {
        for(Monster monster : listMonsters){
            monster.setSpeed(monster.getSpeed() + slow);
        }
    }

    /**
     * Renvoie le floor aux coordonnées
     * @param x abscisses
     * @param y ordonnées
     * @return
     */
    public Floor getFloor(int x, int y){
        for (Floor floor: listFloor) {

            if(floor.getPosition().x <= x && floor.getPosition().x+WIDTH >= x
            && floor.getPosition().y <= y && floor.getPosition().y+HEIGHT >= y){
                return floor;
            }
        }
        return null;
    }

    public int getWidth(){
        return colonne;
    }

    public int getHeight(){
        return ligne;
    }
}
