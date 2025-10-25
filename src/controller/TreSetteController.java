package controller;

import model.TreSetteModel;
import view.TreSetteView;

/**
 * Controller principale che collega modello e view.
 * Coordina gli altri controller del gioco.
 */
public class TreSetteController {

    /**
     * Costruttore del controller principale.
     *
     * @param model il modello del gioco
     * @param view la view del gioco
     */
    public TreSetteController(TreSetteModel model, TreSetteView view) {
        GameController gc = new GameController(model, view); // logica di gioco
        model.addObserver(view);
        model.addObserver(gc);
    }
}