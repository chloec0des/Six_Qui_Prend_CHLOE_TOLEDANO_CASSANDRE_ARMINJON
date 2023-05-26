import java.util.List;

public class IA extends Joueur {
    private int niveauDifficulte;

    public IA(int niveauDifficulte, String nom) {
        super(nom); // Passer le nom au constructeur de la superclasse
        this.niveauDifficulte = niveauDifficulte;
    }

    @Override
    public Carte choisirCarte(int index) {
        // L'IA ignore l'index et joue la carte avec la valeur la plus élevée dans sa main
        List<Carte> hand = this.getHand();
        Carte carteMax = hand.get(0);
        for (Carte carte : hand) {
            if (carte.getValeur() > carteMax.getValeur()) {
                carteMax = carte;
            }
        }
        hand.remove(carteMax);
        return carteMax;
    }
}
