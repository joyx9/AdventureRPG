package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ListOfPlayers {

    // list of all players in battle...
    // sort by speed, then for each player, input command
    private List<Player> team;
    private Random rm = new Random();

    // EFFECTS: constructs a list of players
    public ListOfPlayers(){
        team = new ArrayList<>();
    }
    // MODIFIES: this
    // EFFECTS: adds a Player to a ListOfPlayers
    public void addPlayer(Player p) {
        team.add(p);
    }

    // REQUIRES: a Player at called index position
    // EFFECTS: returns Entity at index position
    public Player getPlayer(int position) {
        return team.get(position);
    }

    // EFFECTS: gets a Player at random
    public Player getRandomPlayer(){
        int random = rm.nextInt(team.size());
        return team.get(random);
    }


    // EFFECTS: returns int of how many players are in the list
    public int getSizeListOfPlayers() {
        return team.size();
    }

    // EFFECTS: returns true if all players are alive
    public boolean areAllPlayersAlive(){
        boolean alive = true;
        for(Player p : team){
            if(!p.isAlive())
                alive = false;
        }
        return alive;
    }


    // MODIFIES: this
    // EFFECTS: sorts players in list from highest speed to lowest speed
    public void sortBySpeed() {
        Collections.sort(team, new SpeedComparator());
    }
}