import java.util.List;

public class IA extends Joueur{
    private int niveauDifficulte;

    public IA(int niveauDifficulte) {
        this.niveauDifficulte = niveauDifficulte;
    }

    @Override
    public Carte choisirCarte() {
        // L'IA joue la carte avec la valeur la plus élevée dans sa main
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
