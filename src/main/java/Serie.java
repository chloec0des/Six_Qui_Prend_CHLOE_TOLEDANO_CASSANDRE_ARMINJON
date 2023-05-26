import java.util.ArrayList;
import java.util.List;
import java.util.List;
import java.util.List;import java.util.List;

import java.util.List;
import java.util.List;

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
    public Carte getDerniereCarte() {
        // Retourne la dernière carte de la série.
        // On suppose que les cartes sont stockées dans l'ordre dans une List.
        return this.cartes.get(this.cartes.size() - 1);
    }

    public List<Carte> getCartes() {
        // Retourne la liste de toutes les cartes dans la série.
        return this.cartes;
    }

    public void vider() {
        // Vide la série de ses cartes.
        this.cartes.clear();
    }

}
