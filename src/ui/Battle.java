package ui;

import exceptions.InvalidInputException;
import model.ListOfPlayers;
import model.MainPlayer;
import model.Monster;
import model.Player;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Battle {
    private GamePath data;
    private MainPlayer yourPlayer;
    private ListOfPlayers team;
    private Monster monster;
    private int inputAction;
    private boolean noNameFound = true;



    public Battle(GamePath data) {
        this.data = data;
        initializeBattle();

        while(yourPlayer.isAlive() && monster.isAlive()){
            for(int i = 0; i < team.getSizeListOfPlayers(); i++) {
                Player currentPlayer = team.getPlayer(i);
                if(currentPlayer.isAlive()){
                    do{
                        try{
                            inputPlayerAction(currentPlayer);
                        }
                        catch(InputMismatchException e) {
                            System.out.println("Huh? That wasn't one of the options...");
                        }
                    } while (invalidAction(inputAction));
                }
            }
            oneRoundBattle();
        }
        battleEnd();
    }

    //MODIFIES: monster, team
    //EFFECTS: sets up battlefield with one monster and team from GamePath
    //         sorts the team by speed
    public void initializeBattle(){
        monster = new Monster("Slime", 20,3, 2);
        monster.setAction(1);
        team = data.getTeamFromPath();
        yourPlayer = data.getYourPlayerFromPath();
        team.sortBySpeed();

        System.out.println("Enemy monster appeared! What will we do? ");
        System.out.println("Time to engage!");
    }

    //MODIFIES: currentPlayer
    //EFFECTS: user chooses what action a living player will execute by inputting available options into console
    //         if user tries to input a non existing option, an InvalidInputException is thrown
    public void inputPlayerAction(Player currentPlayer) throws InvalidInputException {
        Scanner playerInput = new Scanner(System.in);
        System.out.println(currentPlayer.getName() + " has "+currentPlayer.getHitPoint() +
                " HP. " + currentPlayer.getName() + " may: [1]attack, [2]examine, [3]heal");
        inputAction = playerInput.nextInt();

        if(invalidAction(inputAction)){
            throw new InvalidInputException();
        }
        else{
            currentPlayer.setAction(inputAction);
        }
    }

    //EFFECTS: each player in team will execute their respective action if they are alive
    private void executeTurn(){
        for(int i = 0; i < team.getSizeListOfPlayers(); i++) {
            Player currentPlayer = team.getPlayer(i);
            if(monster.isAlive() && currentPlayer.isAlive()){
                actionInTurn(currentPlayer, currentPlayer.getAction());
            }
            if(!currentPlayer.isAlive()){
                System.out.println(currentPlayer.getName() + " is unconscious!");
            }
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
        monster.attack(unlucky);
        if(!unlucky.isAlive()){
            System.out.println(unlucky.getName() + " was knocked out!");
        }
    }

    //EFFECTS: player executes action: attack, examine, or heal
    private void actionInTurn(Player currentPlayer, int action){
        if(action == 1){
            currentPlayer.attack(monster);
            if(!monster.isAlive()){
                System.out.println("Hurray! " + currentPlayer.getName() + " defeated the monster!");
            }
        }
        else if(action == 2){
            currentPlayer.examine(monster);
        }
        else if(action == 3) {
            do {
                try {
                    healAction(currentPlayer);
                } catch (InvalidInputException e) {
                    System.out.println("No one has that name!?");
                }

            }
            while(noNameFound);
        }
    }

    //EFFECTS: user selects which player will get healed by currentPlayer
    //         if user inputs a name that doesn't exist, an InvalidInputException is thrown
    private void healAction(Player currentPlayer) throws InvalidInputException {
        System.out.println("Who would " + currentPlayer.getName() + " like to heal? Enter their name:");
        Scanner healWho = new Scanner(System.in);
        String healPlayer = healWho.nextLine();
        for (int i = 0; i < team.getSizeListOfPlayers(); i++) {
            Player needsHealing = team.getPlayer(i);
            if (needsHealing.getName().equals(healPlayer)) {
                noNameFound = false;
                currentPlayer.playerHeal(needsHealing);
            }
        }
        if(noNameFound){
            throw new InvalidInputException();
        }
    }

    //EFFECTS: one round of battle
    private void oneRoundBattle(){
        executeTurn();
        if(monster.isAlive()){
            monsterTurn();
        }
    }

    //EFFECTS: result of the battle
    private void battleEnd(){
        if(!monster.isAlive()){
            System.out.println("You got: " + monster.getMonsterDrop().getItemName() + "!");
            yourPlayer.getInventory().addItem(monster.getMonsterDrop());
        }
        else if(!yourPlayer.isAlive()){
            System.out.println("(Hogh...you draw your last breath at the monster's final blow...)");
            System.out.println("(Should've stayed in school...)");
        }
    }

    //EFFECTS: if user inputs an unavailable action, returns false
    private boolean invalidAction(int input){
        return (input != 1 && input != 2 && input != 3);
    }


}
