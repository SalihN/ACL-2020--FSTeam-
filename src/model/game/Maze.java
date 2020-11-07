package model.game;

import model.PacmanGame;
import model.PacmanPainter;
import model.game.floor.*;
import model.game.monster.Monster;
import model.game.monster.NormalMonster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Alexis Richer, Goetz Alexandre
 * @version 2.2.1
 *
 * Labyrinthe du jeu
 */
//TODO: Vérifier que le fichier a bien un laby rectangulaire et sa taille au début du fichier
public class Maze {
    private Hero hero;
    private Floor[][] listFloor;
    private Collection<Monster> listMonsters;
    private int labyHeight, labyWidth;
    //TILE SIZE
    public  int tileWidth = 32;
    public  int tileHeight = 32;

    private TimerTask decount;
    private int time;
    private int sizeOfPolice = 24;
    Font font = new Font("TimesRoman", Font.PLAIN, sizeOfPolice);

    public Maze(Hero hero) throws IOException {
        this.hero = hero;
        listMonsters = new ArrayList<>();
        labyHeight = 0;
        labyWidth=0;
        reset();
    }

    /**
     * Permet de creer un labyrinthe à partir d'un fichier texte
     * Les deux première lignes du fichier sont la largeur et la longueur du labyrinthe
     * Les lignes suivantes sont la compositions de celui ci avec les symbole correspondant
     * @throws IOException
     */
    public void generate() throws IOException {
        BufferedReader buff = null;
        String level = "resources/mazes/maze"+ PacmanGame.cpt + ".txt";
        String line;
        try {
            buff = new BufferedReader(new FileReader(level));
        }
        catch(FileNotFoundException err){
            System.exit(0);
        }
        // lecture du nombre de lignes et de colonnes
        line = buff.readLine();
        this.labyHeight = Integer.parseInt(line)+2;
        tileHeight = (int) Math.ceil((double)PacmanPainter.tileHeight / labyHeight);

        line = buff.readLine();
        this.labyWidth = Integer.parseInt(line)+2;
        tileWidth = (int) Math.ceil((double)PacmanPainter.tileWidth / labyWidth);

        hero.setWidth(tileWidth);
        hero.setHeight(tileHeight);


        listFloor = new Floor[labyHeight][labyWidth];
        // lecture de la structure du labyrinthe
        int rowNumber = 0;

        //Construction des murs
        for(int i = 0 ; i < labyWidth ; i++){
            listFloor[i][0] = new Wall(new Point(i * tileWidth , 0), tileWidth, tileHeight);
        }
        for(int i = 0 ; i < labyWidth ; i++){
            listFloor[i][labyHeight-1] = new Wall(new Point(i * tileWidth , (labyHeight-1) * tileHeight), tileWidth, tileHeight);
        }
        for(int i = 0 ; i < labyHeight ; i++){
            listFloor[0][i] = new Wall(new Point(0 , i * tileHeight), tileWidth, tileHeight);
        }
        for(int i = 0 ; i < labyHeight ; i++){
            listFloor[labyWidth-1][i] = new Wall(new Point((labyWidth-1) * tileWidth , i * tileHeight), tileWidth, tileHeight);
        }

        while((line = buff.readLine()) != null){

            for(int j=0 ; j<line.length() ; j++){
                int i = j+1;
                switch(line.charAt(j)){
                    case 'w' :
                        listFloor[rowNumber][i] = new Wall(new Point(i * tileWidth , rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 'n' :
                        listFloor[rowNumber][i] = new NormalFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 't' :
                        listFloor[rowNumber][i] = new TreasureFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 'h' :
                        listFloor[rowNumber][i] = new HealthFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 'f' :
                        listFloor[rowNumber][i] = new FreezeFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 's' :
                        listFloor[rowNumber][i] = new SlowFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        break;
                    case 'm' :
                        listFloor[rowNumber][i] = new NormalFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        listMonsters.add(new NormalMonster(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight));
                        break;
                    case 'p' :
                        listFloor[rowNumber][i] = new NormalFloor(new Point(i * tileWidth, rowNumber * tileHeight), tileWidth, tileHeight);
                        hero.setPosition(new Point(i * tileWidth, rowNumber * tileHeight));
                        break;
                }
            }
            rowNumber++;
        }
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
     * Permet de dessiner le labyrinthe ainsi que les monstres present dans ce labyrinthe
     * @param im
     * @throws IOException
     */
    public void draw(BufferedImage im) throws IOException {
        for(int i = 0; i< labyHeight;i++){
            for(int j = 0; j< labyWidth;j++){
                listFloor[i][j].draw(im);
            }
        }
        for(Monster monster : listMonsters){
            if(monster.isCanMove()) {
                monster.move(this);
            }
            monster.draw(im);
        }

        Graphics2D crayon = (Graphics2D) im.getGraphics();
        crayon.setColor(Color.red);
        crayon.setFont(font);
        crayon.drawString(Integer.toString(time), getWidth()-((sizeOfPolice+tileWidth)/2), (sizeOfPolice/2 + tileHeight)/2);
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
            monster.getStats().setSpeed(monster.getStats().getSpeed() - slow);
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
            monster.getStats().setSpeed(monster.getStats().getSpeed() + slow);
        }
    }

    /**
     * Charge le niveau suivant
     */
    public void nextLevel() throws IOException {
        reset();
        PacmanGame.cpt++;
        generate();
    }

    /**
     * Supprime le maze
     */
    private void reset(){
        listMonsters.clear();
        time = 60;

    }

    /**
     * Return the floor position at the pixel (x,y)
     * @param x abscisses
     * @param y ordonnées
     * @return
     */
    public Floor getFloor(int x, int y){
        return listFloor[(y/tileHeight)][(x/tileWidth)];
    }

    public int getWidth(){
        return labyWidth * tileWidth;
    }

    public int getHeight(){
        return labyHeight * tileHeight;
    }

    public Collection<Monster> getListMonsters() {
        return listMonsters;
    }

    public int getTime() {
        return time;
    }
}
