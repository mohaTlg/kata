package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.Player;
import re.forestier.edu.rpg.Goblin;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.HashMap;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        Adventurer player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test
    @DisplayName("Test addMoney")
    void testAddMoney() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.addMoney(45);
        assertEquals(145, p.money);
    }

    @Test
    @DisplayName("Test removeMoney")
    void testRemoveMoney() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        
        p.removeMoney(45);
        
        assertEquals(55, p.money);
    }

    @Test
    @DisplayName("Test removeMoney with invalid value")
    void testRemoveMoneyInvalid() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(150));
    }

    @Test
    @DisplayName("Test addXp and retrieveLevel")
    void testAddXpAndRetrieveLevel() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        
        Player.addXp(p, 5);
        assertEquals(1, p.retrieveLevel());
        
        Player.addXp(p, 20);
        assertEquals(2, p.retrieveLevel());

        Player.addXp(p, 10);
        assertEquals(3, p.retrieveLevel());

        Player.addXp(p, 45);
        assertEquals(4, p.retrieveLevel());
        
        Player.addXp(p, 120);
        assertEquals(5, p.retrieveLevel());
    }

    @Test
    @DisplayName("Test addXp and retrieveLevel for the mutations")
    void testAddXpAndRetrieveLevelMutation() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        
        Player.addXp(p, 10);
        assertEquals(2, p.retrieveLevel());

        Player.addXp(p, 17);
        assertEquals(3, p.retrieveLevel());

        Player.addXp(p, 30);
        assertEquals(4, p.retrieveLevel());

        Player.addXp(p, 54);
        assertEquals(5, p.retrieveLevel());
        
        
    }



    @Test
    @DisplayName("Test avatar class validation")
    void testAvatarClassValidation() {
        // Classe d'avatar non valide (a effacer après verification)
        // playerOld invalidPlayer = new playerOld("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>());
        // assertNull(invalidPlayer.getAvatarClass());

        // Classe d'avatar valide
        Archer validPlayer = new Archer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertEquals("ARCHER", validPlayer.getAvatarClass());
    }

    @Test
    @DisplayName("Test getXp")
    void testGetXp() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertEquals(0, p.getXp());
        
        // Getion d'un test de mutation
        Player.addXp(p, 45);
        assertEquals(45, p.getXp());
    }

    //TESt update player
    @Test
    @DisplayName("testPlayerKO") 
    void testPlayerKO(){
         Adventurer player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 0;
        Player.majFinDeTour(player);
        assertEquals(0, player.currenthealthpoints);
    }

    @Test
    @DisplayName("test health below half (adventurer)")
    void testHealthBelowHalfAdventurer(){
        Adventurer player = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        Player.addXp(player, 20);
        Player.majFinDeTour(player);
        assertEquals(41, player.currenthealthpoints);
    }
    
    @Test
    // @DisplayName("test health below half adventurer with elixir")
    void testHealthBelowHalfdwarfWithElixir(){
        
        Dwarf player = new Dwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        
        Item holyElixir = new Item("Holy Elixir", "Recover your HP", 2, 100);
        player.inventory.add(holyElixir);
        Player.majFinDeTour(player);
        assertEquals(42, player.currenthealthpoints);
    }
        
       
    @Test
    // @DisplayName("test health below half archer with magic bow")
    void testHealthBelowHalfarcherWithmagic(){
        Archer player = new Archer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        Item item = new Item("Magic Bow", "Prevents surprise attacks", 2, 100);
        player.inventory.add(item);
        Player.majFinDeTour(player);
        assertEquals(45, player.currenthealthpoints);
    }

    @Test
    // @DisplayName("test health above half point")
    void testHealthAboveHalf(){
        Dwarf player = new Dwarf("Florian", "Grognak le barbare", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 100;
        Player.majFinDeTour(player);
        assertEquals(100, player.currenthealthpoints);
    }

    @Test
    @DisplayName("return false if want add 0 xp")
    void testAddZeroXp(){
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        assertFalse(Player.addXp(p, 0));
    }
    // test pour la mise à jour concernant Goblin
   @Test
    @DisplayName("Test avatar class validation for Goblin")
    void testAvatarClassValidationGoblin() {
        Goblin goblinPlayer = new Goblin("Goblin", "Goblin Avatar", 100, new ArrayList<>());
        assertEquals("GOBLIN", goblinPlayer.getAvatarClass());
    }
        @Test
    @DisplayName("Test get avatar abilities for Goblin at level 1")
    void testGoblinAbilitiesLevel1() {
        Goblin goblinPlayer = new Goblin("Goblin", "Goblin Avatar", 100, new ArrayList<>());
        HashMap<String, Integer> levelAbilities = goblinPlayer.getAvatarLevel(1);

        assertNotNull(levelAbilities);
        assertTrue(levelAbilities.containsKey("INT"));
        assertTrue(levelAbilities.containsKey("ATK"));
        assertTrue(levelAbilities.containsKey("ALC"));

        assertEquals(2, levelAbilities.get("INT"));
        assertEquals(2, levelAbilities.get("ATK"));
        assertEquals(1, levelAbilities.get("ALC"));
    }
        @Test
    @DisplayName("Test Goblin abilities at different levels")
    void testGoblinAbilitiesAtDifferentLevels() {
        Goblin goblinPlayer = new Goblin("Goblin", "Goblin Avatar", 100, new ArrayList<>());
        
        // Test concernant le niveau 1
        HashMap<String, Integer> level1Abilities = goblinPlayer.getAvatarLevel(1);
        assertEquals(2, level1Abilities.get("INT"));
        assertEquals(2, level1Abilities.get("ATK"));
        assertEquals(1, level1Abilities.get("ALC"));

        // Test concernant le niveau 2
        HashMap<String, Integer> level2Abilities = goblinPlayer.getAvatarLevel(2);
        assertEquals(3, level2Abilities.get("ATK"));
        assertEquals(4, level2Abilities.get("ALC"));
    }
    // test goblinhealth
    @Test
    @DisplayName("Test goblin health update when current health is below half")
    void testGoblinHealthUpdate() {
        Goblin goblinPlayer = new Goblin("Goblin", "Goblin Avatar", 100, new ArrayList<>());
        goblinPlayer.healthpoints = 100;
        goblinPlayer.currenthealthpoints = 40;  // 

        Player.majFinDeTour(goblinPlayer);

        assertEquals(43, goblinPlayer.currenthealthpoints); //
    }
    @Test
    @DisplayName("Test goblin health update when level is 4")
    void testGoblinHealthLevel4() {
        Goblin goblinPlayer = new Goblin("Goblin", "Goblin Avatar", 100, new ArrayList<>());
        goblinPlayer.healthpoints = 100;
        goblinPlayer.currenthealthpoints = 40;
        goblinPlayer.setXp (150); //

        Player.majFinDeTour(goblinPlayer);

        assertEquals(44, goblinPlayer.currenthealthpoints); // 
    }
        // test Pour l'ajout des objets  
    @Test
    @DisplayName("Test addItem with valid item")
    void testAddItemValid() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.maxWeight = 10;  // Max weight the player can carry
        p.currentWeight = 0; // No items yet

        Item item = new Item("Sword", "A sharp blade", 5, 50);
        boolean result = p.addItem(item);

        assertTrue(result); // Item should be added successfully
        assertEquals(5, p.currentWeight); // Current weight should be updated
        assertTrue(p.inventory.contains(item));
}
    @Test
    @DisplayName("Test addItem with exceeding weight")
    void testAddItemExceedingWeight() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.maxWeight = 10;  
        p.currentWeight = 8;

        Item item = new Item("Heavy Armor", "A very heavy armor", 5, 200); 
        boolean result = p.addItem(item);

        assertFalse(result); 
        assertEquals(8, p.currentWeight); 
    }
    @Test
    @DisplayName("Test sell an item in inventory")
    void testSellItem() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        Item item = new Item("Sword", "A sharp blade", 5, 50);
        p.inventory.add(item);

        boolean result = p.sell(item);

        assertTrue(result);
        assertFalse(p.inventory.contains(item));
        assertEquals(150, p.money); 
    }
    @Test
    @DisplayName("Test setXp")
    void testSetXp() {
        Adventurer p = new Adventurer("Florian", "Grognak le barbare", 100, new ArrayList<>());
        p.setXp(50);  

        assertEquals(50, p.getXp()); 
    }
   
}
