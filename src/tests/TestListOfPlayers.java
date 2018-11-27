package tests;

import model.ListOfPlayers;
import model.MainPlayer;
import model.Player;
import model.PlayerEve;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestListOfPlayers {
    private ListOfPlayers team;

    @BeforeEach
    public void runBefore() {
        team = new ListOfPlayers();
    }

    @Test
    public void testAddPlayer(){
        Player a = new Player("a", 1,1,1);
        Player m = new Player("m",1,1,1);
        team.addPlayer(a);
        team.addPlayer(m);
        assertEquals(a, team.getPlayer(0));
        assertEquals(m, team.getPlayer(1));
    }

    @Test
    public void testSizeOfPlayerList(){
        MainPlayer a = new MainPlayer("a", 1,1,3);
        PlayerEve b = new PlayerEve("b", 1,1,2);
        Player c = new Player("c", 1,1,5);

        team.addPlayer(a);
        team.addPlayer(b);
        team.addPlayer(c);

        assertEquals(3, team.getSizeListOfPlayers());
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

    @Test
    public void testGetMainPlayer(){
        MainPlayer me = new MainPlayer("me",1,1,1);
        team.addPlayer(me);

        team.getPlayer(0);
        assertEquals(me, team.getPlayer(0));
    }

    @Test
    public void testGetRandomPlayer(){
        Player a = new Player("a",1,1,1);
        Player b = new Player("b", 1,1,3);

        team.addPlayer(a);
        team.addPlayer(b);

        System.out.println(team.getRandomPlayer().getName());

    }

    @Test
    public void testAreAllPlayersAlive(){
        Player a = new Player("a",1,1,1);
        Player b = new Player("b", 1,1,3);

        team.addPlayer(a);
        team.addPlayer(b);

        assertTrue(team.areAllPlayersAlive());

        b.setHitPoint(0);

        assertFalse(team.areAllPlayersAlive());
    }

}
