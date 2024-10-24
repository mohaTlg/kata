package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player {
    public String playerName;
    public String avatarName;
    public String avatarClass;

    public int money;
    public float realMoney; // Nommé correctement sans les underscores non conventionnels

    public int level;
    public int healthPoints;
    public int currenthealthPoints;
    public int xp;

    public Map<String, Integer> abilities;
    public List<String> inventory;

    // Liste des classes valides pour un avatar
    public static final List<String> VALID_CLASSES = List.of("ARCHER", "ADVENTURER", "DWARF");

    public Player(String playerName, String avatarName, String avatarClass, int money, List<String> inventory) {
        // Simplification de la validation de classe via une méthode statique
        if (!isValidClass(avatarClass)) {
            throw new IllegalArgumentException("Invalid avatar class: " + avatarClass);
        }

        this.playerName = playerName;
        this.avatarName = avatarName;
        this.avatarClass = avatarClass;
        this.money = money;
        this.inventory = new ArrayList<>(inventory); // Sécurisation de la liste d'inventaire
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(avatarClass).get(1);
    }

    public static boolean isValidClass(String avatarClass) {
        return VALID_CLASSES.contains(avatarClass);
    }

    public String getAvatarClass() {
        return avatarClass;
    }

    public void removeMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (money < amount) {
            throw new IllegalArgumentException("Player can't have negative money!");
        }
        money -= amount;
    }

    public void addMoney(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        money += amount; // Utilisation directe des opérateurs rapides
    }

    public int retrieveLevel() {
        // Simplification de la logique des niveaux via un tableau
        int[] xpLevels = {0, 10, 27, 57, 111}; // Chaque index correspond au niveau (index 0 pour le niveau 1)
        for (int i = xpLevels.length - 1; i >= 1; i--) {
            if (xp >= xpLevels[i]) {
                return i + 1;
            }
        }
        return 1; // Si XP est inférieur au niveau 2
    }

    public int getXp() {
        return this.xp;
    }

    public int getMoney() {
        return this.money;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
}

