package view;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe di utilità per la gestione dei percorsi delle immagini delle carte.
 */
public class CardImageUtil {
    private static final Map<String, String> IMMAGINI_CARTE = new HashMap<>();

    static {
        String[] semi = {"Bastoni", "Coppe", "Denari", "Spade"};
        String[] num = {"asso", "due", "tre", "quattro", "cinque", "sei", "sette", "otto", "nove", "dieci"};
        String[] nomi = {"Asso", "2", "3", "4", "5", "6", "7", "Fante", "Cavallo", "Re"};

        for (String s : semi) {
            String semeLow = s.toLowerCase();
            for (int i = 0; i < 10; i++) {
				IMMAGINI_CARTE.put(nomi[i] + "_" + s, "assets/cards/" + num[i] + "_di_" + semeLow + ".PNG");
			}
        }
    }

    /**
     * Restituisce il percorso dell'immagine per una specifica carta.
     *
     * @param valore Il valore della carta (es. "Asso", "2", "Fante")
     * @param seme Il seme della carta (es. "Bastoni", "Coppe")
     * @return Il percorso del file immagine o null se non trovato
     */
    public static String getImagePath(String valore, String seme) {
        return IMMAGINI_CARTE.getOrDefault(valore + "_" + seme, null);
    }
}