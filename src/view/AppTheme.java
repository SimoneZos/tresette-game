package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.UIManager;

/**
 * Tema grafico globale dell'applicazione.
 * Definisce colori, font e stili consistenti per tutta l'interfaccia.
 */
public class AppTheme {
    /** Font per i pulsanti. */
    public static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    /** Colore di sfondo dei pulsanti. */
    public static final Color BUTTON_BG = new Color(70, 130, 180);
    /** Colore del testo dei pulsanti. */
    public static final Color BUTTON_FG = Color.WHITE;
    /** Colore di sfondo dei pulsanti del menu. */
    public static final Color MENU_BUTTON_BG = new Color(255, 140, 0);
    /** Font per i titoli. */
    public static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 20);
    /** Font per le etichette. */
    public static final Font LABEL_FONT = new Font("Arial", Font.PLAIN, 16);


    /**
     * Applica il tema grafico all'interfaccia.
     */
    public static void apply() {
        UIManager.put("Button.font", BUTTON_FONT);
        UIManager.put("Button.background", BUTTON_BG);
        UIManager.put("Button.foreground", BUTTON_FG);
        UIManager.put("Label.font", LABEL_FONT);
        UIManager.put("OptionPane.messageFont", LABEL_FONT);
        UIManager.put("OptionPane.buttonFont", BUTTON_FONT);
        UIManager.put("TitledBorder.font", LABEL_FONT);
    }
}