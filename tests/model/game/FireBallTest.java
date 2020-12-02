package model.game;

import model.game.monster.NormalMonster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class FireBallTest {

    private FireBall fireBall;
    private Maze maze;
    @Before
    public void setUp() throws IOException {
        maze = createMock(Maze.class);
        maze.tileWidth = 32;
        maze.tileHeight = 32;
        fireBall = new FireBall(maze.getHero(), 20,20);
    }
    @After
    public void tearDown(){

    }
    /**
     * On vérifie le déplacement d'une bouge de feu
     */
    @Test
    public void testMove(){
        expect(maze.isAWall(anyInt(), anyInt())).andReturn(false).anyTimes();
        expect(maze.getWidth()).andReturn(500).anyTimes();
        expect(maze.getHeight()).andReturn(500).anyTimes();
        replay(maze);

        fireBall.move(maze);
        assertNotEquals(new Point(50, 50), fireBall.getPosition());
    }

}