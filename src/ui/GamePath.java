package ui;

import exceptions.InvalidInputException;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePath extends TextBox implements ActionListener {

    private MainPlayer yourPlayer;
    private PlayerEve evePC;
    private ListOfPlayers team;

    private String yourName;
    private int action;

    private boolean gameIntro = true;
    private boolean battleGoingOn = false;
    private boolean isMCAlive = true;
    private boolean isEveAlive = true;


    public GamePath(){
        gameIntroText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameIntro){
            addEve();
            try{
                if(userInput.getText().matches(".*[a-zA-Z].*")){
                    yourName = userInput.getText();
                    textArea.setText(" Eve: Hi, " + yourName +"! Remember, there are quite a few monsters about, " +
                            "so keep your guard up, okay?");
                    initializeMainPlayer();
                    gameIntro = false;
                }
                else
                    throw new InvalidInputException();
            }
            catch (InvalidInputException error){
                gameIntroText();
            }
        }
        else {
            pathMenuText();
            try{
                action = Integer.parseInt(userInput.getText());
            }
            catch(NumberFormatException error){
                pathMenuText();
            }
            pathChoice(action);
        }

        userInput.setText("");
    }

    public void gameIntroText(){
        textArea.setText(" ???: Hello, adventurer! Welcome to your first day of exploration!");
        textArea.append("\n Eve: My name's Eve. I'll be helping you along the way today.");
        textArea.append("\n Eve: What's your name?");
        textArea.append("\n\n (Type your name and press next to input your command!)");
    }

    public void pathMenuText(){
        textArea.setText(" Eve: What would you like to do?");
        textArea.append("\n >[1] Move forward");
        textArea.append("\n >[2] Check Inventory");
        textArea.append("\n >[3] Quit");
        textArea.append("\n\n (Type in the desired number and then press next.)");
    }



        // EFFECTS: executes choice depending on user input of an int
    public void pathChoice(int action){
        if(action == 1){
            battleGoingOn = true;
            new Battle(this);
        }
        else if (action == 2) {
            yourPlayer.getInventory().checkInventory();
            this.action = 0;
        }
        else if (action == 3){
            System.exit(0);
        }
        else{
            this.action = 0;
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

    public void setBattleGoingOn(boolean battleGoingOn){
        this.battleGoingOn = battleGoingOn;
    }

    public boolean getBattleGoingOn(){
        return battleGoingOn;
    }

    public void setAction(int i){
        this.action = i;
    }

    public boolean isMCAlive(){
        return isMCAlive;
    }

    public void setIsMCAlive(boolean isMCAlive){
        this.isMCAlive = isMCAlive;
    }

    public boolean isEveAlive(){
        return isEveAlive;
    }

    public void setIsEveAlive(boolean isEveAlive){
        this.isEveAlive = isEveAlive;
    }





}

