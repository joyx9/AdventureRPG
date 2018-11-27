package model;

import ui.Battle;

import java.util.Objects;

public class MainPlayer extends Player {
    private Inventory inventory;

    public MainPlayer(String name, int hitPoint, int damage, int speed){
        super(name, hitPoint, damage, speed);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory newInventory) {
        if(this != newInventory.getPlayer()){
            this.inventory = newInventory;
            newInventory.setPlayer(this);
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model.MainPlayer that = (model.MainPlayer) o;
        return Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(inventory);
    }
}
