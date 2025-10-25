package view.screens;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


/**
 * Menu principale dell'applicazione.
 */
public class MainMenu extends JPanel {
    private final JButton playButton;
    private final JButton statsButton;

    /**
     * Costruttore del menu principale.
     *
     * @param nickname nickname del giocatore
     * @param avatar avatar del giocatore
     */
    public MainMenu(String nickname, String avatar) {
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        // Avatar
        if (avatar != null) {
             ImageIcon icon = new ImageIcon(avatar);
             Image img = icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
             JLabel avatarPic = new JLabel(new ImageIcon(img));
             mainPanel.add(avatarPic, gbc);
            }


        JLabel welcomeLabel = new JLabel("Benvenuto, " + nickname + "!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(welcomeLabel, gbc);

        playButton = new JButton("Gioca");
        statsButton = new JButton("Statistiche");
        mainPanel.add(playButton, gbc);
        mainPanel.add(statsButton, gbc);
        add(mainPanel, BorderLayout.CENTER);

    }

    /**
     * Permette di impostare un listener per il pulsante "Gioca".
     * @param l listener da notificare quando si preme "Gioca"
     */
    public void setPlayListener(ActionListener l) {
        playButton.addActionListener(l);
    }

    /**
     * Permette di impostare un listener per il pulsante "Statistiche".
     * @param l listener da notificare quando si preme "Statistiche"
     */
    public void setStatsListener(ActionListener l) {
        statsButton.addActionListener(l);
    }
}