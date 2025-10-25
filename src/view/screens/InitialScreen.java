package view.screens;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Schermata iniziale per inserimento nickname e selezione avatar.
 * Gestisce l'interfaccia utente per la configurazione iniziale del gioco.
 */
public class InitialScreen extends JFrame {
    private final JTextField nickField = new JTextField(20);
    private final JToggleButton[] avatarButtons;
    private final JButton continueButton = new JButton("Continua");
    private static final String[] avatarPaths = {
        "assets/avatars/avatar1.png",
        "assets/avatars/avatar2.png",
        "assets/avatars/avatar3.png"
    };

    // Tipi di messaggio per dialog
    public static final int ERROR_MESSAGE = JOptionPane.ERROR_MESSAGE;
    public static final int WARNING_MESSAGE = JOptionPane.WARNING_MESSAGE;
    public static final int INFORMATION_MESSAGE = JOptionPane.INFORMATION_MESSAGE;

    /**
     * Costruttore della schermata iniziale.
     * Inizializza i componenti UI e layout della finestra.
     */
    public InitialScreen() {
        super("TreSette - Benvenuto");
        setSize(500, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Benvenuto in TreSette!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JPanel avatarPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        ButtonGroup avatarGroup = new ButtonGroup();
        avatarButtons = new JToggleButton[avatarPaths.length];
        for (int i = 0; i < avatarPaths.length; i++) {
        	Image img = new ImageIcon(avatarPaths[i]).getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            JToggleButton btn = new JToggleButton(new ImageIcon(img));
            btn.setPreferredSize(new Dimension(72, 72));
            btn.setActionCommand(avatarPaths[i]);
            avatarGroup.add(btn);
            avatarPanel.add(btn);
            avatarButtons[i] = btn;
        }
        avatarButtons[0].setSelected(true);

        continueButton.setBackground(new Color(70, 130, 180));
        continueButton.setForeground(Color.WHITE);
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(titleLabel, gbc);
        mainPanel.add(new JLabel("Inserisci il tuo nickname:"), gbc);
        mainPanel.add(nickField, gbc);
        mainPanel.add(new JLabel("Scegli il tuo avatar:"), gbc);
        mainPanel.add(avatarPanel, gbc);
        mainPanel.add(continueButton, gbc);
        add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    /**
     * Restituisce il nickname inserito dall'utente.
     *
     * @return Il testo del nickname, trimizzato
     */
    public String getNickname() {
        return nickField.getText().trim();
    }

    /**
     * Restituisce il percorso dell'avatar selezionato.
     *
     * @return Il percorso dell'avatar selezionato, null se nessuno selezionato
     */
    public String getSelectedAvatar() {
        for (JToggleButton btn : avatarButtons) {
            if (btn.isSelected()) {
                return btn.getActionCommand();
            }
        }
        return null;
    }

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

    /**
     * Aggiunge un listener al pulsante Continua.
     *
     * @param listener Il listener da registrare per il pulsante
     */
    public void addContinueButtonListener(ActionListener listener) {
        continueButton.addActionListener(listener);
    }
}