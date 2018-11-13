package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    //EFFECTS: stores items in their specific list
    private Map<String, ArrayList<Item>> itemMap;
    private ArrayList<Item> keyItems;
    private ArrayList<Item> monsterDrops;
    private MainPlayer yourPlayer;

    public Inventory(){
        itemMap = new HashMap<String, ArrayList<Item>>();

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
            items = new ArrayList<Item>();
            items.add(item);
            itemMap.put(category, items);
        }
        else{
            Item initial = new Item("", "", category);

            items = itemMap.get(category);
            items.add(item);
            if(isInMap(initial, category)){
                items.remove(initial);
            }

            // category already exists
        }
    }

    public boolean isInMap(Item item, String categoryName){
        ArrayList<Item> items = itemMap.get(categoryName);
        return items.contains(item);
    }

    public void print() {
        for (Map.Entry<String, ArrayList<Item>> i : itemMap.entrySet()) {
            ArrayList<Item> itemsInCategory = i.getValue();
            for (Item item : itemsInCategory)
                System.out.println(i.getKey() + ": " + item.getItemName());
        }
    }

    public void setInventory(){
        if(yourPlayer.a)
    }

    public void checkInventory(){
        Scanner input = new Scanner(System.in);
        String command = null;

        boolean keepGoing = true;

        while(keepGoing){
            System.out.println("Enter name of item category to see all items in that list " +
                                "or name of item to check its description, or 'all' or 'quit'");
            command = input.nextLine();

            if(command.equals("quit")){
                keepGoing = false;
            }
            else if(command.equals("all")){
                this.print();
            }
            else if(itemMap.containsKey(command)){
                ArrayList<Item> items = itemMap.get(command);
                System.out.println(command + ":");
                for (Item item : items)
                    System.out.println("\t"+item.getItemName());

            }
            else{
                for (Map.Entry<String, ArrayList<Item>> i : itemMap.entrySet()) {
                    ArrayList<Item> itemsInCategory = i.getValue();
                    for (Item item : itemsInCategory)
                        if(item.getItemName().equals(command)){
                            System.out.println("\t" + item.getDescription());
                        }
                }
            }

        }
    }
}
