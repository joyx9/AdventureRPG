package model;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p2.getSpeed() - p1.getSpeed();
    }

    //https://stackoverflow.com/questions/16629190/how-to-sort-arraylist-based-on-a-variables-value
    //https://www.geeksforgeeks.org/collections-sort-java-examples/


}
