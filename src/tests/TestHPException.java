package tests;

import model.Player;
import org.junit.jupiter.api.Test;
import ui.exceptions.HPOutOfBoundsException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class TestHPException {

    @Test
    public void testHPException(){
        Player p1 = new Player("Jester", 10,8,4);
        Player p2 = new Player("Fjord", 15,6,6);
        int heal = 5;

        try{
            p1.playerHeal(p2);
            fail("should not pass");
        }
        catch (HPOutOfBoundsException e){
            System.out.println(p2.getName() + " was healed for " +
                    Integer.toString(p2.getMaxHP() - p2.getHitPoint()) +
                    " points!");
            p2.setHitPoint(p2.getMaxHP());
        }
        assertEquals(p2.getMaxHP(), p2.getHitPoint());

    }

    public void testHPExceptionNotThrown(){
        Player p1 = new Player("Jester", 10,8,4);
        Player p2 = new Player("Fjord", 15,6,6);
        int heal = 4;

        p2.setHitPoint(10);

        try{
            p1.playerHeal(p2);
        }
        catch(HPOutOfBoundsException e){
            fail("should not pass");
        }

        assertEquals(14,p2.getHitPoint());
    }
}
