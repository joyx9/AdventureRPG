package ui;

import model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePath {
    private MainPlayer yourPlayer;
    private Player evePC;
    private ListOfPlayers team;


    public GamePath(){
        gameIntro();
        while(yourPlayer.isAlive()){
            try {
                pathMenu();
            }
            catch (InputMismatchException e){
                pathMenu();
            }
        }
    }

    // EFFECTS: start of the Game
    public void gameIntro(){
        Scanner name = new Scanner(System.in);
        evePC = new Player("Eve", 15,3, 3);
        team = new ListOfPlayers();

        System.out.println("???: Hello, adventurer! Welcome to your first day of exploration!");
        System.out.println("Eve: My name's Eve. I'll be helping you along the way today.");
        System.out.println("Eve: What's your name?");

        String yourName = name.nextLine();
        System.out.println("Eve: Hi " + yourName +"! Remember, there are quite a few monsters about, " +
                "so keep your guard up, okay?");

        yourPlayer = new MainPlayer(yourName, 10,2,2);
        initializeInventory();
        team.addPlayer(yourPlayer);
        team.addPlayer(evePC);
    }



    // EFFECTS: menu that prompts user to enter a number to select an option
    public void pathMenu() {
        int action = 0;
        do {
            System.out.println("(What would you like to do?) [1] Go right [2] Go left [3] Check Inventory [4] Quit");

            Scanner choice = new Scanner(System.in);
            action = choice.nextInt();

            pathChoice(action);
        }
        while(action == 3);
    }

    // EFFECTS: executes choice depending on user input of an int
    public void pathChoice(int action){
        if(action == 1 | action == 2){
            new Battle(this);
        }
        else if (action == 3) {
            yourPlayer.getInventory().checkInventory();
        }
        else if (action == 4){
            System.exit(0);
        }
        else{
            pathMenu();
        }
    }

    // EFFECTS: your beginning inventory
    public void initializeInventory(){
        Inventory inventory = new Inventory();
        inventory.addItem(new Item("Chipped Sword",
                "Bought at a bargain sale.", "Key Items"));
        inventory.addItem(new Item("Leather Armor",
                "It's seen better days.", "Key Items"));
        inventory.addItem(new Item("Random Key",
                "You never know if you might need it!", "Key Items"));

        yourPlayer.setInventory(inventory);
    }

    // EFFECTS: returns team that exists in the game
    public ListOfPlayers getTeamFromPath(){
        return team;
    }

    // EFFECTS: returns yourPlayer that exists in the game
    public MainPlayer getYourPlayerFromPath(){
        return yourPlayer;
    }
}

