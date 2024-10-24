package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;
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
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

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
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(45);
        assertEquals(145, p.money);
    }

    @Test
    @DisplayName("Test removeMoney")
    void testRemoveMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
        p.removeMoney(45);
        
        assertEquals(55, p.money);
    }

    @Test
    @DisplayName("Test removeMoney with invalid value")
    void testRemoveMoneyInvalid() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
        assertThrows(IllegalArgumentException.class, () -> p.removeMoney(150));
    }

    @Test
    @DisplayName("Test addXp and retrieveLevel")
    void testAddXpAndRetrieveLevel() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        
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
        assertThrows(IllegalArgumentException.class, () -> new Player("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>()));
        

        // Classe d'avatar valide
        Player validPlayer = new Player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        assertEquals("ARCHER", validPlayer.getAvatarClass());
    }

    @Test
    @DisplayName("Test getXp")
    void testGetXp() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertEquals(0, p.getXp());
    }

    //TESt update player
    @Test
    @DisplayName("testPlayerKO") 
    void testPlayerKO(){
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthPoints = 100;
        player.currenthealthPoints = 0;
        UpdatePlayer.majFinDeTour(player);
        assertEquals(0, player.currenthealthPoints);
    }

    @Test
    @DisplayName("test health below half (adventurer)")
    void testHealthBelowHalfAdventurer(){
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        player.healthPoints = 100;
        player.currenthealthPoints = 40;
        UpdatePlayer.addXp(player, 20);
        UpdatePlayer.majFinDeTour(player);
        assertEquals(41, player.currenthealthPoints);
    }
    
    @Test
    // @DisplayName("test health below half adventurer with elixir")
    void testHealthBelowHalfdwarfWithElixir(){
        Player player = new Player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        player.healthPoints = 100;
        player.currenthealthPoints = 40;
        player.inventory.add("Holy Elixir");
        UpdatePlayer.majFinDeTour(player);
        assertEquals(42, player.currenthealthPoints);
    }

    @Test
    // @DisplayName("test health below half archer with magic bow")
    void testHealthBelowHalfarcherWithmagic(){
        Player player = new Player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        player.healthPoints = 100;
        player.currenthealthPoints = 40;
        player.inventory.add("Magic Bow");
        UpdatePlayer.majFinDeTour(player);
        assertEquals(45, player.currenthealthPoints);
    }

    @Test
    // @DisplayName("test health above half point")
    void testHealthAboveHalf(){
        Player player = new Player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        player.healthPoints = 100;
        player.currenthealthPoints = 100;
        UpdatePlayer.majFinDeTour(player);
        assertEquals(100, player.currenthealthPoints);
    }

    @Test
    @DisplayName("return false if want add 0 xp")
    void testAddZeroXp(){
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertFalse(UpdatePlayer.addXp(player, 0));
    }



}
