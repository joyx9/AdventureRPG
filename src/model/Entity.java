package model;

import java.util.Random;

abstract public class Entity {
    protected int hitPoint;
    protected int damage;
    protected int speed;
    protected String name;
    Random rm = new Random();


    public Entity(String name, int hitPoint, int damage, int speed){
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean isAlive() {
        if(this.hitPoint > 0) {
            return true;
        }
        return false;
    }

    abstract public void attack(Player p, Monster m);
    // https://www.redblobgames.com/articles/probability/damage-rolls.html


}
