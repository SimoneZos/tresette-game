package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Modello principale del gioco TreSette.
 * Gestisce lo stato del gioco, le regole e notifica i cambiamenti alla view.
 * Estende Observable per implementare il pattern Observer.
 */
public class TreSetteModel extends Observable {
    private List<Carta> mazzo, carteSulTavolo;
    private List<Giocatore> giocatori;
    private List<Carta>[] prese;
    private int[] punti, puntiSquadra;
    private int turnoCorrente, primoGiocatoreMano;
    public boolean partitaFinita;
    private String nickname, avatar, messaggioFine;
    private Map<String, int[]> statistiche;
    private List<String> log;
    public boolean manoDaChiudere = false;

    /**
     * Costruttore del modello.
     *
     * @param nickname nickname del giocatore
     * @param statistiche statistiche del giocatore
     * @param avatar avatar del giocatore
     */
    public TreSetteModel(String nickname, Map<String, int[]> statistiche, String avatar) {
        this.nickname = nickname;
        this.statistiche = statistiche;
        this.avatar = avatar;
        this.log = new ArrayList<>();
        initGame();
    }

    /**
     * Prepara una nuova partita.
     */
    public void initGame() {
        log.clear();
        mazzo = CardManager.createDeck();
        carteSulTavolo = new ArrayList<>();
        giocatori = PlayerManager.createPlayers(nickname, avatar);
        prese = new ArrayList[GameLogic.NUM_PLAYERS];
        Arrays.setAll(prese, i -> new ArrayList<>());
        punti = new int[GameLogic.NUM_PLAYERS];
        puntiSquadra = new int[2];
        CardManager.dealCards(mazzo, giocatori);
        turnoCorrente = GameLogic.determinaPrimoGiocatore(giocatori);
        partitaFinita = false;
        setChanged();
        notifyObservers();
    }

    /**
     * Gestisce la giocata di una carta.
     *
     * @param giocatoreIndex indice del giocatore
     * @param carta carta da giocare
     * @return true se la mossa è valida, false altrimenti
     */
    public boolean giocaCarta(int giocatoreIndex, Carta carta) {
        if (giocatoreIndex != turnoCorrente || partitaFinita || !giocatori.get(giocatoreIndex).getCarte().contains(carta)) {
            return false;
        }
        log.add("• " + giocatori.get(giocatoreIndex).getNome() + " gioca " + carta);
        giocatori.get(giocatoreIndex).removeCarta(carta);
        carta.setProprietario(giocatori.get(giocatoreIndex).getNome());
        carteSulTavolo.add(carta);
        if (carteSulTavolo.size() == 1) {
            primoGiocatoreMano = turnoCorrente;
        }
        turnoCorrente = (turnoCorrente - 1 + giocatori.size()) % giocatori.size();

        if (carteSulTavolo.size() == GameLogic.NUM_PLAYERS) {
            this.manoDaChiudere = true;
        }

        if (GameLogic.isFineGioco(giocatori) && carteSulTavolo.isEmpty() && !partitaFinita) {
            partitaFinita = true;
            aggiornaStatistiche();
        }
        setChanged();
        notifyObservers();
        return true;
    }

    /**
     * Chiude la mano corrente se necessario.
     */
    public void chiudiManoSeNecessario() {
        if (!manoDaChiudere || carteSulTavolo.isEmpty()) {
            return;
        }
        log.clear();
        int chiPrende = ScoreManager.determinaChiPrende(carteSulTavolo, primoGiocatoreMano, giocatori, prese, log);
        if (chiPrende == -1) {
            manoDaChiudere = false;
            return;
        }
        ScoreManager.calcolaPunti(prese, chiPrende, punti, puntiSquadra);
        carteSulTavolo.clear();
        turnoCorrente = chiPrende;
        manoDaChiudere = false;
        if (GameLogic.isFineGioco(giocatori) && carteSulTavolo.isEmpty() && !partitaFinita) {
            partitaFinita = true;
            aggiornaStatistiche();
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Aggiorna le statistiche del giocatore.
     */
    private void aggiornaStatistiche() {
        int[] stats = statistiche.getOrDefault(nickname, new int[]{0,0,0});
        stats[0]++;
        int[] punteggiFinali = ScoreManager.calcolaPunteggiFinali(prese, turnoCorrente);
        int mieiPunti = punteggiFinali[0];
        int puntiAvversari = punteggiFinali[1];
        if (mieiPunti == puntiAvversari) {
            messaggioFine = "Pareggio! Entrambe le squadre hanno " + mieiPunti + " punti.";
        } else if (mieiPunti > puntiAvversari) {
            stats[1]++;
            messaggioFine = "La tua squadra ha vinto con " + mieiPunti + " punti contro " + puntiAvversari + ".";
        } else {
            messaggioFine = "Hai perso! La squadra avversaria ha vinto con " + puntiAvversari + " punti contro " + mieiPunti + ".";
        }
        statistiche.put(nickname, stats);
        log.add(messaggioFine);
    }

    /** 
	 * Aggiunge un osservatore.
	 * 
	 * @param o l'osservatore da aggiungere
     */
    
    @Override
    public synchronized void addObserver(Observer o) { super.addObserver(o); }

    // Metodi getter
    public List<Giocatore> getGiocatori() { return giocatori; }
    public List<Carta>[] getPrese() { return prese; }
    public int[] getPunti() { return punti; }
    public int[] getPuntiSquadra() { return puntiSquadra; }
    public List<Carta> getCarteSulTavolo() { return carteSulTavolo; }
    public List<String> getLog() { return log; }
    public boolean isPartitaFinita() { return partitaFinita; }
    public String getMessaggioFine() { return messaggioFine; }
    public int getTurnoCorrente() { return turnoCorrente; }
    public String getNickname() { return nickname; }
    public Map<String, int[]> getStatistiche() { return statistiche; }

    public void setPartitaFinita(boolean finita) {
        this.partitaFinita = finita;
    }
}