package model.game;

import model.game.floor.Floor;
import model.game.floor.NormalFloor;
import model.game.floor.Wall;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

public class Maze {
    private Collection<Floor> listFloor;
    private int ligne, colonne;
    private final int WIDTH = 20;
    private final int HEIGHT = 20;

    public Maze(){
        listFloor = new ArrayList<>();
        ligne = 0;
    }

    public void generate(String string){
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

    public void draw(BufferedImage im){
        for(Floor floor : listFloor){
            floor.draw(im);
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
