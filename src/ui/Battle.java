package ui;


import model.ListOfPlayers;
import model.Monster;
import model.Player;

import java.util.Scanner;

//question: how to access an object made in another class?

public class Battle {
    Scanner scanner = new Scanner(System.in);
    private int damage = 0;
    private int hitPoint = 0;

    //TODO: find out how to do a proper RPG style turn input of players :/
    public Battle(ListOfPlayers team) {
        Monster monster = new Monster("Slime", 20,4,2);

        Player yourPlayer = team.getPlayer(1);
        team.sortBySpeed();

        int action = 0;
        System.out.println("Enemy monster appeared! What will we do? ");
        System.out.println("Time to engage!");
        while(yourPlayer.isAlive() && monster.isAlive() && action != 3){
            //sort ListOfPlayer by speed, then input commands for each player.
            team.sortBySpeed();

            for(int i = 0; i < team.getSizeListOfPlayer(); i++) {
                Player currentPlayer = team.getPlayer(i);

                if ((currentPlayer.getName()).equals(yourPlayer.getName())) {
                    System.out.println(currentPlayer.getName() + " has "+currentPlayer.getHitPoint() +
                            " HP. " + currentPlayer.getName() + " may: [1]attack, [2]examine, or [3]flee");

                    action = scanner.nextInt();

                    if(action == 1){
                        currentPlayer.attack(currentPlayer, monster);
                        if(monster.isAlive()){
                            monster.attack(currentPlayer, monster);
                        }
                        else {
                            System.out.println("The monster was defeated.");
                            break;
                        }

                    }

                    else if(action == 2){
                        currentPlayer.examine(monster);
                        monster.attack(currentPlayer,monster);
                    }

                    else System.out.println("You ran away! Maybe another day you'll defeat it...");
                }

                else {
                    if(currentPlayer.isAlive()) {
                        System.out.println(currentPlayer.getName() + " has "+currentPlayer.getHitPoint() +
                                " HP. " + currentPlayer.getName() + " may: [1]attack, [2]heal " + yourPlayer.getName());

                        action = scanner.nextInt();

                        if(action == 1){
                            currentPlayer.attack(currentPlayer, monster);
                            if(monster.getHitPoint() > 0){
                                monster.attack(currentPlayer, monster);
                            }
                            else {
                                System.out.println("Woohoo! " + currentPlayer.getName()+ " defeated the monster!");
                                break;
                            }

                        }

                        else {
                            currentPlayer.playerHeal(currentPlayer, yourPlayer);
                            monster.attack(currentPlayer, monster);
                        }
                    }
                    if (!currentPlayer.isAlive()) {
                        System.out.println(currentPlayer.getName() + " is knocked out!");
                    }
                }

            }
        }

        if(!yourPlayer.isAlive()){
            System.out.println("(Hogh...you draw your last breath at the monster's final blow...)");
            System.out.println("(Should've stayed in school...)");
        }

    }


}
