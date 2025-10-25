package model;

/**
 * Rappresenta una carta da gioco con seme, valore e proprietario.
 * Gestisce anche il punteggio, l'ordine di forza.
 */
public class Carta {
    private String seme, valore, proprietario;

    /**
     * Costruttore per creare una nuova carta.
     *
     * @param seme seme della carta
     * @param valore valore della carta
     */
    public Carta(String seme, String valore) {
        this.seme = seme;
        this.valore = valore;
    }

    /** @return il seme della carta */
    public String getSeme() { return seme; }

    /** @return il valore della carta */
    public String getValore() { return valore; }

    /** @param proprietario il proprietario della carta */
    public void setProprietario(String proprietario) { this.proprietario = proprietario; }

    /** @return il proprietario della carta */
    public String getProprietario() { return proprietario; }

    /**
     * Calcola il punteggio secondo le regole del Tresette.
     *
     * @return il punteggio della carta
     */
    public int getPunteggio() {
        switch(valore) {
            case "Asso": return 3;   // 3 terzi (1 punto)
            case "3":
            case "2":
            case "Re":
            case "Cavallo":
            case "Fante":
                return 1;  // 1 terzo (1/3 di punto)
            default: return 0;  // scartine (0 punti)
        }
    }

    /**
     * Definisce l'ordine di forza delle carte per determinare chi vince la mano.
     *
     * @return l'ordine di forza della carta
     */
    public int getOrdine() {
        switch(valore) {
            case "3": return 10;
            case "2": return 9;
            case "Asso": return 8;
            case "Re": return 7;
            case "Cavallo": return 6;
            case "Fante": return 5;
            case "7": return 4;
            case "6": return 3;
            case "5": return 2;
            case "4": return 1;
            default: return 0;
        }
    }

    /**
     * Rappresentazione testuale della carta.
     *
     * @return stringa rappresentante la carta (es: "Asso di Bastoni")
     */
    @Override public String toString() { return valore + " di " + seme; }
}