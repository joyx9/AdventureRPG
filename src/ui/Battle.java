package ui;


import model.ListOfPlayers;
import model.Monster;
import model.Player;

import java.util.Random;
import java.util.Scanner;

//question: how to access an object made in another class?

public class Battle {
    Scanner scanner = new Scanner(System.in);
    Random rm = new Random();
    private int damage = 0;
    private int hitPoint = 0;

    //TODO: find out how to do a proper RPG style turn input of players :/
    public Battle(ListOfPlayers team) {
        Monster monster = new Monster();

        Player yourPlayer = team.getPlayer(1);
        team.sortBySpeed();

        int action = 0;
        System.out.println("Enemy monster appeared! What will we do? ");
        System.out.println("Time to engage!");
        while(yourPlayer.isAlive() && monster.hitPoint > 0 && action != 3){
            //sort ListOfPlayer by speed, then input commands for each player.
            team.sortBySpeed();

            for(int i = 0; i < team.getSizeListOfPlayer(); i++) {
                Player currentPlayer = team.getPlayer(i);

                if ((currentPlayer.getName()).equals(yourPlayer.getName())) {
                    System.out.println(currentPlayer.getName() + " has "+currentPlayer.getHitPoint() +
                            " HP. " + currentPlayer.getName() + " may: [1]attack, [2]examine, or [3]flee");

                    action = scanner.nextInt();

                    if(action == 1){
                        playerAttack(currentPlayer, monster);
                        if(monster.hitPoint > 0){
                            monsterAttack(currentPlayer, monster);
                        }
                        else {
                            System.out.println("The monster was defeated.");
                            break;
                        }

                    }

                    else if(action == 2){
                        int monsterHP = examine(monster);
                        System.out.println("It appears to have " +monsterHP+ " HP.");
                        monsterAttack(currentPlayer,monster);
                    }

                    else System.out.println("You ran away! Maybe another day you'll defeat it...");
                }

                else {
                    if(currentPlayer.isAlive()) {
                        System.out.println(currentPlayer.getName() + " has "+currentPlayer.getHitPoint() +
                                " HP. " + currentPlayer.getName() + " may: [1]attack, [2]heal " + yourPlayer.getName());

                        action = scanner.nextInt();

                        if(action == 1){
                            playerAttack(currentPlayer, monster);
                            if(monster.hitPoint > 0){
                                monsterAttack(currentPlayer, monster);
                            }
                            else {
                                System.out.println("Woohoo! " + currentPlayer.getName()+ " defeated the monster!");
                                break;
                            }

                        }

                        else {
                            playerHeal(currentPlayer, yourPlayer);
                            monsterAttack(currentPlayer, monster);
                        }
                    }
                    if (!currentPlayer.isAlive()) {
                        System.out.println(currentPlayer.getName() + " is knocked out!");
                    }
                }

            }
        }

        if(yourPlayer.getHitPoint() <= 0){
            System.out.println("(Hogh...you draw your last breath at the monster's final blow...)");
            System.out.println("(Should've stayed in school...)");
        }

    }

    // MODIFIES: m
    // EFFECTS: monsters hp lowers by value of player's attack
    //              player attack is their base damage + 1d6
    private void playerAttack(Player p, Monster m) {
        //generate random damage for attack
        this.damage = p.getDamage() + 1 + rm.nextInt(6);
        m.hitPoint -= this.damage;
        System.out.println(p.getName()+" attacked the monster! Dealt " + this.damage +" damage!");
    }

    // MODIFIES: p
    // EFFECTS: player hp lowers by value of monster's attack
    //              monster attack is their base damage + 1d4
    private void monsterAttack(Player p, Monster m) {
        this.damage = m.damage + 1 + rm.nextInt(4);
        this.hitPoint = p.getHitPoint();
        this.hitPoint -= this.damage;
        p.setHitPoint(this.hitPoint);

        System.out.println("Monster attacked " + p.getName() + " for " + this.damage + " damage!");
    }

    // MODIFIES: healed
    // EFFECTS: healer player increases hp of healed player by heal value
    //              heal value is healer's base damage + 1d4
    private void playerHeal (Player healer, Player healed) {
        int heal = healer.getDamage() + 1 + rm.nextInt(4);
        healed.setHitPoint(healed.getHitPoint() + heal);

        System.out.println(healed.getName() +" was healed for " + heal + " points!");
    }

    // EFFECTS: returns monster's hp
    private int examine(Monster m){
        System.out.println("You examine the monster.");
        return m.hitPoint;
    }


    // CODE SOURCES:
    // https://www.redblobgames.com/articles/probability/damage-rolls.html
    // https://www.geeksforgeeks.org/comparator-interface-java/
    // https://www.geeksforgeeks.org/collections-sort-java-examples/
    // https://stackoverflow.com/questions/44954585/why-cant-i-use-foreach-on-my-class
}
