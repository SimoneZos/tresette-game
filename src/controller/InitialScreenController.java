package controller;

import java.util.HashMap;
import java.util.Map;

import view.screens.InitialScreen;

/**
 * Controller per la gestione della schermata iniziale (nickname/avatar).
 */
public class InitialScreenController {

    /**
     * Interfaccia per avviare il MainCoordinator con dati.
     */
    public interface MainCoordinatorLauncher {
        /**
         * Avvia il MainCoordinator con i dati dell'utente.
         * @param nickname il nickname inserito
         * @param statistiche le statistiche del giocatore
         * @param avatar l'avatar selezionato
         */
        void launch(String nickname, Map<String, int[]> statistiche, String avatar);
    }

    /**
     * Costruttore del controller per la schermata iniziale.
     * Gestisce click su continua
     * @param view La schermata iniziale da controllare
     * @param launcher Callback per avviare MainCoordinator
     */
    public InitialScreenController(InitialScreen view, MainCoordinatorLauncher launcher) {
        view.addContinueButtonListener(e -> {
            String nickname = view.getNickname();
            if (nickname.isEmpty()) {
                view.showMessage("Per favore inserisci un nickname!", "Errore", InitialScreen.ERROR_MESSAGE);
                return;
            }
            String avatar = view.getSelectedAvatar();
            if (avatar == null) {
                view.showMessage("Per favore seleziona un avatar!", "Errore", InitialScreen.ERROR_MESSAGE);
                return;
            }
            view.dispose(); // chiude schermata iniziale
            Map<String, int[]> statistiche = new HashMap<>();
            launcher.launch(nickname, statistiche, avatar);
        });
    }
}