package view.components;

import java.awt.Color;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Pannello per visualizzare il log della partita.
 */
public class LogPanel extends JScrollPane {
    private JTextArea logArea;

    /** Costruttore del pannello log. */
    public LogPanel() {
        logArea = new JTextArea(10, 30);
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        logArea.setBackground(new Color(240, 240, 240));
        setViewportView(logArea);
        setBorder(BorderFactory.createTitledBorder("Log Partita"));
    }

    /**
     * Aggiorna il contenuto del log.
     *
     * @param log lista dei messaggi da visualizzare
     */
    public void update(List<String> log) {
        logArea.setText(String.join("\n", log));
        logArea.setCaretPosition(0);
    }
}