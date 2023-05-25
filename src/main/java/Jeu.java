import java.util.ArrayList;
import java.util.List;

public class Jeu {
    private List<Carte> deck;
    private List<Joueur> players;
    private List<Serie> table;
    public static InterfaceUtilisateur interfaceUtilisateur;

    public Jeu() {
        this.deck = new ArrayList<>();
        this.players = new ArrayList<>();
        this.table = new ArrayList<>();
    }

    public void demarrerJeu() {
        // Logic to start the game
    }

    public void terminerTour() {
        // Logic to end the turn
    }

    public void terminerJeu() {
        // Logic to end the game
    }
}
