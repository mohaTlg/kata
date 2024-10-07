package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class UnitTests {

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

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
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(45);
        assertEquals(145, p.money);
    }

    @Test
    @DisplayName("Test removeMoney")
    void testRemoveMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
        p.removeMoney(45);
        
        assertEquals(55, p.money);
    }

    @Test
    @DisplayName("Test removeMoney with invalid value")
    void testRemoveMoneyInvalid() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(150));
    }

    @Test
    @DisplayName("Test addXp and retrieveLevel")
    void testAddXpAndRetrieveLevel() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
        UpdatePlayer.addXp(p, 5);
        assertEquals(1, p.retrieveLevel());
        
        UpdatePlayer.addXp(p, 20);
        assertEquals(2, p.retrieveLevel());

        UpdatePlayer.addXp(p, 10);
        assertEquals(3, p.retrieveLevel());

        UpdatePlayer.addXp(p, 45);
        assertEquals(4, p.retrieveLevel());
        
        UpdatePlayer.addXp(p, 120);
        assertEquals(5, p.retrieveLevel());
    }



    @Test
    @DisplayName("Test avatar class validation")
    void testAvatarClassValidation() {
        // Classe d'avatar non valide
        player invalidPlayer = new player("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>());
        assertNull(invalidPlayer.getAvatarClass());

        // Classe d'avatar valide
        player validPlayer = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        assertEquals("ARCHER", validPlayer.getAvatarClass());
    }

    @Test
    @DisplayName("Test getXp")
    void testGetXp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertEquals(0, p.getXp());
    }

    //TESt update player
    @Test
    @DisplayName("testPlayerKO") 
    void testPlayerKO(){
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 0;
        UpdatePlayer.majFinDeTour(player);
        assertEquals(0, player.currenthealthpoints);
    }

    @Test
    @DisplayName("test health below half (adventurer)")
    void testHealthBelowHalfAdventurer(){
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        UpdatePlayer.addXp(player, 20);
        UpdatePlayer.majFinDeTour(player);
        assertEquals(41, player.currenthealthpoints);
    }
    
    @Test
    // @DisplayName("test health below half adventurer with elixir")
    void testHealthBelowHalfdwarfWithElixir(){
        player player = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        player.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(player);
        assertEquals(42, player.currenthealthpoints);
    }

    @Test
    // @DisplayName("test health below half archer with magic bow")
    void testHealthBelowHalfarcherWithmagic(){
        player player = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 40;
        player.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(player);
        assertEquals(45, player.currenthealthpoints);
    }

    @Test
    // @DisplayName("test health above half point")
    void testHealthAboveHalf(){
        player player = new player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        player.healthpoints = 100;
        player.currenthealthpoints = 100;
        UpdatePlayer.majFinDeTour(player);
        assertEquals(100, player.currenthealthpoints);
    }

    @Test
    @DisplayName("return false if want add 0 xp")
    void testAddZeroXp(){
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertFalse(UpdatePlayer.addXp(player, 0));
    }



}
