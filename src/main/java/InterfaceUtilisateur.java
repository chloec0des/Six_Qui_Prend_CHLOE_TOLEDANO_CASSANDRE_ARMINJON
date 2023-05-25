import java.util.List;
import java.util.Scanner;

public class InterfaceUtilisateur {
    public void afficherJeu(Jeu jeu) {
        // Display game state to the user
    }

    public Carte recevoirInputJoueur(JoueurHumain joueur) {
        // Receive user input and return the selected card
        System.out.println("Joueur, veuillez entrer le numéro de la carte à jouer:");
        Scanner scanner = new Scanner(System.in);
        int carteIndex = scanner.nextInt();
        List<Carte> hand = joueur.getHand();
        Carte carteChoisie = hand.get(carteIndex);
        hand.remove(carteChoisie);
        return carteChoisie;
    }
}
