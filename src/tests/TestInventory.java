package tests;

import model.Inventory;
import model.Item;
import model.MainPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestInventory {
    private Inventory testInven;
    private Item dog;

    @BeforeEach
    public void runBefore(){
        testInven = new Inventory();
        dog = new Item("Dog", "Fluffy, white, pure; a good boy", "Key Items");
    }

    @Test
    public void testAddGetItem(){
        testInven.addItem(dog);
        assertEquals(dog, testInven.getItem(dog));
    }

    @Test
    public void testIsInMapRemoveItem(){
        testInven.addItem(dog);
        assertTrue(testInven.isInMap(dog, dog.getItemCategory()));
        testInven.removeItem(dog);
        assertFalse(testInven.isInMap(dog,dog.getItemCategory()));
    }

    @Test
    public void testSetPlayer(){
        MainPlayer me = new MainPlayer("me",1,1,1);
        testInven.setPlayer(me);

        assertEquals("me", testInven.getPlayer().getName());
        assertEquals(1, testInven.getPlayer().getHitPoint());
        assertEquals(1, testInven.getPlayer().getDamage());
        assertEquals(1, testInven.getPlayer().getSpeed());

    }
}

