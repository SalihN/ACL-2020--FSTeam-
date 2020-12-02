package model.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StatsTest {
    private Stats stats;
    @Before
    public void setUp() throws Exception {
        stats = new Stats(2,2);
    }
    @After
    public void tearDown() throws Exception {
    }

    /**
     * test de la fonction de soin
     * pas de soin <= 0
     */
    @Test
    public void testHeal(){
        // heal en négatif en dessous de 0
        stats.heal(-3);
        assertTrue(stats.getHp() >= 1);
        // heal au dessus de hp max
        stats.heal(10);
        assertEquals(stats.getHpMax(),stats.getHp());
    }

    /**
     * Test de la fonction de dommage
     */
    public void testDamage(){
        //dommage >= aux points de vies
        stats.hit(stats.getHpMax() + 1);
        assertNotEquals(0,stats.getHp());
        stats.hit(-5);
        assertNotEquals(2,stats.getHp());
    }

}