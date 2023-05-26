import java.util.ArrayList;
import java.util.List;

public class JoueurHumain extends Joueur {


    public JoueurHumain(String nom) {
            super(nom); // Passer le nom au constructeur de la superclasse

    }

    //test


    @Override
    public Carte choisirCarte(int index) {
        return main.remove(index);
    }

}
