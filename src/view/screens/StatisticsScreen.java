package view.screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Schermata delle statistiche del giocatore.
 */
public class StatisticsScreen extends JPanel {
    private final JButton closeButton;

    /**
     * Costruttore della schermata statistiche.
     *
     * @param nickname nickname del giocatore
     * @param statistiche mappa delle statistiche
     */
    public StatisticsScreen(String nickname, Map<String, int[]> statistiche) {
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        int[] stats = statistiche.getOrDefault(nickname, new int[]{0,0,0});

        JLabel titleLabel = new JLabel("Statistiche di " + nickname, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel playedLabel = new JLabel("Partite giocate: " + stats[0]);
        playedLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel wonLabel = new JLabel("Partite vinte: " + stats[1]);
        wonLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel lostLabel = new JLabel("Partite perse: " + (stats[0] - stats[1]));
        lostLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        closeButton = new JButton("Chiudi");

        mainPanel.add(titleLabel, gbc);
        mainPanel.add(playedLabel, gbc);
        mainPanel.add(wonLabel, gbc);
        mainPanel.add(lostLabel, gbc);
        mainPanel.add(closeButton, gbc);
        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Permette al controller di impostare il listener del pulsante "Chiudi".
     */
    public void addCloseButtonListener(ActionListener l) {
        closeButton.addActionListener(l);
    }
}