package model.game;

import model.game.floor.*;
import model.game.monster.Monster;
import model.game.monster.NormalMonster;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Maze {
    private Collection<Floor> listFloor;
    private Collection<Monster> listMonsters;
    private int ligne, colonne;
    public final int WIDTH = 32;
    public final int HEIGHT = 32;

    public Maze(){
        listFloor = new ArrayList<>();
        listMonsters = new ArrayList<>();
        listMonsters.add(new NormalMonster(new Point(100,100),20,20));
        ligne = 0;
    }

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
                case 'm' :
                    listFloor.add(new MagicalFloor(new Point(colonne, ligne), WIDTH, HEIGHT));
                    break;
            }
            colonne += WIDTH;
        }
        ligne += HEIGHT;
    }

    public void draw(BufferedImage im) throws IOException {
        for (Floor floor : listFloor) {
            floor.draw(im);
        }
        for(Monster monster : listMonsters){
            monster.move(this, WIDTH, HEIGHT);
            monster.draw(im);
        }
    }

    public boolean isAWall(int x, int y){
        if(getFloor(x, y) instanceof Wall){
            return true;
        }else{
            return false;
        }
    }

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
