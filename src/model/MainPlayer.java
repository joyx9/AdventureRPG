package model;

public class MainPlayer extends Player {
    private Inventory inventory;

    public MainPlayer(String name, int hitPoint, int damage, int speed){
        super(name, hitPoint, damage, speed);
        inventory = new Inventory();
    }

    public Inventory getInventory(){
        return inventory;
    }
}
