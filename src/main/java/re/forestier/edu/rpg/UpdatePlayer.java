package re.forestier.edu.rpg;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;

public class UpdatePlayer {

    private final static String[] objectList = {
        "Lookout Ring : Prevents surprise attacks",
        "Scroll of Stupidity : INT-2 when applied to an enemy", 
        "Draupnir : Increases XP gained by 100%", 
        "Magic Charm : Magic +10 for 5 rounds", 
        "Rune Staff of Curse : May burn your ennemies... Or yourself. Who knows?", 
        "Combat Edge : Well, that's an edge", 
        "Holy Elixir : Recover your HP"
    };

    public static HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel() {
        HashMap<String, HashMap<Integer, HashMap<String, Integer>>> abilitiesPerTypeAndLevel = new HashMap<>();
    
        abilitiesPerTypeAndLevel.put("ADVENTURER", createAdventurerAbilities());
        abilitiesPerTypeAndLevel.put("ARCHER", createArcherAbilities());
        abilitiesPerTypeAndLevel.put("DWARF", createDwarfAbilities());
    
        return abilitiesPerTypeAndLevel;
    }
    
    private static HashMap<Integer, HashMap<String, Integer>> createAdventurerAbilities() {
        HashMap<Integer, HashMap<String, Integer>> adventurerMap = new HashMap<>();
        adventurerMap.put(1, createAbilitiesMap(new String[]{"INT", "DEF", "ATK", "CHA"}, new int[]{1, 1, 3, 2}));
        adventurerMap.put(2, createAbilitiesMap(new String[]{"INT", "CHA"}, new int[]{2, 3}));
        adventurerMap.put(3, createAbilitiesMap(new String[]{"ATK", "ALC"}, new int[]{5, 1}));
        adventurerMap.put(4, createAbilitiesMap(new String[]{"DEF"}, new int[]{3}));
        adventurerMap.put(5, createAbilitiesMap(new String[]{"VIS", "DEF"}, new int[]{1, 4}));
        return adventurerMap;
    }
    
    private static HashMap<Integer, HashMap<String, Integer>> createArcherAbilities() {
        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();
        archerMap.put(1, createAbilitiesMap(new String[]{"INT", "ATK", "CHA", "VIS"}, new int[]{1, 3, 1, 3}));
        archerMap.put(2, createAbilitiesMap(new String[]{"DEF", "CHA"}, new int[]{1, 2}));
        archerMap.put(3, createAbilitiesMap(new String[]{"ATK"}, new int[]{3}));
        archerMap.put(4, createAbilitiesMap(new String[]{"DEF"}, new int[]{2}));
        archerMap.put(5, createAbilitiesMap(new String[]{"ATK"}, new int[]{4}));
        return archerMap;
    }
    
    private static HashMap<Integer, HashMap<String, Integer>> createDwarfAbilities() {
        HashMap<Integer, HashMap<String, Integer>> dwarfMap = new HashMap<>();
        dwarfMap.put(1, createAbilitiesMap(new String[]{"ALC", "INT", "ATK"}, new int[]{4, 1, 3}));
        dwarfMap.put(2, createAbilitiesMap(new String[]{"DEF", "ALC"}, new int[]{1, 5}));
        dwarfMap.put(3, createAbilitiesMap(new String[]{"ATK"}, new int[]{4}));
        dwarfMap.put(4, createAbilitiesMap(new String[]{"DEF"}, new int[]{2}));
        dwarfMap.put(5, createAbilitiesMap(new String[]{"CHA"}, new int[]{1}));
        return dwarfMap;
    }
    
    private static HashMap<String, Integer> createAbilitiesMap(String[] abilities, int[] values) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < abilities.length; i++) {
            map.put(abilities[i], values[i]);
        }
        return map;
    }
    

    public static boolean addXp(player player, int xp) {
        int previousLevel = player.retrieveLevel();
        player.xp += xp;
        int newLevel = player.retrieveLevel();

        if (newLevel > previousLevel) { // Player leveled-up!

            // Give a random object
            Random random = new Random();
            player.inventory.add(objectList[random.nextInt(objectList.length)]);

            // Add/upgrade abilities to player
            Map<String, Integer> abilities = abilitiesPerTypeAndLevel().get(player.getAvatarClass()).get(newLevel);
            player.abilities.putAll(abilities);
            
            return true;
        }
        return false;
    }

    // majFinDeTour met à jour les points de vie
    public static void majFinDeTour(player player) {
        if(player.currenthealthpoints == 0) {
            System.out.println("Le joueur est KO !");
            return;
        }

        if(player.currenthealthpoints < player.healthpoints/2) {
            if(!player.getAvatarClass().equals("ADVENTURER")) {
                if(player.getAvatarClass().equals("DWARF")) {
                    if(player.inventory.contains("Holy Elixir")) {
                        player.currenthealthpoints+=1;
                    }
                    player.currenthealthpoints+=1;
                } else if(player.getAvatarClass().equals("ADVENTURER")) {
                    player.currenthealthpoints+=2;
                }


                if(player.getAvatarClass().equals("ARCHER")) {
                    player.currenthealthpoints+=1;
                    if(player.inventory.contains("Magic Bow")) {
                        player.currenthealthpoints+=player.currenthealthpoints/8-1;
                    }
                }
            } else {
                player.currenthealthpoints+=2;
                if(player.retrieveLevel() < 3) {
                    player.currenthealthpoints-=1;
                }
            }
        } else if(player.currenthealthpoints >= player.healthpoints/2){
            if(player.currenthealthpoints >= player.healthpoints) {
                player.currenthealthpoints = player.healthpoints;
                return;
            }
        }


        if(player.currenthealthpoints >= player.healthpoints) {
            player.currenthealthpoints = player.healthpoints;
        }
    }
}