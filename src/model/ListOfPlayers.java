package model;

import interfaces.Loadable;
import interfaces.Savable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ListOfPlayers implements Savable, Loadable {

    // list of all players in battle...
    // sort by speed, then for each player, input command
    List<Player> pl = new ArrayList<Player>();

    // MODIFIES: the list players
    // EFFECTS: adds a player to a ListOfPlayers
    public void addPlayer(Player p){
        pl.add(p);
    }

    // REQUIRES: a player at called index position
    // EFFECTS: returns player at index position
    public Player getPlayer(int position) {
        return pl.get(position);
    }


    // EFFECTS: returns int of how many players are in the list
    public int getSizeListOfPlayer() {
        return pl.size();
    }

    // MODIFIES: this
    // EFFECTS: sorts players in list from highest speed to lowest speed
    public void sortBySpeed(){
        Collections.sort(pl, new PlayerComparator());
    }

    public void saveGame(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");

        for(int i = 0; i < this.getSizeListOfPlayer(); i++){
            Player savePlayer = this.getPlayer(i);
            lines.add(savePlayer.getName() + " " + savePlayer.getHitPoint() + " "
                    + savePlayer.getDamage() + " " + savePlayer.getSpeed());

        }

        for(String line: lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            System.out.print("Name: "+partsOfLine.get(0) + " ");
            System.out.print("HP: "+partsOfLine.get(1) + " ");
            System.out.print("Damage: "+partsOfLine.get(2) + " ");
            System.out.println("Speed: " +partsOfLine.get(3));
            writer.println(line);
        }
        writer.close();
    }


    public void load(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));

        for (String line : lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Player loadPlayer = new Player(partsOfLine.get(0), Integer.parseInt(partsOfLine.get(1)),
                    Integer.parseInt(partsOfLine.get(2)), Integer.parseInt(partsOfLine.get(3)));
            this.addPlayer(loadPlayer);
            System.out.print("Name: "+partsOfLine.get(0) + " ");
            System.out.print("HP: "+partsOfLine.get(1) + " ");
            System.out.print("Damage: "+partsOfLine.get(2) + " ");
            System.out.println("Speed: " +partsOfLine.get(3));
        }
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
