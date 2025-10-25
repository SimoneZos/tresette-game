package view;

import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.JPanel;

/**
 * Gestisce il cambio tra le schermate dell'applicazione (pattern Singleton).
 * Utilizza un cardlayout per mostrare diverse schermate.
 */
public class Navigator extends JPanel {
    private static Navigator instance;
    private final CardLayout cardLayout;

    /** Costruttore privato per il pattern Singleton. */
    private Navigator() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
    }

    /**
     * Restituisce l'istanza singleton di Navigator.
     *
     * @return l'istanza di Navigator
     */
    public static Navigator getInstance() {
        if (instance == null) {
            instance = new Navigator();
        }
        return instance;
    }

    /**
     * Aggiunge una schermata al navigator.
     *
     * @param name nome della schermata
     * @param panel pannello della schermata
     */
    public void addScreen(String name, JPanel panel) {
        panel.setName(name);
        add(panel, name);
    }

    /**
     * Mostra una specifica schermata.
     *
     * @param name nome della schermata da mostrare
     */
    public void showScreen(String name) {
        cardLayout.show(this, name);
    }

    /**
     * Aggiorna la schermata delle statistiche.
     *
     * @param statsScreen pannello della schermata statistiche già istanziato dal controller
     */
    public void updateStatisticsScreen(JPanel statsScreen) {
        remove(getComponent(2));
        addScreen("stats", statsScreen);
        revalidate();
        repaint();
    }

    /**
     * Restituisce la schermata associata al nome.
     *
     * @param name nome della schermata
     * @return il pannello della schermata, o null se non esiste
     */
    public JPanel getScreen(String name) {
        for (Component c : getComponents()) {
            if (name.equals(c.getName())) {
                return (JPanel) c;
            }
        }
        return null;
    }
}