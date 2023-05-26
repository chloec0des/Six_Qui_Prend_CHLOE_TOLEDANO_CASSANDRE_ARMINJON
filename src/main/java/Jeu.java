import java.util.ArrayList;
import java.util.Comparator;
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
        List<Carte> cartesJouees = new ArrayList<>();
        // Chaque joueur choisit une carte à jouer
        for (Joueur joueur : players) {
            Carte carteChoisie;
            if (joueur instanceof JoueurHumain) {
                carteChoisie = interfaceUtilisateur.recevoirInputJoueur((JoueurHumain) joueur, this);
            } else {
                carteChoisie = joueur.jouerCarte();
            }
            joueur.setCarteJouee(carteChoisie);
            cartesJouees.add(carteChoisie);
        }
        // Trier les cartes jouées dans l'ordre croissant
        cartesJouees.sort(Comparator.comparingInt(Carte::getValeur));
        // Chaque joueur place sa carte sur la table
        for (Carte carte : cartesJouees) {
            Joueur joueur = getPlayerWhoPlayedCard(carte);
            placerCarteSurTable(carte, joueur);
            interfaceUtilisateur.afficherJeu(this);
            if (joueur.aTermine()) {
                terminerJeu();
                return;
            }
        }
    }

    // Retourne le joueur qui a joué une carte spécifique
    private Joueur getPlayerWhoPlayedCard(Carte carte) {
        for (Joueur joueur : players) {
            if (joueur.carteJouee.getValeur() == carte.getValeur()) {
                return joueur;
            }
        }
        return null;
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
        // Trouver la série avec la plus petite différence entre sa dernière carte et la carte choisie
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
            // Si une série existe où la carte peut être placée et qu'elle n'est pas complète, placez la carte là-bas.
            serieCible.ajouterCarte(carte);
        } else if (serieCible != null) {
            // Si la série est pleine, le joueur doit la collecter.
            joueur.ramasserSerie(serieCible.ramasserCartes());
            serieCible.ajouterCarte(carte);
        } else {
            //S'il n'y a aucune série où la carte peut être placée, le joueur doit collecter une série de son choix.
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