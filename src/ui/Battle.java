package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Battle implements ActionListener {
    private GamePath data;
    private StatusBar sb;

    private MainPlayer yourPlayer;
    private PlayerEve evePC;
    private ListOfPlayers team;
    private Monster monster;
    protected int inputAction;
    private boolean noNameFound = true;

    private JTextArea textArea;
    private JTextField userInput;
    private JPanel buttonPanel;

    private JButton submit;
    private JButton attackBtn;
    private JButton examineBtn;
    private JButton healSelfBtn;
    private JButton healEveBtn;



    public Battle(GamePath data) {
        this.data = data;

        submit = data.getSubmit();
        userInput = data.getUserInput();
        submit.setVisible(false);
        userInput.setVisible(false);
        createButtons();

        data.repaint();

        textArea = data.getTextArea();

        initializeBattle();
    }

    public void createButtons(){
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.HORIZONTAL;
        sb = new StatusBar(data);
        data.add(sb, c);

        attackBtn = new JButton("Attack");
        examineBtn = new JButton("Examine");
        healSelfBtn = new JButton("Heal Self");
        healEveBtn = new JButton("Heal Eve");

        attackBtn.setActionCommand("attack");
        examineBtn.setActionCommand("examine");
        healSelfBtn.setActionCommand("healself");
        healEveBtn.setActionCommand("healeve");

        attackBtn.addActionListener(this);
        examineBtn.addActionListener(this);
        healSelfBtn.addActionListener(this);
        healEveBtn.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.add(attackBtn);
        buttonPanel.add(examineBtn);
        buttonPanel.add(healSelfBtn);
        buttonPanel.add(healEveBtn);
        data.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(battleGoingOn()){
            if("attack".equals(e.getActionCommand())){
                textArea.setText(yourPlayer.attack(monster));
                if(!monster.isAlive()){
                    monsterDefeatText(yourPlayer);
                    battleEnd();
                }
                else
                    afterPlayerTurn();
            }
            else if("examine".equals(e.getActionCommand())){
                textArea.setText(yourPlayer.examine(monster));
                afterPlayerTurn();
            }
            else if("healself".equals(e.getActionCommand())){
                textArea.setText(yourPlayer.playerHeal(yourPlayer));
                afterPlayerTurn();
            }
            else{
                textArea.setText(yourPlayer.playerHeal(evePC));
                afterPlayerTurn();
            }
            sb.update();
        }
        else {
            battleEnd();
        }
        userInput.setText("");
    }
    //MODIFIES: monster, team
    //EFFECTS: sets up battlefield with one monster and team from GamePath
    //         sorts the team by speed
    public void initializeBattle(){
        monster = new Monster("Slime", 50,2, 2, " Gooey and green." +
                " It oozes with hostility...or something.");
        team = data.getTeamFromPath();
        yourPlayer = data.getYourPlayerFromPath();
        evePC = data.getEvePC();
        team.sortBySpeed();

        textArea.setText(" Woah! Enemy monster appeared! What will we do? ");
        textArea.append("\n Time to engage!");
        textArea.append(playerStatusText());
    }

    private String playerStatusText(){
        return ("\n " + yourPlayer.getName() + " has " + yourPlayer.getHitPoint() + " HP! ");
    }

    private void eveTurn(){
        if(evePC.isAlive()){
            if(evePC.getHitPoint() < 5){
                textArea.append("\n" + evePC.playerHeal(evePC));
            }
            else if(yourPlayer.getHitPoint() > yourPlayer.getMaxHP()/2){
                textArea.append("\n" + evePC.attack(monster));
                if(!monster.isAlive()){
                    monsterDefeatText(evePC);
                    battleEnd();
                }
            }
            else{
                textArea.append("\n" + evePC.playerHeal(yourPlayer));
            }
        }
        else{
            textArea.append("\n Eve is unconscious and cannot take action!");
        }

    }
    private void monsterDefeatText(Player currentPlayer){
        textArea.append(" \n Hurray! " + currentPlayer.getName() + " defeated the monster!");
    }

    public boolean battleGoingOn(){
        return (yourPlayer.isAlive() && monster.isAlive());
    }

    public void afterPlayerTurn(){
        eveTurn();
        if(monster.isAlive()){
            monsterTurn();
        }
        if(!yourPlayer.isAlive()){
            battleEnd();
        }
    }



    //MODIFIES: Player unlucky
    //EFFECTS: monster chooses a random player to attack
    //         if partner is dead, will attack yourPlayer
    //         note: this method only works because there is only one other player besides yourPlayer
    private void monsterTurn(){
        Player unlucky;
        if(team.areAllPlayersAlive()){
            unlucky = team.getRandomPlayer();
        }
        else{
            unlucky = yourPlayer;
        }
        textArea.append(monster.attack(unlucky));
        if(!unlucky.isAlive()){
            textArea.append("\n " + unlucky.getName() + " was knocked out!");
        }
    }

    //EFFECTS: result of the battle
    private void battleResult(){
        if(!monster.isAlive()){
            textArea.append("\n You got: " + monster.getMonsterDrop().getItemName() + "!");
            yourPlayer.getInventory().addItem(monster.getMonsterDrop());
            data.setAction(0);
        }
        else if(!yourPlayer.isAlive()){
            textArea.setText(" (Hogh...you draw your last breath at the monster's final blow...)");
            textArea.append("\n (Should've stayed in school...)");
            data.setAction(3);
        }
    }

    private void battleEnd(){
        battleResult();
        data.remove(buttonPanel);
        data.remove(sb);
        submit.setVisible(true);
        userInput.setVisible(true);
        data.setBattleGoingOn(false);
        data.repaint();
    }



}
