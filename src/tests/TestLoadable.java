package tests;

import model.ListOfPlayers;

import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestLoadable {

    @Test
    public void testLoad() throws IOException {
        ListOfPlayers loadTeam = new ListOfPlayers();
        loadTeam.load("src/tests/testLoadFile");
        assertEquals(3, loadTeam.getSizeListOfPlayer());
        assertEquals("Simon", loadTeam.getPlayer(0).getName());
        assertEquals(8, loadTeam.getPlayer(1).getHitPoint());
        assertEquals(4, loadTeam.getPlayer(2).getDamage());
    }

}
