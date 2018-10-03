package tests;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPlayer {

    private Player p;

    @BeforeEach
    public void runBefore() {
        p = new Player("test", 1,1,1);
    }

    @Test
    public void testConstructor(){
        assertEquals(1, p.getHitPoint());
        assertEquals(1, p.getDamage());
        assertEquals(1, p.getSpeed());
        assertEquals("test", p.getName());
    }

    @Test
    public void testIsPlayerAlive(){
        assertTrue(p.isAlive());
    }
}
