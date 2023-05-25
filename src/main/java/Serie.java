import java.util.ArrayList;
import java.util.List;

public class Serie {
    private List<Carte> cartes;

    public Serie() {
        this.cartes = new ArrayList<>();
    }

    public void ajouterCarte(Carte carte) {
        this.cartes.add(carte);
    }

    public boolean estTerminee() {
        return this.cartes.size() == 5;
    }

    public List<Carte> ramasserCartes() {
        List<Carte> ramassees = new ArrayList<>(this.cartes);
        this.cartes.clear();
        return ramassees;
    }
}
