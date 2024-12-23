package re.forestier.edu;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Item;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
public class GlobalTest {

    @Test
    void testAffichageBase() {
        Adventurer player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<Item>());
        Adventurer.addXp(player, 20);
        player.setInventory(new ArrayList<>());
        Item item = new Item("Magic Bow", "Prevents surprise attacks", 2, 100);
        player.inventory.add(item);
        verify(Affichage.afficherJoueur(player));
    }

       @Test
    void testAffichageMarkdown() {
        Archer player = new Archer("Florian", "SpiderMan", 150, new ArrayList<>());
        Item item = new Item("Magic Bow", "Prevents surprise attacks", 2, 100);
        player.inventory.add(item);
        verify(Affichage.afficherJoeurMarkdown(player));
    }
}
