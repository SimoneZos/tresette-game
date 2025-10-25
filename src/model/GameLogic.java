package model;

import java.util.List;

/**
 * Contiene le regole di gioco base e le costanti statiche per il Tresette.
 */
public class GameLogic {
    /** Numero di giocatori. */
    public static final int NUM_PLAYERS = 4;
    /** Carte per giocatore. */
    public static final int CARDS_PER_PLAYER = 10;
    /** Semi delle carte. */
    public static final String[] SEMI = {"Denari", "Coppe", "Spade", "Bastoni"};
    /** Valori delle carte. */
    public static final String[] VALORI = {"3", "4", "5", "6", "7", "Fante", "Cavallo", "Re", "Asso", "2"};
    /** Assegnazione squadre. */
    public static final int[] SQUADRE = {0, 1, 0, 1};


    /**
     * Determina il primo giocatore che inizia la prima mano.
     *
     * @param giocatori lista dei giocatori
     * @return l'indice del primo giocatore
     */
    public static int determinaPrimoGiocatore(List<Giocatore> giocatori) {
        return java.util.stream.IntStream.range(0, giocatori.size())
            .filter(i -> giocatori.get(i).getCarte().stream()
            .anyMatch(c -> c.getSeme().equals("Denari") && c.getValore().equals("4")))
            .findFirst()
            .orElse(0);
    }

    /**
     * Verifica se tutti i giocatori hanno finito le carte.
     *
     * @param giocatori lista dei giocatori
     * @return true se tutti i giocatori hanno finito le carte
     */
    public static boolean isFineGioco(List<Giocatore> giocatori) {
        return giocatori.stream().allMatch(g -> g.getCarte().isEmpty());
    }
}