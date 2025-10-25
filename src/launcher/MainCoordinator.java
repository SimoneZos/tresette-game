package launcher;

import java.util.Map;

import controller.MenuController;
import model.TreSetteModel;
import view.Navigator;
import view.TreSetteView;
import view.screens.MainMenu;
import view.screens.StatisticsScreen;

/**
 * Coordina la creazione dei componenti principali dell'applicazione.
 * Setup iniziale dell'architettura MVC e collegamento tra componenti.
 */
public class MainCoordinator {
    private final MainFrame frame;

    /**
     * Inizializza l'applicazione con i dati utente.
     * Crea finestra principale, schermate e controller.
     */
    public MainCoordinator(String nickname, Map<String, int[]> statistiche, String avatar) {
        frame = new MainFrame();
        Navigator nav = Navigator.getInstance();

        // Creazione e registrazione schermate
        MainMenu mainMenu = new MainMenu(nickname, avatar);
        nav.addScreen("menu", mainMenu);
        nav.addScreen("game", new TreSetteView());
        nav.addScreen("stats", new StatisticsScreen(nickname, statistiche));

        // Controller per gestire le azioni del menu
        new MenuController(
            mainMenu,
            new MenuController.GameFactory() {
                @Override
                public TreSetteModel createModel() {
                    return new TreSetteModel(nickname, statistiche, avatar);
                }
                @Override
                public TreSetteView createView() {
                    return new TreSetteView();
                }
            },
            nickname,
            statistiche
        );

        // Mostra schermata iniziale
        frame.showScreen("menu");
        frame.setVisible(true);
    }
}