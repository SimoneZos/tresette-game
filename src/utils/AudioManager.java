package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Gestione audio dell'applicazione (pattern Singleton).
 */
public class AudioManager {
    private static AudioManager instance;

    /**
     * Restituisce l'istanza singleton di AudioManager.
     *
     * @return l'istanza di AudioManager
     */
    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    /** Costruttore privato per il pattern Singleton. */
    private AudioManager() {}

    /**
     * Riproduce un file audio.
     *
     * @param filename nome del file audio da riprodurre
     */
    public void play(String filename) {
        try {
            String audioPath = "assets/audio/" + filename;
            InputStream in = new BufferedInputStream(new FileInputStream(audioPath));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (FileNotFoundException e) {
            System.err.println("File audio non trovato: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Errore di I/O durante la lettura del file audio");
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato file audio non supportato");
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            System.err.println("Linea audio non disponibile");
            e.printStackTrace();
        }
    }

    /**
     * Metodo statico rapido per riprodurre un suono.
     *
     * @param soundFileName nome del file audio da riprodurre
     */
    public static void playSound(String soundFileName) {
        getInstance().play(soundFileName);
    }
}