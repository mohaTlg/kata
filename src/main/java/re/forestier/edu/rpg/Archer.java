package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Archer extends Player {

    public Archer(String playerName, String avatarName, int money, ArrayList<Item> inventory) {
        super(playerName, avatarName, money, inventory);
        setAbilities();
    }

    @Override
    public void setAbilities() {
        this.abilities = abilitiesPerTypeAndLevel().get(1);
    }

    // A supprimer vu que c'est pas utilisé (a ne pas oublier)
    // private final static Element[] objectList = {
    //         new Element("Lookout Ring", "Prevents surprise attacks", 1, 50),
    //         new Element("Scroll of Stupidity", "INT-2 when applied to an enemy", 1, 30),
    //         new Element("Draupnir", "Increases XP gained by 100%", 3, 200),
    //         new Element("Magic Charm", "Magic +10 for 5 rounds", 2, 100),
    //         new Element("Rune Staff of Curse", "May burn your enemies... Or yourself. Who knows?", 5, 150),
    //         new Element("Combat Edge", "Well, that's an edge", 2, 75),
    //         new Element("Holy Elixir", "Recover your HP", 1, 25)
    // };

    @Override
    public String getAvatarClass() {
        return "ARCHER";
    }

    public HashMap<String, Integer> getAvatarLevel(int level){
        return abilitiesPerTypeAndLevel().get(level);
    }

    public static HashMap<Integer, HashMap<String, Integer>> abilitiesPerTypeAndLevel() {
        HashMap<Integer, HashMap<String, Integer>> archerMap = new HashMap<>();

        Object[][] levels = {
                {1, new String[]{"INT", "ATK", "CHA", "VIS"}, new int[]{1, 3, 1, 3}},
                {2, new String[]{"DEF", "CHA"}, new int[]{1, 2}},
                {3, new String[]{"ATK"}, new int[]{3}},
                {4, new String[]{"DEF"}, new int[]{2}},
                {5, new String[]{"ATK"}, new int[]{4}}
        };

        for (Object[] levelData : levels) {
            int level = (int) levelData[0];
            String[] abilities = (String[]) levelData[1];
            int[] values = (int[]) levelData[2];

            HashMap<String, Integer> levelAbilities = new HashMap<>();
            for (int i = 0; i < abilities.length; i++) {
                levelAbilities.put(abilities[i], values[i]);
            }
            archerMap.put(level, levelAbilities);
        }

        return archerMap;
    }

}
