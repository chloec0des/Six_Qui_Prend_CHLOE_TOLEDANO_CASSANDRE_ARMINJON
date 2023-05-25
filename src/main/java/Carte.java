public class Carte {
    private int valeur;
    private int teteDeBoeuf;

    public Carte(int valeur, int teteDeBoeuf) {
        this.valeur = valeur;
        this.teteDeBoeuf = teteDeBoeuf;
    }

    public int getValeur() {
        return this.valeur;
    }

    public int getTeteDeBoeuf() {
        return this.teteDeBoeuf;
    }
}
