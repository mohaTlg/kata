package re.forestier.edu;
import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.Dwarf;
import re.forestier.edu.rpg.Player;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Dwarf firstPlayer = new Dwarf("Florian", "Ruzberg de Rivehaute", 200, new ArrayList<>());
        firstPlayer.addMoney(400);

        Player.addXp(firstPlayer, 15);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
        System.out.println("------------------");
        Player.addXp(firstPlayer, 20);
        System.out.println(Affichage.afficherJoueur(firstPlayer));
    }
}