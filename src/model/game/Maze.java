package model.game;

import model.game.floor.Floor;
import model.game.floor.NormalFloor;
import model.game.floor.Wall;
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
    private final int WIDTH = 32;
    private final int HEIGHT = 32;

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
