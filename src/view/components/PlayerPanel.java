package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Carta;
import model.Giocatore;

/**
 * Pannello per visualizzare le carte del giocatore umano.
 */
public class PlayerPanel extends JPanel {

    /** Costruttore del pannello giocatore. */
    public PlayerPanel() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
    }

    /**
     * Aggiorna la visualizzazione del giocatore.
     *
     * @param player il giocatore da visualizzare
     * @param cardListener il listener per le carte
     */
    public void update(Giocatore player, ActionListener cardListener) {
        removeAll();
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        infoPanel.setOpaque(false);
        if (player.getAvatar() != null) {
            ImageIcon icon = new ImageIcon(player.getAvatar());
            Image img = icon.getImage().getScaledInstance(48, 48, Image.SCALE_SMOOTH);
            infoPanel.add(new JLabel(new ImageIcon(img)));
        }
        JLabel playerLabel = new JLabel("<html><center><b>" + player.getNome() + "</b><br>(" + player.getCarte().size() + " carte)</center></html>", SwingConstants.CENTER);
        playerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerLabel.setForeground(Color.WHITE);
        infoPanel.add(playerLabel);
        add(infoPanel);
        JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        cardsPanel.setOpaque(false);
        for (Carta c : player.getCarte()) {
            String imagePath = view.CardImageUtil.getImagePath(c.getValore(), c.getSeme());
            JButton cardButton;
            if (imagePath == null) {
                cardButton = new JButton(); // bottone vuoto se manca immagine
            } else {
                ImageIcon cardIcon = new ImageIcon(imagePath);
                Image scaled = cardIcon.getImage().getScaledInstance(80, 120, Image.SCALE_SMOOTH);
                cardButton = new JButton(new ImageIcon(scaled));
            }
            cardButton.setPreferredSize(new Dimension(80, 120));
            cardButton.setBackground(Color.WHITE);
            cardButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            cardButton.setToolTipText(c.toString());
            if (cardListener != null) {
                cardButton.addActionListener(cardListener);
            }
            cardButton.setActionCommand(c.toString());
            cardsPanel.add(cardButton);
        }
        add(cardsPanel);
        revalidate();
        repaint();
    }
}