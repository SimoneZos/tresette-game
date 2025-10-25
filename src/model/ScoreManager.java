package model;
import java.util.List;

/**
 * Gestisce il calcolo dei punteggi e la determinazione di chi prende la mano.
 */
public class ScoreManager {

    /**
     * Aggiorna i punti del giocatore e della squadra.
     *
     * @param prese array di liste di carte prese
     * @param indiceGiocatore indice del giocatore che ha preso le carte
     * @param puntiGiocatori array dei punti di ogni giocatore
     * @param puntiSquadre array dei punti delle squadre
     */
    public static void calcolaPunti(List<Carta>[] prese, int indiceGiocatore, int[] puntiGiocatori, int[] puntiSquadre) {
        int puntiMano = prese[indiceGiocatore].stream().mapToInt(Carta::getPunteggio).sum();
        puntiGiocatori[indiceGiocatore] += puntiMano;
        puntiSquadre[GameLogic.SQUADRE[indiceGiocatore]] += puntiMano;
    }

    /**
     * Calcola i punteggi finali delle squadre.
     *
     * @param prese array di liste di carte prese
     * @param ultimoPrenditore indice dell'ultimo prenditore
     * @return array con i punteggi delle due squadre
     */
    public static int[] calcolaPunteggiFinali(List<Carta>[] prese, int ultimoPrenditore) {
        int puntiSquadraA = prese[0].stream().mapToInt(Carta::getPunteggio).sum() +
                           prese[2].stream().mapToInt(Carta::getPunteggio).sum();
        int puntiSquadraB = prese[1].stream().mapToInt(Carta::getPunteggio).sum() +
                           prese[3].stream().mapToInt(Carta::getPunteggio).sum();

        int punteggioFinaleA = puntiSquadraA / 3;
        int punteggioFinaleB = puntiSquadraB / 3;

        if (GameLogic.SQUADRE[ultimoPrenditore] == 0) {
            punteggioFinaleA += 1;
        } else {
            punteggioFinaleB += 1;
        }
        return new int[]{punteggioFinaleA, punteggioFinaleB};
    }

    /**
     * Determina quale giocatore prende la mano.
     *
     * @param tavolo carte sul tavolo
     * @param primoGiocatore indice del primo giocatore della mano
     * @param giocatori lista dei giocatori
     * @param prese array di liste di carte prese
     * @param log log delle azioni
     * @return l'indice del giocatore che prende la mano
     */
    public static int determinaChiPrende(List<Carta> tavolo, int primoGiocatore, List<Giocatore> giocatori, List<Carta>[] prese, List<String> log) {
        if (tavolo.size() < GameLogic.NUM_PLAYERS) {
            return -1;
        }

        String semeIniziale = tavolo.get(0).getSeme();
        int massimoOrdine = -1;
        int indiceVincitore = 0;

        for (int i = 0; i < tavolo.size(); i++) {
            Carta carta = tavolo.get(i);
            if (carta.getSeme().equals(semeIniziale) && carta.getOrdine() > massimoOrdine) {
                massimoOrdine = carta.getOrdine();
                indiceVincitore = i;
            }
        }

        int indiceGiocatorePrenditore = (primoGiocatore - indiceVincitore + GameLogic.NUM_PLAYERS) % GameLogic.NUM_PLAYERS;
        log.add(giocatori.get(indiceGiocatorePrenditore).getNome() + " prende con " + tavolo.get(indiceVincitore));
        prese[indiceGiocatorePrenditore].addAll(tavolo);

        return indiceGiocatorePrenditore;
    }
}