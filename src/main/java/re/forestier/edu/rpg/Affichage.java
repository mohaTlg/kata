package re.forestier.edu.rpg;

import java.util.List;

public class Affichage {

    public static String afficherJoueur(Player player) {
        final String[] finalString = {"Joueur " + player.Avatar_name + " joué par " + player.playerName};
        finalString[0] += "\nNiveau : " + player.retrieveLevel() + " (XP totale : " + player.xp + ")";
        finalString[0] += "\n\nCapacités :";
        player.abilities.forEach((name, level) -> {
            finalString[0] += "\n   " + name + " : " + level;
        });
        finalString[0] += "\n\nInventaire :";
        player.inventory.forEach(item -> {
            finalString[0] += "\n   " + item;
        });

        return finalString[0];
    }

    public static String afficherJoeurMarkdown(Player player) {
        StringBuilder markdown = new StringBuilder();

        markdown.append("# Joueur : ").append(player.playerName).append("\n");
        markdown.append("## Avatar : ").append(player.Avatar_name).append("\n");
        markdown.append("**Classe d'avatar** : ").append(player.getAvatarClass()).append("\n");
        markdown.append("**Niveau** : ").append(player.retrieveLevel()).append("\n\n");
        markdown.append("### Capacités :\n");
        player.abilities.forEach((name, level) -> {
            markdown.append("* **").append(name).append("** : ").append(level).append("\n");
        });
        
        markdown.append("\n### Inventaire** :\n");
        player.inventory.forEach(item -> {
            markdown.append(" *").append(item.getName()).append("\n");
        });
        return markdown.toString();
    }


}
