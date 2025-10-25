package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.TreSetteModel;
import view.components.ComputerPanels;
import view.components.LogPanel;
import view.components.PlayerPanel;
import view.components.TablePanel;

/**
 * Schermata principale di gioco.
 * Implementa Observer per ricevere aggiornamenti dal modello.
 */
public class TreSetteView extends JPanel implements Observer {
	private final PlayerPanel playerPanel = new PlayerPanel();
    private final ComputerPanels computerPanels = new ComputerPanels();
    private final TablePanel tablePanel = new TablePanel();
    private final LogPanel logPanel = new LogPanel();
    private final JButton playButton = new JButton("Nuova Partita");
    private final JButton menuButton = new JButton("Menu");
    private final JPanel buttonPanel = new JPanel();
    private TreSetteModel model;
    private ActionListener cardListener;

    // Tipi di messaggio per dialog
    public static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
    public static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
    public static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;

    /** Costruttore della view di gioco. */
    public TreSetteView() {
        setLayout(new BorderLayout());
        buttonPanel.add(playButton);
        buttonPanel.add(menuButton);
        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(new Color(0, 100, 0));
        gamePanel.add(computerPanels, BorderLayout.NORTH);
        gamePanel.add(tablePanel, BorderLayout.CENTER);
        gamePanel.add(playerPanel, BorderLayout.SOUTH);
        add(gamePanel, BorderLayout.CENTER);
        add(logPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.SOUTH);

        for (JButton btn : new JButton[]{playButton, menuButton}) {
            btn.setFocusPainted(false);
            btn.setContentAreaFilled(true);
            btn.setOpaque(true);
        }
        playButton.setBackground(new Color(70, 130, 180));
        menuButton.setBackground(new Color(255, 140, 0));
    }

    /**
     * Collega il modello alla view.
     * @param model il modello da collegare alla view
     */
    public void setModel(TreSetteModel model) { this.model = model; }

    /**
     * Imposta il listener per le carte.
     *
     * @param listener il listener da impostare
     */
    public void setCardListener(ActionListener listener) {
        this.cardListener = listener;
    }
    /**
     * Imposta il listener per il pulsante "Menu".
     *
     * @param listener il listener da impostare
     */
    public void setMenuButtonListener(ActionListener listener) {
        menuButton.addActionListener(listener);
    }

    /** 
	 * Aggiorna tutti i componenti della view quando il model cambia.
	 * 
	 * @param o il model osservabile
	 * @param arg argomento passato dalla notifica
     */
    @Override
    public void update(Observable o, Object arg) {
        if (model == null) {
			return;
		}
        computerPanels.update(model.getGiocatori());
        playerPanel.update(model.getGiocatori().get(0), cardListener);
        tablePanel.update(model.getCarteSulTavolo());
        logPanel.update(model.getLog());
    }
    /**
     * Aggiunge un listener al pulsante Nuova Partita.
     *
     * @param listener il listener da registrare
     */
    public void addPlayButtonListener(ActionListener listener) {
        playButton.addActionListener(listener);
    }

    /**
     * Restituisce il pulsante "Nuova Partita".
     * @return il pulsante playButton
     */
    public JButton getPlayButton() { return playButton; }
    /**
     * Restituisce il pulsante "Menu".
     * @return il pulsante menuButton
     */
    public JButton getMenuButton() { return menuButton; }
    /**
     * Restituisce il pannello del giocatore.
     * @return il pannello playerPanel
     */
    public PlayerPanel getPlayerPanel() { return playerPanel; }
    /**
     * Restituisce il pannello dei computer.
     * @return il pannello computerPanels
     */
    public ComputerPanels getComputerPanels() { return computerPanels; }
    /**
     * Restituisce il pannello del tavolo.
     * @return il pannello tablePanel
     */
    public TablePanel getTablePanel() { return tablePanel; }
    /**
     * Restituisce il pannello del log.
     * @return il pannello logPanel
     */
    public LogPanel getLogPanel() { return logPanel; }
    /**
     * Restituisce il modello collegato alla view.
     * @return il modello TreSetteModel
     */
    public TreSetteModel getModel() { return model; }

    /**
     * Mostra un messaggio all'utente tramite dialog.
     *
     * @param message il messaggio da mostrare
     * @param title il titolo della finestra
     * @param messageType il tipo di messaggio (JOptionPane constant)
     */
    public void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }
}