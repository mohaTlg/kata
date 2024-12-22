package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.Player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Random;

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

}
