package model;

import ui.exceptions.HPOutOfBoundsException;

public class Player extends Entity{

    // REQUIRES: all int values > 0
    // EFFECTS: Constructs a player character with given HP, damage, speed, and name
    public Player(String name, int hitPoint, int damage, int speed) {
        super(name,hitPoint,damage,speed);
    }



    // MODIFIES: m
    // EFFECTS: monsters hp lowers by value of player's attack
    //          player attack is their base damage + 1d6
    @Override
    public void attack(Player p, Monster m) {
        //generate random damage for attack
        int damage = p.getDamage() + 1 + rm.nextInt(6);
        m.setHitPoint(m.getHitPoint()-damage);
        System.out.println(p.getName()+" attacked the monster! Dealt " + damage +" damage!");
    }

    // MODIFIES: healed
    // EFFECTS: healer player increases hp of healed player by heal value if healed player's HP does not go over maxHP
    //          otherwise, throws HPOutOfBoundsException
    //          heal value is healer's base damage + 1d4
    public void playerHeal(Player healed) throws HPOutOfBoundsException {
        int heal = this.getDamage() + 1 + rm.nextInt(4);

        if(healed.getHitPoint() + heal > healed.getMaxHP()){
            throw new HPOutOfBoundsException();
        }
        healed.setHitPoint(healed.getHitPoint() + heal);
        System.out.println(healed.getName() +" was healed for " + heal + " points!");


    }

    // EFFECTS: returns monster's hp and name in dialogue
    public void examine(Monster m) {
        System.out.println("You examine the monster.");
        System.out.println("It's a " + m.getName() + "! It appears to have " + m.getHitPoint() + " HP left.");
    }

    // EFFECTS: returns true if player's hitPoint is above 0 and is therefore alive


    //TODO: level up system?
}
