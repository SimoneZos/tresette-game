package launcher;

import javax.swing.SwingUtilities;

import controller.InitialScreenController;
import view.AppTheme;
import view.screens.InitialScreen;

/**
 * Classe principale per l'avvio dell'applicazione.
 */
public class JTresette {

    /**
     * Metodo principale di avvio dell'applicazione.
     */
    public static void main(String[] args) {
        AppTheme.apply();
        SwingUtilities.invokeLater(() -> {
            InitialScreen initialScreen = new InitialScreen();
            new InitialScreenController(
                initialScreen,
                (nickname, statistiche, avatar) -> new MainCoordinator(nickname, statistiche, avatar)
            );
            initialScreen.setVisible(true);
        });
    }
}
