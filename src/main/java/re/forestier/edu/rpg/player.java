package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public abstract class Player {

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    private Float __real_money__;
    public int maxWeight;
    public int currentWeight;


    public int level;
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<Item> inventory;  

    public Player(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        this.playerName = playerName;
        this.Avatar_name = avatarName;
        this.money = money;
        this.inventory = inventory;
    }

    public abstract void setAbilities();
    public abstract String getAvatarClass();
    public void setInventory(ArrayList<Item> inventory) {
        this.inventory = inventory;
    }


    public void removeMoney(int amount) throws IllegalArgumentException {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money = money - amount;
    }
    public void addMoney(int amount) {
        var value = Integer.valueOf(amount);
        money = money + (value != null ? value : 0);
    }
    public int retrieveLevel() {
        HashMap<Integer, Integer> levels = new HashMap<>();
        levels.put(2,10);
        levels.put(3,27);
        levels.put(4,57);
        levels.put(5,111);
        //TODO : ajouter les prochains niveaux

        if (xp < levels.get(2)) {
            return 1;
        }
        else if (xp < levels.get(3)) {return 2;
        }
        if (xp < levels.get(4)) {
            return 3;
        }
        if (xp < levels.get(5)) return 4;
        return 5;
    }

    public int getXp() {
        return this.xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    private final static Item[] objectList = {
            new Item("Lookout Ring", "Prevents surprise attacks", 2, 100),
            new Item("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 50),
            new Item("Draupnir", "Increases XP gained by 100%", 3, 300),
            new Item("Magic Charm", "Magic +10 for 5 rounds", 1, 75),
            new Item("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?", 4, 150),
            new Item("Holy Elixir", "Recover your HP", 1, 200),
    };

    public abstract HashMap<String, Integer> getAvatarLevel(int level);

    public static boolean addXp(Player player, int xp) {
        int currentLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel > currentLevel) {
            Random random = new Random();

            Item newItem = objectList[random.nextInt(objectList.length)];
            player.inventory.add(newItem);

            HashMap<String, Integer> abilities = player.getAvatarLevel(newLevel);
            abilities.forEach(player.abilities::put);

            return true;
        }
        return false;
    }


    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(Player player) {
        if (player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if (player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
            return;
        }

        if (player.currenthealthpoints < player.healthpoints / 2) {
            setHealthByClass(player);
        }
    }

    private static void setHealthByClass(Player player) {
        switch (player.getAvatarClass()) {
            case "DWARF":
                dwarHealth(player);
                break;

            case "ARCHER":
                archerHealth(player);
                break;

            case "ADVENTURER":
                adventurerHealth(player);
                break;

            default:
                break;
        }
    }

    private static void dwarHealth(Player player) {
        player.currenthealthpoints += 1;
    
        for (Item item : player.inventory) {
            if (item.getName().equals("Holy Elixir")) {
                player.currenthealthpoints += 1; 
                break;
            }
        }
    }
    

    private static void archerHealth(Player player) {
        player.currenthealthpoints += 1;
    
        for (Item item : player.inventory) {
            if (item.getName().equals("Magic Bow")) {
                player.currenthealthpoints += player.currenthealthpoints / 8 - 1;
                break; 
            }
        }
    }
    

    private static void adventurerHealth(Player player) {
        player.currenthealthpoints += 2;
        if (player.retrieveLevel() < 3) {
            player.currenthealthpoints -= 1;
        }
    }

    public boolean addItem(Item item) {
        if (currentWeight + item.getWeight() <= maxWeight) {
            inventory.add(item);                
            currentWeight += item.getWeight(); 
            System.out.println(item.getName() + " a été ajouter dans l'inventaire.");
            return true;
        } else {
            System.out.println("Vous ne pouvez pas porter plus d'objet !");
            return false;
        }
    }

}