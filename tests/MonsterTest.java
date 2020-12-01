import engine.Cmd;
import model.game.FireBall;
import model.game.Hero;
import model.game.Maze;
import model.game.monster.Monster;
import model.game.monster.NormalMonster;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

public class MonsterTest {
    private Monster monster;
    private Maze maze;

    @Before
    public void setUp() throws Exception {
        maze = createMock(Maze.class);
        maze.tileWidth = 32;
        maze.tileHeight = 32;
        monster = new NormalMonster(new Point(50,50), 20,20);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCheckWall(){
        expect(maze.isAWall(anyInt(), anyInt())).andReturn(true).anyTimes();
        expect(maze.getWidth()).andReturn(500).anyTimes();
        expect(maze.getHeight()).andReturn(500).anyTimes();
        replay(maze);

        monster.move(maze);
        assertEquals(new Point(50, 50), monster.getPosition());
    }

    /**
     * On teste la réaction du monstre s'il veut bouger alors qu'il peut
     */
    @Test
    public void testMove() {
        expect(maze.isAWall(anyInt(), anyInt())).andReturn(false).anyTimes();
        expect(maze.getWidth()).andReturn(500).anyTimes();
        expect(maze.getHeight()).andReturn(500).anyTimes();
        replay(maze);

        monster.move(maze);
        assertNotEquals(new Point(50, 50), monster.getPosition());
    }

    /**
     * On teste la réaction du monstre s'il veut bouger alors qu'il est freeze
     */
    @Test
    public void testCantMove() {
        expect(maze.isAWall(anyInt(), anyInt())).andReturn(false).anyTimes();
        expect(maze.getWidth()).andReturn(500).anyTimes();
        expect(maze.getHeight()).andReturn(500).anyTimes();
        replay(maze);

        monster.freeze();
        monster.move(maze);
        assertNotEquals(new Point(50, 50), monster.getPosition());
    }

    @Test
    public void testAction() throws IOException {
        Hero hero = new Hero();
        monster.action(hero);

        assertNotEquals(hero.getStats().getHpMax(), hero.getStats().getHp());
        assertTrue(hero.isInvincible());
    }
    @Test
    public void testFireBall() throws IOException {
        expect(maze.isAWall(anyInt(), anyInt())).andReturn(false).anyTimes();
        expect(maze.getWidth()).andReturn(500).anyTimes();
        expect(maze.getHeight()).andReturn(500).anyTimes();
        FireBall fireBall = new FireBall(new Hero(), monster.getPosition().x, monster.getPosition().y);
        ArrayList<Monster> list = new ArrayList();
        list.add(monster);
        expect(maze.getListMonsters()).andReturn(list).anyTimes();
        replay(maze);

        fireBall.move(maze);
        assertFalse(monster.isAlive());
        assertTrue(fireBall.isDestroyed());
    }


}