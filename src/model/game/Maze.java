package model.game;

import model.game.floor.Floor;
import model.game.floor.NormalFloor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;

public class Maze {
    private Collection<Floor> listFloor;

    public Maze(){
        //Ici j'ai ajouté un sol à la main mais bien sûr ce sera généré par un txt
        listFloor = new ArrayList<>();
        listFloor.add(new NormalFloor(new Point(0,0), 50, 50));
    }

    public void draw(BufferedImage im){
        for(Floor floor : listFloor){
            floor.draw(im);
        }
    }
}
