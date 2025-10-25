package view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Carta;

/**
 * Pannello per visualizzare le carte sul tavolo di gioco.
 */
public class TablePanel extends JPanel {
    private List<Carta> carteSulTavolo;
    /** Costruttore del pannello tavolo. */
    public TablePanel() {
        setOpaque(true);
        setBackground(new Color(0, 100, 0));
        setPreferredSize(new Dimension(400, 300));
    }

    /**
     * Aggiorna la lista delle carte sul tavolo.
     *
     * @param carteSulTavolo lista delle carte da visualizzare
     */
    public void update(List<Carta> carteSulTavolo) {
        this.carteSulTavolo = carteSulTavolo;
        repaint();
    }

    /** 
     * Disegna il componente, mostrando le carte sul tavolo e i loro proprietari.
     * Se non ci sono carte, visualizza un messaggio. Ogni carta viene disegnata
     * come immagine con sotto il nome del proprietario.
     * 
     * @param g il contesto grafico per disegnare.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 22));
        if (carteSulTavolo == null || carteSulTavolo.isEmpty()) {
            g.drawString("Nessuna carta sul tavolo", getWidth()/2 - 100, getHeight()/2);
            return;
        }

        int cardWidth = 80, cardHeight = 120, spacing = 30;
        int totalWidth = carteSulTavolo.size() * (cardWidth + spacing) - spacing;
        int startX = (getWidth() - totalWidth) / 2;
        int yImage = getHeight() / 2 - cardHeight / 2;
        int yText = yImage + cardHeight + 25;

        for (int i = 0; i < carteSulTavolo.size(); i++) {
            Carta carta = carteSulTavolo.get(i);
            String nomeProprietario = carta.getProprietario();

            String imagePath = view.CardImageUtil.getImagePath(carta.getValore(), carta.getSeme());
            int x = startX + i * (cardWidth + spacing);

            if (imagePath != null) {
                Image img = new ImageIcon(imagePath).getImage();
                g.drawImage(img, x, yImage, cardWidth, cardHeight, null);
            } else {
                g.setColor(Color.RED);
                g.fillRect(x, yImage, cardWidth, cardHeight);
                g.setColor(Color.BLACK);
                g.drawRect(x, yImage, cardWidth, cardHeight);
                g.setColor(Color.WHITE);
                g.drawString("IMG?", x + 10, yImage + cardHeight / 2);
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            int textWidth = g.getFontMetrics().stringWidth(nomeProprietario);
            g.drawString(nomeProprietario, x + (cardWidth - textWidth) / 2, yText);
        }
    }
}