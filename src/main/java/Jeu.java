import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Jeu {
    private List<Carte> deck;
    private List<Joueur> players;
    private List<Serie> table;
    public static InterfaceUtilisateur interfaceUtilisateur;

    public Jeu() {
        this.deck = genererDeck();
        this.players = new ArrayList<>();
        this.table = new ArrayList<>();
    }

    private List<Carte> genererDeck() {
        List<Carte> deck = new ArrayList<>();
        for (int i = 1; i <= 104; i++) {
            deck.add(new Carte(i));
        }
        return deck;
    }

    public void ajouterJoueur(Joueur joueur) {
        this.players.add(joueur);
    }

    public List<Joueur> getPlayers() {
        return players;
    }

    public List<Serie> getTable() {
        return table;
    }

    public void demarrerJeu() {
        // Shuffle deck
        Collections.shuffle(deck);

        // Distribute 10 cards to each player
        for (Joueur joueur : players) {
            for (int i = 0; i < 10; i++) {
                joueur.ajouterCarte(deck.remove(deck.size() - 1));
            }
        }

        // Place 4 cards in the middle of the table
        for (int i = 0; i < 4; i++) {
            Serie serie = new Serie();
            serie.ajouterCarte(deck.remove(deck.size() - 1));
            table.add(serie);
        }

        // Start first turn
        jouerTour();
    }

    public void jouerTour() {
        while(!estTermine()) {
            for (Joueur joueur : players) {

                Carte carteChoisie;
                if (joueur instanceof JoueurHumain) {
                    carteChoisie = interfaceUtilisateur.recevoirInputJoueur((JoueurHumain) joueur);
                } else {
                    carteChoisie = joueur.jouerCarte();
                }
                // Place the card on the table according to the game rules
                placerCarteSurTable(carteChoisie, joueur);
                interfaceUtilisateur.afficherJeu(this);
                if (joueur.aTermine()) {
                    terminerJeu();
                    return;
                }
            }
        }
        terminerJeu();
    }


    public void terminerJeu() {
        // Calculate scores and declare winner
        Joueur gagnant = null;
        int scoreMin = Integer.MAX_VALUE;
        for (Joueur joueur : players) {
            int score = joueur.calculerScore();
            if (score < scoreMin) {
                scoreMin = score;
                gagnant = joueur;
            }
        }
        System.out.println("Le jeu est terminé. Le gagnant est " + gagnant + " avec un score de " + scoreMin);
    }

    private void placerCarteSurTable(Carte carte, Joueur joueur) {
        // Find the serie with the smallest difference between its last card and the chosen card
        Serie serieCible = null;
        int diffMin = Integer.MAX_VALUE;
        for (Serie serie : table) {
            int diff = carte.getValeur() - serie.getDerniereCarte().getValeur();
            if (diff > 0 && diff < diffMin) {
                diffMin = diff;
                serieCible = serie;
            }
        }

        if (serieCible != null && serieCible.getCartes().size() < 5) {
            // If there is a serie where the card can be placed and it's not full, place the card there
            serieCible.ajouterCarte(carte);
        } else if (serieCible != null) {
            // If the serie is full, the player has to collect it
            joueur.ramasserSerie(serieCible.ramasserCartes());
            serieCible.ajouterCarte(carte);
        } else {
            // If there is no serie where the card can be placed, the player has to collect a serie of their choice
            if (joueur instanceof JoueurHumain) {
                Serie serieAChoisir = interfaceUtilisateur.choisirSerie((JoueurHumain) joueur, table);
                joueur.ramasserSerie(serieAChoisir.ramasserCartes());
                serieAChoisir.ajouterCarte(carte);
            } else {
                // Si le joueur est une IA, vous pouvez implémenter la logique pour choisir une série à ramasser.
                // Pour l'instant, on suppose que l'IA ramasse simplement la première série.
                joueur.ramasserSerie(table.get(0).ramasserCartes());
                table.get(0).ajouterCarte(carte);
            }
        }
    }
    public boolean estTermine() {
        if (deck.isEmpty()) {
            return true;
        }
        for (Joueur joueur : players) {
            if (!joueur.aTermine()) {
                return false;
            }
        }
        return true;
    }

}
