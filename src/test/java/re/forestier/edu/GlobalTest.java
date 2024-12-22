package re.forestier.edu;

import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Adventurer;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Item;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
public class GlobalTest {

    @Test
    void testAffichageBase() {
        Adventurer player = new Adventurer("Florian", "Gnognak le Barbare", 200, new ArrayList<Item>());
        Adventurer.addXp(player, 20);
        player.setInventory(new ArrayList<>());

        verify(Affichage.afficherJoueur(player));
    }
}
