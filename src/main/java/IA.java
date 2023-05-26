import java.util.List;
        import java.util.Random;

public class IA extends Joueur {
    private int niveauDifficulte;

    public IA(int niveauDifficulte, String nom) {
        super(nom);
        this.niveauDifficulte = niveauDifficulte;
    }

    @Override
    public Carte jouerCarte() {
        switch (niveauDifficulte) {
            case 1:
                return jouerCarteNiveauFacile();
            case 2:
                return jouerCarteNiveauIntermediaire();
            case 3:
                return jouerCarteNiveauDifficile();
            default:
                return jouerCarteNiveauFacile();
        }
    }

    private Carte jouerCarteNiveauFacile() {
        // L'IA choisit une carte aléatoirement
        List<Carte> hand = this.getHand();
        int randomIndex = new Random().nextInt(hand.size());
        return hand.remove(randomIndex);
    }

    private Carte jouerCarteNiveauIntermediaire() {
        // L'IA joue la carte avec la valeur la plus élevée
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

    private Carte jouerCarteNiveauDifficile() {
        // L'IA évalue chaque carte de sa main en fonction de la situation actuelle du jeu
        // et joue la carte qui minimise le nombre de têtes de bœuf qu'elle risque de ramasser
        // Pour l'instant, cela est simplement représenté par la carte avec la plus faible valeur,
        // mais cela pourrait être amélioré pour inclure une évaluation plus sophistiquée.
        List<Carte> hand = this.getHand();
        Carte carteMin = hand.get(0);
        for (Carte carte : hand) {
            if (carte.getValeur() < carteMin.getValeur()) {
                carteMin = carte;
            }
        }
        hand.remove(carteMin);
        return carteMin;
    }
}
