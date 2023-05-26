import java.util.ArrayList;
import java.util.List;

public abstract class Joueur {
    private List<Carte> main;
    private List<Carte> pile;
    private String nom;

    public Joueur(String nom) {
        this.main = new ArrayList<>();
        this.pile = new ArrayList<>();
        this.nom = nom;
    }

    public abstract Carte choisirCarte(int index);
    public String getNom() {
        return this.nom;
    }
    public void ajouterCarte(Carte carte) {
        this.main.add(carte);
    }

    public void ramasserSerie(List<Carte> serie) {
        this.pile.addAll(serie);
    }

    // Getter for hand
    public List<Carte> getHand() {
        return this.main;
    }
    public Carte jouerCarte() {
        return this.main.remove(0);
    }

    public boolean aTermine() {
        return this.main.isEmpty();
    }

    public int calculerScore() {
        int score = 0;
        for (Carte carte : this.pile) {
            score += carte.getTeteDeBoeuf();
        }
        return score;
    }

    public void ramasserSerie(Serie serie) {
        this.pile.addAll(serie.ramasserCartes());
    }
    public List<Carte> getPile() {
        return this.pile;
    }


}
