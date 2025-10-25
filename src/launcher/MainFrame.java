package launcher;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import view.Navigator;

/**
 * Finestra principale dell'applicazione TreSette.
 * Responsabilità: solo gestione finestra e navigazione.
 */
public class MainFrame extends JFrame {
    /** Larghezza predefinita della finestra. */
    public static final int FRAME_WIDTH = 1200;
    /** Altezza predefinita della finestra. */
    public static final int FRAME_HEIGHT = 850;

    /**
     * Costruttore principale che configura la finestra.
     */
    public MainFrame() {
        setTitle("TreSette");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setMinimumSize(new java.awt.Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setLocationRelativeTo(null);
        setContentPane(Navigator.getInstance());
    }

    /**
     * Mostra la schermata specificata.
     * @param name nome della schermata
     */
    public void showScreen(String name) {
        Navigator.getInstance().showScreen(name);
    }
}