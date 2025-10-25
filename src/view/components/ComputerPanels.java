package view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Giocatore;

/**
 * Pannello per visualizzare le carte degli avversari CPU.
 */
public class ComputerPanels extends JPanel {

    /** Costruttore del pannello computer. */
    public ComputerPanels() {
        super(new GridLayout(1, 3, 20, 0));
        setOpaque(false);
    }

    /**
     * Aggiorna la visualizzazione dei giocatori computer.
     *
     * @param giocatori lista dei giocatori
     */
    public void update(List<Giocatore> giocatori) {
        removeAll();
        for (int i = 1; i < giocatori.size(); i++) {
            Giocatore g = giocatori.get(i);
            JPanel panel = new JPanel(new BorderLayout());
            panel.setOpaque(false);
            panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
            String squadra = (i == 2) ? "<span style='color: #00FF00;'>(TUA SQUADRA)</span>" : "";
            JLabel nameLabel = new JLabel("<html><center><b>" + g.getNome() + "</b> " + squadra + "<br>(" + g.getCarte().size() + " carte)</center></html>", SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
            nameLabel.setForeground(Color.WHITE);
            JPanel cardsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
            cardsPanel.setOpaque(false);
            for (int j = 0; j < g.getCarte().size(); j++) {
                cardsPanel.add(new JLabel(new ImageIcon(createCardImage(true, 36, 54))));
            }
            panel.add(nameLabel, BorderLayout.NORTH);
            panel.add(cardsPanel, BorderLayout.CENTER);
            add(panel);
        }
        revalidate();
        repaint();
    }

    /**
     * Crea un'immagine di carta coperta o scoperta.
     *
     * @param coperta true per carta coperta, false per scoperta
     * @param width larghezza dell'immagine
     * @param height altezza dell'immagine
     * @return l'immagine della carta
     */
    private Image createCardImage(boolean coperta, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        java.awt.Graphics g = image.getGraphics();
        if (coperta) {
            g.setColor(new Color(20, 60, 120));
            g.fillRoundRect(0, 0, width-1, height-1, 8, 8);
            g.setColor(Color.WHITE);
            g.drawRoundRect(0, 0, width-1, height-1, 8, 8);
        } else {
            g.setColor(Color.WHITE);
            g.fillRoundRect(0, 0, width-1, height-1, 8, 8);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, width-1, height-1, 8, 8);
        }
        g.dispose();
        return image;
    }
}