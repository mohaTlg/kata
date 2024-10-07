package re.forestier.edu;

import org.junit.jupiter.api.*;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
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
    @DisplayName("add money, get xp, remove money")
    void testAddNullMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.addMoney(45);
            p.getXp();
            p.removeMoney(45);
            
            UpdatePlayer.addXp(p, 30);
            p.retrieveLevel();            

            UpdatePlayer.addXp(p, 50); //level 4
            p.retrieveLevel();            
            
            UpdatePlayer.addXp(p, 120); //level 5
            p.retrieveLevel();            
        } catch (IllegalArgumentException e) {
            return;
        }
        assertThat(p.money, is(100));
    }

    @Test
    @DisplayName("avatar class test")
    void avatarClass() {
        player player = new player("Florian", "Grognak le barbare", "ADvendu", 100, new ArrayList<>());
        player p1 = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
    
        
    }


}
