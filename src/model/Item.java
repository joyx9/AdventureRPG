package model;

import java.util.Objects;

public class Item {

    private String itemName;
    private String description;
    private String itemCategory;

    //Constructor
    // EFFECTS: represents an Item that can be seen in player's Inventory
    public Item(String itemName, String description, String itemCategory){
        this.itemName = itemName;
        this.description = description;
        this.itemCategory = itemCategory;
    }

    public String getItemName(){
        return this.itemName;
    }

    public String getDescription(){
        return this.description;
    }

    public String getItemCategory(){
        return this.itemCategory;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemName, item.itemName) &&
                Objects.equals(description, item.description) &&
                Objects.equals(itemCategory, item.itemCategory);
    }

    @Override
    public int hashCode() {

        return Objects.hash(itemName, description, itemCategory);
    }
}
