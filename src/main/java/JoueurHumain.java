public class JoueurHumain extends Joueur{
    @Override
    public Carte choisirCarte() {
        return Jeu.interfaceUtilisateur.recevoirInputJoueur(this);
    }
}
