public class Main {

    public static void main(String[] args) {
        JoueurHumain joueur1 = new JoueurHumain("Joueur 1");
        JoueurHumain joueur2 = new JoueurHumain("Joueur 2");
        Jeu jeu = new Jeu();
        jeu.ajouterJoueur(joueur1);
        jeu.ajouterJoueur(joueur2);

        // Initialisation de l'interface utilisateur
        InterfaceUtilisateur interfaceUtilisateur = new InterfaceUtilisateur();
        Jeu.interfaceUtilisateur = interfaceUtilisateur; // Assign the interface to the static field in Jeu

        jeu.demarrerJeu();

        // Boucle principale du jeu
        while (!jeu.estTermine()) {
            interfaceUtilisateur.afficherJeu(jeu);
            jeu.jouerTour();
        }

        // Affichage du score final
        System.out.println("Le jeu est terminé.");
        System.out.println("Score final du joueur 1: " + joueur1.calculerScore());
        System.out.println("Score final du joueur 2: " + joueur2.calculerScore());
    }
}