import java.util.ArrayList;
import java.util.List;

public class JoueurHumain extends Joueur {
    private String nom;
    private List<Carte> main;
    private List<Carte> pile;

    public JoueurHumain(String nom) {
            super(nom); // Passer le nom au constructeur de la superclasse

    }

    //test

    public String getNom() {
        return nom;
    }

    public List<Carte> getMain() {
        return main;
    }

    public List<Carte> getPile() {
        return pile;
    }

    @Override
    public Carte choisirCarte(int index) {
        return main.remove(index);
    }

}
