package ui;

import exceptions.InvalidInputException;
import model.*;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePath extends TextBox implements ActionListener {

    private MainPlayer yourPlayer;
    private PlayerEve evePC;
    private ListOfPlayers team;

//    protected JTextArea textArea;
//    protected JTextField userInput;
//    protected JButton submit;
    private String yourName;
    private int action;

    private boolean gameIntro = true;
    private boolean onPathMenu = true;
    private boolean inventoryMenu = true;

    public GamePath(){
        gameIntroText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameIntro){
            addEve();
            yourName = userInput.getText();
            textArea.setText(" Hi, " + yourName +"! Remember, there are quite a few monsters about, " +
                    "so keep your guard up, okay?");
            initializeMainPlayer();
            gameIntro = false;
        }
        else if(onPathMenu){
            pathMenuText();
            try{
                action = Integer.parseInt(userInput.getText());
            }
            catch(NumberFormatException error){
                pathMenuText();
            }
            onPathMenu = false;
            pathChoice(action);
        }
        else if(inventoryMenu){
            action = 0;
        }
        userInput.setText("");
    }

    public void gameIntroText(){
        textArea.setText(" Hello, adventurer! Welcome to your first day of exploration!");
        textArea.append("\n My name's Eve. I'll be helping you along the way today.");
        textArea.append("\n What's your name?");
        textArea.append("\n\n (Type your name and press next to input your command!)");
    }

    public void pathMenuText(){
        textArea.setText(" (What would you like to do?)");
        textArea.append("\n >[1] Move forward");
        textArea.append("\n >[2] Check Inventory");
        textArea.append("\n >[3] Quit");
        textArea.append("\n\n (Type in the desired number and then press next.)");
    }



        // EFFECTS: executes choice depending on user input of an int
    public void pathChoice(int action){
        if(action == 1){
            new Battle(this);
            onPathMenu = true;
            this.action = 0;
        }
        else if (action == 2) {
            onPathMenu = true;
            yourPlayer.getInventory().checkInventory();
        }
        else if (action == 3){
            System.exit(0);
        }
        else{
            onPathMenu = true;
        }
    }

    // EFFECTS: adds Eve to the game
    public void addEve(){
        evePC = new PlayerEve("Eve", 15,3, 3);
        team = new ListOfPlayers();
        team.addPlayer(evePC);
    }

    public void initializeMainPlayer(){
        yourPlayer = new MainPlayer(yourName, 10,2,2);
        team.addPlayer(yourPlayer);
        initializeInventory();
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

    public PlayerEve getEvePC() {return evePC; }

    public JButton getSubmit(){ return submit;}
}

