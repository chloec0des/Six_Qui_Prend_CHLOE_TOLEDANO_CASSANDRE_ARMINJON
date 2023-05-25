import java.util.ArrayList;
import java.util.List;

public abstract class Joueur {
    private List<Carte> hand;
    private List<Carte> pile;

    public Joueur() {
        this.hand = new ArrayList<>();
        this.pile = new ArrayList<>();
    }

    public abstract Carte choisirCarte();

    public void ajouterCarte(Carte carte) {
        this.hand.add(carte);
    }

    public void ramasserSerie(List<Carte> serie) {
        this.pile.addAll(serie);
    }

    // Getter for hand
    public List<Carte> getHand() {
        return this.hand;
    }
}
