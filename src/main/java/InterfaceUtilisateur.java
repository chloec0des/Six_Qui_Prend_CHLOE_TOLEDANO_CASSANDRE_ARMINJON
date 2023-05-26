import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InterfaceUtilisateur {
    private Scanner scanner;

    public InterfaceUtilisateur() {
        this.scanner = new Scanner(System.in);
    }
    public void afficherJeu(Jeu jeu) {
        System.out.println("État actuel du jeu :");

        // Display each player's hand
        int playerNumber = 1;
        for (Joueur player : jeu.getPlayers()) {
            System.out.println("Main du Joueur " + playerNumber + " :");
            int cardIndex = 0;
            for (Carte card : player.getHand()) {
                System.out.print(cardIndex + ": " + card.getValeur() + " ");
                cardIndex++;
            }
            System.out.println();
            playerNumber++;
        }

        // Display current state of the table
        System.out.println("État de la table :");
        int serieNumber = 1;
        for (Serie serie : jeu.getTable()) {
            System.out.println("Serie " + serieNumber + ":");
            for (Carte card : serie.getCartes()) {
                System.out.print(card.getValeur() + " ");
            }
            System.out.println();
            serieNumber++;
        }

        // Display current scores
        System.out.println("Scores actuels :");
        playerNumber = 1;
        for (Joueur player : jeu.getPlayers()) {
            int score = 0;
            for (Carte card : player.getPile()) {
                score += card.getTeteDeBoeuf();
            }
            System.out.println("Score Joueur " + playerNumber + ": " + score);
            playerNumber++;
        }
    }


    public Carte recevoirInputJoueur(JoueurHumain joueur) {
        // Receive user input and return the selected card
        Scanner scanner = new Scanner(System.in);
        int carteIndex = -1;
        while (carteIndex < 0 || carteIndex >= joueur.getHand().size()) {
            System.out.println("Joueur, veuillez entrer le numéro de la carte à jouer:");
            try {
                carteIndex = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrée non valide. Veuillez entrer un nombre.");
                scanner.next(); // discard invalid input
            }
        }
        Carte carteChoisie = joueur.getHand().get(carteIndex);
        joueur.getHand().remove(carteChoisie);
        return carteChoisie;
    }
    public Serie choisirSerie(JoueurHumain joueur, List<Serie> table) {
        Scanner scanner = new Scanner(System.in);  // Add this line to declare a new Scanner
        System.out.println("Vous devez ramasser une série. Voici les séries disponibles :");
        for (int i = 0; i < table.size(); i++) {
            Serie serie = table.get(i);
            System.out.println((i + 1) + ". " + serie);
        }

        int choix = 0;
        do {
            System.out.println("Veuillez entrer le numéro de la série que vous voulez ramasser :");
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        } while (choix < 1 || choix > table.size());

        return table.get(choix - 1);
    }

}
