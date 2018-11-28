package model;


import java.util.Random;

abstract public class Entity {
    private int hitPoint;
    private int damage;
    private int speed;
    private String name;
    private int maxHP;

    protected Random rm = new Random();


    public Entity(String name, int hitPoint, int damage, int speed){
        this.hitPoint = hitPoint;
        this.damage = damage;
        this.speed = speed;
        this.name = name;

        maxHP = hitPoint;
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

    public int getMaxHP(){
        return maxHP;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public boolean isAlive() {
        return this.hitPoint > 0;
    }

    // https://www.redblobgames.com/articles/probability/damage-rolls.html


}
