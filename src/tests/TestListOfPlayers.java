package tests;

import model.ListOfPlayers;
import model.Player;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestListOfPlayers {
    private ListOfPlayers team;

    @BeforeEach
    public void runBefore() {
        team = new ListOfPlayers();
    }

    @Test
    public void testAddPlayer(){
        Player a = new Player("a", 1,1,1);
        team.addPlayer(a);
        assertEquals(a, team.getPlayer(0));
    }

    @Test
    public void testSizeOfPlayerList(){
        Player a = new Player("a", 1,1,3);
        Player b = new Player("b", 1,1,2);
        Player c = new Player("c", 1,1,5);

        team.addPlayer(a);
        team.addPlayer(b);
        team.addPlayer(c);

        assertEquals(3, team.getSizeListOfPlayer());
    }

    @Test
    public void testThreePlayersSortedBySpeed(){
        Player a = new Player("a", 1,1,3);
        Player b = new Player("b", 1,1,2);
        Player c = new Player("c", 1,1,5);

        team.addPlayer(a);
        team.addPlayer(b);
        team.addPlayer(c);

        team.sortBySpeed();

        assertEquals(c, team.getPlayer(0));
        assertEquals(a, team.getPlayer(1));
        assertEquals(b, team.getPlayer(2));

    }

    @Test
    public void testThreePlayersSortedBySameSpeed(){
        Player a = new Player("a", 1,1,3);
        Player b = new Player("b", 1,1,3);
        Player c = new Player("c", 1,1,3);

        team.addPlayer(a);
        team.addPlayer(b);
        team.addPlayer(c);

        team.sortBySpeed();

        assertEquals(a, team.getPlayer(0));
        assertEquals(b, team.getPlayer(1));
        assertEquals(c, team.getPlayer(2));

    }

}
