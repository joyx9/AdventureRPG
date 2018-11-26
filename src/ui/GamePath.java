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
    private Player evePC;
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
//        super(new GridBagLayout());
//        Font font = new Font("Sans Serif", Font.PLAIN, 18);
//        javax.swing.border.Border border = BorderFactory.createLineBorder(Color.WHITE);
//
//        textArea = new JTextArea(7, 20);
//        textArea.setFont(font);
//        textArea.setEditable(false);
//        textArea.setBackground(Color.DARK_GRAY);
//        textArea.setForeground(Color.LIGHT_GRAY);
//        textArea.setBorder(border);
//
//        userInput = new JTextField(20);
//        userInput.setFont(font);
//        userInput.setBackground(Color.DARK_GRAY);
//        userInput.setForeground(Color.LIGHT_GRAY);
//
//        submit = new JButton("Enter");
//        submit.addActionListener(this);
//
//        //Add Components to this panel.
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridwidth = GridBagConstraints.REMAINDER;
//
//        c.fill = GridBagConstraints.BOTH;
//        c.weightx = 1.0;
//        c.weighty = 1.0;
//        add(textArea, c);
//
//        c.fill = GridBagConstraints.HORIZONTAL;
//        add(userInput, c);
//        add(submit, c);

        gameIntroText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(gameIntro){
            addEve();
            yourName = userInput.getText();
            textArea.setText(" Hi, " + yourName +"! Remember, there are quite a few monsters about, " +
                    "so keep your guard up, okay?");
            textArea.append("\n (Press enter to continue)");
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
        textArea.append("\n\n (Press enter to input your command!)");
    }

    public void pathMenuText(){
        textArea.setText(" (What would you like to do?)");
        textArea.append("\n >[1] Move forward");
        textArea.append("\n >[2] Check Inventory");
        textArea.append("\n >[3] Quit");
    }



        // EFFECTS: executes choice depending on user input of an int
    public void pathChoice(int action){
        if(action == 1){
            new Battle(this);
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
        evePC = new Player("Eve", 15,3, 3);
        team = new ListOfPlayers();

//        text.getTextArea().setText(" Hello, adventurer! Welcome to your first day of exploration!");
//        text.getTextArea().append("\n My name's Eve. I'll be helping you along the way today.");
//        text.getTextArea().append("\n What's your name?");
//        text.getTextArea().append("\n\n (Press enter to input your command");
//        System.out.println("Hi " + yourName +"! Remember, there are quite a few monsters about, " +
//                "so keep your guard up, okay?");
//        yourPlayer = new MainPlayer(yourName, 10,2,2);
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





    //
//    private void Weather(){
//        try {
//            Weather weather = new Weather();
//        }
//        catch (IOException e){
//            System.out.println("Error reading weather data");
//        }
//    }
//
////    public void gameIntro(){
////        Scanner name = new Scanner(System.in);
////        evePC = new Player("Eve", 15,3, 3);
////        team = new ListOfPlayers();
////
////
////        System.out.println("Hello, adventurer! Welcome to your first day of exploration!");
////        System.out.println("My name's Eve. I'll be helping you along the way today.");
////        System.out.println("What's your name?");
////
////        String yourName = name.nextLine();
////        System.out.println("Hi " + yourName +"! Remember, there are quite a few monsters about, " +
////                "so keep your guard up, okay?");
////
////        yourPlayer = new MainPlayer(yourName, 10,2,2);
////        initializeInventory();
////        team.addPlayer(yourPlayer);
////        team.addPlayer(evePC);
////
////        try {
////            new Weather();
////        }
////        catch (IOException e){
////            System.out.println("Error reading weather data");
////        }
////    }
//
//
//
//    // EFFECTS: menu that prompts user to enter a number to select an option
////    public void pathMenu() {
////        int action = 0;
////        do {
////            System.out.println("(What would you like to do?) [1] Go right [2] Go left [3] Check Inventory [4] Quit");
////
////            Scanner choice = new Scanner(System.in);
////            action = choice.nextInt();
////
////            pathChoice(action);
////        }
////        while(action == 3);
////    }
//
//    // EFFECTS: executes choice depending on user input of an int
//    public void pathChoice(int action){
//        if(action == 1 | action == 2){
//            new Battle(this);
//        }
//        else if (action == 3) {
//            yourPlayer.getInventory().checkInventory();
//        }
//        else if (action == 4){
//            System.exit(0);
//        }
//        else{
//            text.pathMenu();
//        }
//    }
//


//    private void setText(TextBox text){
//        this.text = text;
//    }
//

}

