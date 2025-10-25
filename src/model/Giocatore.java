package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta un giocatore (umano o CPU) con nome, avatar e carte in mano.
 */
public class Giocatore {
    private String nome;
    private boolean umano;
    private String avatar;
    private List<Carta> carte = new ArrayList<>();

    /**
     * Costruttore per giocatore con nome, tipo e avatar.
     *
     * @param nome nome del giocatore
     * @param umano true se è umano, false se CPU
     * @param avatar percorso dell'avatar
     */
    public Giocatore(String nome, boolean umano, String avatar) {
        this.nome = nome;
        this.umano = umano;
        this.avatar = avatar;
    }

    /**
     * Costruttore per giocatore senza avatar.
     *
     * @param nome nome del giocatore
     * @param umano true se è umano, false se CPU
     */
    public Giocatore(String nome, boolean umano) {
        this(nome, umano, null);
    }

    /**
     * Restituisce il nome del giocatore.
     * @return il nome del giocatore
     */
    public String getNome() { return nome; }

    /**
     * Indica se il giocatore è umano.
     * @return true se il giocatore è umano
     */
    public boolean isUmano() { return umano; }

    /**
     * Restituisce il percorso dell'avatar.
     * @return il percorso dell'avatar
     */
    public String getAvatar() { return avatar; }

    /**
     * Restituisce la lista delle carte in mano.
     * @return la lista delle carte in mano
     */
    public List<Carta> getCarte() { return carte; }

    /**
     * Aggiunge una carta alla mano del giocatore.
     *
     * @param carta la carta da aggiungere
     */
    public void addCarta(Carta carta) { carte.add(carta); }

    /**
     * Rimuove una carta dalla mano del giocatore.
     *
     * @param carta la carta da rimuovere
     */
    public void removeCarta(Carta carta) { carte.remove(carta); }
}