package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gestisce la creazione del mazzo e la distribuzione delle carte.
 */
public class CardManager {

    /**
     * Crea e mescola un mazzo di carte.
     *
     * @return Lista di carte mescolate
     */
    public static List<Carta> createDeck() {
        List<Carta> mazzo = new ArrayList<>();
        for (String seme : GameLogic.SEMI) {
            for (String valore : GameLogic.VALORI) {
                mazzo.add(new Carta(seme, valore)); 
            }
        }
        Collections.shuffle(mazzo);
        return mazzo;
    }

    /**
     * Distribuisce le carte ai giocatori.
     *
     * @param mazzo mazzo di carte da distribuire
     * @param giocatori lista dei giocatori
     */
    public static void dealCards(List<Carta> mazzo, List<Giocatore> giocatori) {
        for (int i = 0; i < GameLogic.CARDS_PER_PLAYER; i++) {
            for (int j = giocatori.size() - 1; j >= 0; j--) {
                giocatori.get(j).addCarta(mazzo.remove(0));
            }
        }
    }
}