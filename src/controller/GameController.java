package controller;

import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

import model.Carta;
import model.Giocatore;
import model.PlayerManager;
import model.TreSetteModel;
import utils.AudioManager;
import view.Navigator;
import view.TreSetteView;

/**
 * Controller principale del gioco TreSette.
 *
 * Gestisce la logica di gioco, coordina le interazioni tra il modello e la view,
 * e gestisce i turni dei giocatori umani e computer.
 * Implementa l'interfaccia Observer per ricevere aggiornamenti dal modello.
 *
 * @see TreSetteModel
 * @see TreSetteView
 */
public class GameController implements Observer {
    /** Flag che indica se il timer del computer è attivo. */
    private boolean computerTimerAttivo = false;
    /** Modello del gioco. */
    private final TreSetteModel model;
    /** View del gioco. */
    private final TreSetteView view;
    /** Flag per prevenire la visualizzazione multipla del dialogo di fine partita. */
    private boolean finePartitaMostrata = false;

    /**
     * Costruttore del controller del gioco.
     *
     * @param model il modello del gioco
     * @param view la view del gioco
     */
    public GameController(TreSetteModel model, TreSetteView view) {
        this.model = model;
        this.view = view;
        // Imposta il listener delle carte nella view tramite il setter
        view.setCardListener(createCardListener());
        // Listener per "Nuova Partita"
        view.addPlayButtonListener(e -> {
            model.initGame();
            finePartitaMostrata = false;
            playFirstComputerIfNeeded();
            playComputerTurnsAsync();
        });
        // Listener per "Menu"
        view.setMenuButtonListener(e -> {
            model.setPartitaFinita(true);
            Navigator.getInstance().showScreen("menu");
        });
    }

    /**
     * Metodo chiamato quando il modello notifica un cambiamento.
     *
     * @param o l'observable che ha notificato il cambiamento
     * @param arg argomento passato con la notifica
     */
    @Override
    public void update(Observable o, Object arg) {
    	checkGameState();
    }

    /**
     * Gestisce l'evoluzione della partita in base allo stato corrente del modello.
     *
     * - Se la mano va chiusa, avvia un timer per chiuderla e far giocare eventualmente il computer.
     * - Se la partita è terminata, mostra il messaggio di fine partita nella view.
     * - Altrimenti, mantiene lo stato coerente per le successive azioni.
     */

    public void checkGameState() {

        if (model.manoDaChiudere) {
            Timer timer = new Timer(3000, e -> {
                model.chiudiManoSeNecessario();
                if (!model.isPartitaFinita() && !model.getGiocatori().get(model.getTurnoCorrente()).isUmano()) {
                    playComputerTurnsAsync();
                }
            });
            timer.setRepeats(false);
            timer.start();
            return;
        }
        // Mostra dialogo fine partita
        if (model.isPartitaFinita() && !finePartitaMostrata) {
            finePartitaMostrata = true;
            AudioManager.playSound("end.wav");
            view.showMessage(model.getMessaggioFine(), "Fine partita", TreSetteView.INFORMATION_MESSAGE);
        }
        if (!model.isPartitaFinita()) {
            finePartitaMostrata = false;
        }
    }

    /**
     * Crea e restituisce un ActionListener per gestire i click sulle carte.
     *
     * @return ActionListener configurato per gestire le interazioni con le carte
     */
    private ActionListener createCardListener() {
        return e -> {
            if (model.getTurnoCorrente() != 0) {
                view.showMessage("Non è il tuo turno!", "Errore", TreSetteView.ERROR_MESSAGE);
                return;
            }
            Giocatore player = model.getGiocatori().get(0);
            List<Carta> carte = player.getCarte();
            Carta cartaSelezionata = carte.stream()
                .filter(c -> c.toString().equals(e.getActionCommand()))
                .findFirst()
                .orElse(null);
            if (cartaSelezionata == null) {
				return;
			}
            // Regola del seme obbligatorio
            if (!model.getCarteSulTavolo().isEmpty()) {
                String semeRichiesto = model.getCarteSulTavolo().get(0).getSeme();
                boolean haSeme = carte.stream().anyMatch(c -> c.getSeme().equals(semeRichiesto));
                if (haSeme && !cartaSelezionata.getSeme().equals(semeRichiesto)) {
                    view.showMessage("Devi giocare una carta di " + semeRichiesto + "!", "Mossa non valida", TreSetteView.WARNING_MESSAGE);
                    return;
                }
            }
            model.giocaCarta(0, cartaSelezionata);
            AudioManager.playSound("play.wav");
            if (!model.manoDaChiudere) {
                playComputerTurnsAsync();
            }
        };
    }

    /**
     * Gestisce i turni dei giocatori computer in modo asincrono con un timer.
     *
     * I computer giocano le loro carte con un ritardo di 3 secondi tra un turno e l'altro
     * per simulare un comportamento più realistico.
     */
    public void playComputerTurnsAsync() {
        if (model.manoDaChiudere || computerTimerAttivo) {
			return;
		}
        computerTimerAttivo = true;
        Timer timer = new Timer(3000, null);
        timer.addActionListener(ev -> {
            if (!model.isPartitaFinita() && !model.getGiocatori().get(model.getTurnoCorrente()).isUmano() && !model.manoDaChiudere) {
                Giocatore giocatore = model.getGiocatori().get(model.getTurnoCorrente());
                List<Carta> carte = giocatore.getCarte();
                Carta cartaScelta = carte.isEmpty() ? null : PlayerManager.selectCardToPlay(carte, model.getCarteSulTavolo());
                if (cartaScelta != null) {
                    model.giocaCarta(model.getTurnoCorrente(), cartaScelta);
                    AudioManager.playSound("play.wav");
                }
                checkGameState();
                computerTimerAttivo = false;
                if (!model.isPartitaFinita() && !model.getGiocatori().get(model.getTurnoCorrente()).isUmano() && !model.manoDaChiudere) {
                    playComputerTurnsAsync();
                }
            } else {
                computerTimerAttivo = false;
                timer.stop();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * Fa giocare immediatamente il computer se è il primo di turno all'inizio della partita.
     *
     * Questo metodo viene chiamato all'avvio di una nuova partita per garantire
     * che il computer giochi per primo se necessario.
     */
    private void playFirstComputerIfNeeded() {
        if (!model.getGiocatori().get(model.getTurnoCorrente()).isUmano() && !model.isPartitaFinita()) {
            Giocatore giocatore = model.getGiocatori().get(model.getTurnoCorrente());
            List<Carta> carte = giocatore.getCarte();
            Carta cartaScelta = PlayerManager.selectCardToPlay(carte, model.getCarteSulTavolo());
            if (cartaScelta != null) {
                model.giocaCarta(model.getTurnoCorrente(), cartaScelta);
            }
        }
    }
}