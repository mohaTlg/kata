package re.forestier.edu;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.Item;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class GlobalTest {

    @Test
    void testAffichageBase() {
        Adventurer player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<Item>());
        Adventurer.addXp(player, 20);
        player.setInventory(new ArrayList<>());

        verify(Affichage.afficherJoueur(player));
    }

       @Test
    void testAffichageMarkdown() {
        Archer player = new Archer("Florian", "SpiderMan", 150, new ArrayList<>());

        verify(Affichage.afficherJoeurMarkdown(player));
    }
}
