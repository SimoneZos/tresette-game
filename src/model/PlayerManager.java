package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestisce la creazione dei giocatori e la logica di gioco per la CPU.
 */
public class PlayerManager {

    /**
     * Crea la lista dei giocatori (umano + CPU).
     *
     * @param nickname nickname del giocatore umano
     * @param avatar avatar del giocatore umano
     * @return lista dei giocatori
     */
    public static List<Giocatore> createPlayers(String nickname, String avatar) {
        List<Giocatore> giocatori = new ArrayList<>();
        giocatori.add(new Giocatore(nickname, true, avatar));
        for (int i = 1; i < GameLogic.NUM_PLAYERS; i++) {
            giocatori.add(new Giocatore("Computer " + i, false));
        }
        return giocatori;
    }

    /**
     * Sceglie la carta da giocare per il computer.
     *
     * @param carteComputer carte in mano al computer
     * @param carteSulTavolo carte già sul tavolo
     * @return la carta scelta da giocare
     */
    public static Carta selectCardToPlay(List<Carta> carteComputer, List<Carta> carteSulTavolo) {
        if (carteSulTavolo.isEmpty()) {
            return carteComputer.get(0);
        }
        String semeDominante = carteSulTavolo.get(0).getSeme();
        List<Carta> carteStessoSeme = carteComputer.stream()
                .filter(c -> c.getSeme().equals(semeDominante))
                .toList();
        if (!carteStessoSeme.isEmpty()) {
            return carteStessoSeme.get(0);
        }
        return carteComputer.get(0);
    }
}