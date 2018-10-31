package ui;

import model.Inventory;
import model.Item;
import model.ListOfPlayers;
import model.Player;
import ui.exceptions.HPOutOfBoundsException;

import java.io.*;

import java.util.Scanner;

public class GamePath{
    Scanner scannerInt = new Scanner(System.in);
    Scanner scannerStr = new Scanner(System.in);

    private ListOfPlayers team;
    private Player yourPlayer;
    private String yourName;
    private int move = 0;
    private int confirm;
    private Inventory inventory = new Inventory();

    public GamePath() throws IOException, HPOutOfBoundsException {
        team = new ListOfPlayers();

        System.out.println("[1] New Game [2] Load Game");
        initialInventory();

        confirm = scannerInt.nextInt();
        if(confirm == 1) {
            //instantiating companion player
            Player friendEve = new Player("Eve", 15,3,3);
            team.addPlayer(friendEve);

            System.out.println("???: Hello, adventurer! Welcome to your first day of exploration!");
            System.out.println("Eve: My name's Eve. I'll be helping you along the way today.");
            System.out.println("Eve: What's your name?");

            yourName = scannerStr.nextLine();

            yourPlayer = new Player(yourName, 10,2,2);
            team.addPlayer(yourPlayer);

            System.out.println("Eve: Hi " + yourName +"! Remember, there are quite a few monsters about, " +
                    "so keep your guard up, okay?");
        }

        else {
            team.load("src/saveFile");
            yourPlayer = team.getPlayer(1);
            System.out.println("Eve: Shall we go?");
        }


        while(move == 0 || move == 3 || move == 4){
            System.out.println("(What would you like to do?) [1] Go right [2] Go left [3] Check Inventory [4] Save" );
            move = scannerInt.nextInt();
            if(move == 1 || move == 2) {
                new Battle(team, this);
            }
            else if(move == 3){
                if (inventory != null){
                    inventory.checkInventory();
                }
            }
            else{
                team.saveGame( "src/saveFile");
            }
        }

        //TODO: add some cool events lol
        // for now we will just have one battle


        if(yourPlayer.isAlive()) {
            System.out.println("Eve: Nice work! Let's keep going.");
        }
        else {
            System.out.println("Eve: Oops. Let's try that again.");
        }

        System.out.println("(What would you like to do?) [1] Save [2] Check Inventory [3] End");
        confirm = scannerInt.nextInt();
        if(confirm == 1) {
            team.saveGame("src/saveFile");
        }
        else if(confirm == 2){
            inventory.checkInventory();
        }



    }


    public Inventory getInventory(){
        return inventory;
    }

    public void initialInventory(){
        inventory.addItem(new Item("Chipped Sword",
                                    "Bought at a bargain sale.", "Key Items"));
        inventory.addItem(new Item("Leather Armor",
                                    "It's seen better days.", "Key Items"));
        inventory.addItem(new Item("Random Key",
                                    "You never know if you might need it!", "Key Items"));
    }

    //TODO
    //this is the class that represents the "world" where player is exploring.
    //make methods to create branching paths of the game...?
    //ex. present situations and make player choose between two decisions

}
