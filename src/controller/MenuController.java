package controller;

import java.util.Map;

import model.TreSetteModel;
import view.Navigator;
import view.TreSetteView;
import view.screens.MainMenu;
import view.screens.StatisticsScreen;

/**
 * Gestisce le interazioni con il menu principale e coordina
 * la navigazione tra le diverse schermate dell'applicazione.
 */
public class MenuController {

    /**
     * Factory per fornire Model e View del gioco.
     */
    public interface GameFactory {
        /**
         * Crea un nuovo modello di gioco.
         * @return il modello creato
         */
        TreSetteModel createModel();

        /**
         * Crea una nuova view di gioco.
         * @return la view creata
         */
        TreSetteView createView();
    }

    /**
     * Costruttore del controller del menu.
     *
     * @param mainMenu la view del menu principale
     * @param gameFactory factory che fornisce model e view del gioco
     * @param nickname nickname del giocatore
     * @param statistiche statistiche del giocatore
     */
    public MenuController(MainMenu mainMenu, GameFactory gameFactory, String nickname, Map<String, int[]> statistiche) {
        // Listener per il pulsante di gioco
        mainMenu.setPlayListener(e -> {
            TreSetteModel model = gameFactory.createModel();
            TreSetteView gameView = gameFactory.createView();
            gameView.setModel(model);
            new TreSetteController(model, gameView);
            Navigator nav = Navigator.getInstance();
            nav.addScreen("game", gameView);
            nav.showScreen("game");
        });

        // Listener per il pulsante delle statistiche
        mainMenu.setStatsListener(e -> {
            Navigator nav = Navigator.getInstance();
            StatisticsScreen statsScreen = new StatisticsScreen(nickname, statistiche);
            statsScreen.addCloseButtonListener(ev -> nav.showScreen("menu"));
            nav.updateStatisticsScreen(statsScreen);
            nav.showScreen("stats");
        });
    }
}