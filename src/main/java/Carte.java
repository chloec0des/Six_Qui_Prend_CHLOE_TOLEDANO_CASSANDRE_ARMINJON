public class Carte {
    private int valeur;
    private int teteDeBoeuf;

    public Carte(int valeur, int teteDeBoeuf) {
        this.valeur = valeur;
        this.teteDeBoeuf = teteDeBoeuf;
    }
    public Carte(int valeur) {
        this.valeur = valeur;
        this.teteDeBoeuf = determineTeteDeBoeuf(valeur);
    }

    private int determineTeteDeBoeuf(int valeur) {
        if (valeur % 10 == 0) {
            return 3;
        } else if (valeur % 10 == 5) {
            return 2;
        } else {
            return 1;
        }
    }


    public int getValeur() {
        return this.valeur;
    }

    public int getTeteDeBoeuf() {
        return this.teteDeBoeuf;
    }
}
