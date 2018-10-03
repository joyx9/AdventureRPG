package tests;

import model.ListOfPlayers;
import model.Player;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestSavable {
    private ListOfPlayers team;
    private Player testP1;
    private Player testP2;

    @Test
    public void testSave() throws IOException {

        team = new ListOfPlayers();
        testP1 = new Player("Cloud",1,1,1);
        testP2 = new Player("Shulk",2,3,5);
        team.addPlayer(testP1);
        team.addPlayer(testP2);

        team.saveGame("src/tests/testSaveFile");
        List<String> lines = Files.readAllLines(Paths.get("src/tests/testSaveFile"));;

        assertEquals("Cloud 1 1 1", lines.get(0));
        assertEquals(2, lines.size());
    }


}
