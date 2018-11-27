package model;

import ui.TextBox;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Inventory extends TextBox implements ActionListener{

    //EFFECTS: stores items in their specific list
    private Map<String, ArrayList<Item>> itemMap;
    private ArrayList<Item> keyItems;
    private ArrayList<Item> monsterDrops;
    private MainPlayer yourPlayer;

    JFrame inventoryFrame;

    public Inventory(){
        itemMap = new HashMap<>();

        keyItems = new ArrayList<>();
        monsterDrops = new ArrayList<>();

        keyItems.add(new Item("","","Key Items"));
        monsterDrops.add(new Item("","","Monster Drops"));

        itemMap.put("Key Items", keyItems);
        itemMap.put("Monster Drops", monsterDrops);

    }

    public void addItem(Item item){
        String category = item.getItemCategory();
        ArrayList<Item> items;

        if(!itemMap.containsKey(category)){
            // creates new category of items
            items = new ArrayList<>();
            items.add(item);
            itemMap.put(category, items);
        }
        else{
            // category already exists
            Item initial = new Item("", "", category);

            items = itemMap.get(category);
            items.add(item);
            if(isInMap(initial, category)){
                removeItem(initial);
            }
        }
    }

    public Item getItem(Item item){
        ArrayList<Item> items = itemMap.get(item.getItemCategory());
        for(Item i : items){
            if(i.equals(item)){
                return i;
            }
        }

        return null;
    }

    public void removeItem(Item item){
        ArrayList<Item> items = itemMap.get(item.getItemCategory());
        items.remove(item);
    }

    public boolean isInMap(Item item, String categoryName){
        ArrayList<Item> items = itemMap.get(categoryName);
        return items.contains(item);
    }

    public void print() {
        for (Map.Entry<String, ArrayList<Item>> i : itemMap.entrySet()) {
            ArrayList<Item> itemsInCategory = i.getValue();
            textArea.append(" " + i.getKey() + ": \n");
            for (Item item : itemsInCategory)
                textArea.append("\t " + item.getItemName() +"\n");
        }
    }

    public void setPlayer(MainPlayer yourPlayer){
        if(this != yourPlayer.getInventory()){
            this.yourPlayer = yourPlayer;
            yourPlayer.setInventory(this);
        }
    }

    public MainPlayer getPlayer(){
        return yourPlayer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textArea.setText("");
        String command = userInput.getText();
        checkInventory(command);
        userInput.setText("");
    }

    public void checkInventory() {
        inventoryFrame = new JFrame("Inventory");

        inventoryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inventoryFrame.add(this);
        inventoryFrame.setPreferredSize(new Dimension(800,300));
        inventoryFrame.pack();
        inventoryFrame.setVisible(true);
        textArea.setText(" Enter name of item category to see all items\n in that list " +
                "or name of item to check\n its description, or 'all' or 'quit'");
    }

    private void checkInventory(String command){

            if(command.equals("quit")){
                inventoryFrame.dispose();
            }
            else if(command.equals("all")){
                this.print();
            }
            else if(itemMap.containsKey(command)){
                ArrayList<Item> items = itemMap.get(command);
                textArea.setText(command + ":");
                for (Item item : items)
                    textArea.append("\t "+item.getItemName() +"\n");

            }
            else if(command.equals("")){
                textArea.setText(" Enter name of item category to see all items\n in that list " +
                        "or name of item to check\n its description, or 'all' or 'quit'");
            }
            else{
                boolean foundItem = false;
                for (Map.Entry<String, ArrayList<Item>> i : itemMap.entrySet()) {
                    ArrayList<Item> itemsInCategory = i.getValue();
                    for (Item item : itemsInCategory)
                        if(item.getItemName().equals(command)){
                            foundItem = true;
                            textArea.setText(" "+item.getDescription());
                        }
                }
                if(!foundItem){
                    textArea.setText(" Enter name of item category to see all items\n in that list " +
                            "or name of item to check\n its description, or 'all' or 'quit'");
                }
            }

    }


//    public void checkInventory(){
//        Scanner input = new Scanner(System.in);
//        String command;
//
//        boolean keepGoing = true;
//
//        while(keepGoing){
//            System.out.println("Enter name of item category to see all items in that list " +
//                    "or name of item to check its description, or 'all' or 'quit'");
//            command = input.nextLine();
//
//            if(command.equals("quit")){
//                keepGoing = false;
//            }
//            else if(command.equals("all")){
//                this.print();
//            }
//            else if(itemMap.containsKey(command)){
//                ArrayList<Item> items = itemMap.get(command);
//                System.out.println(command + ":");
//                for (Item item : items)
//                    System.out.println("\t"+item.getItemName());
//
//            }
//            else{
//                for (Map.Entry<String, ArrayList<Item>> i : itemMap.entrySet()) {
//                    ArrayList<Item> itemsInCategory = i.getValue();
//                    for (Item item : itemsInCategory)
//                        if(item.getItemName().equals(command)){
//                            System.out.println("\t" + item.getDescription());
//                        }
//                }
//            }
//
//        }
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        model.Inventory inventory = (model.Inventory) o;
        return Objects.equals(itemMap, inventory.itemMap) &&
                Objects.equals(keyItems, inventory.keyItems) &&
                Objects.equals(monsterDrops, inventory.monsterDrops) &&
                Objects.equals(yourPlayer, inventory.yourPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemMap, keyItems, monsterDrops, yourPlayer);
    }


}
