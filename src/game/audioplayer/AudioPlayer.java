package game.audioplayer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Jason Galicia
 */
public class AudioPlayer {
    
    private AudioInputStream audioIn;
    private Clip clip;
    
    /**
     * Plays the sound from the file passed on to it
     * @param filename The filename of the sound
     */
    public void playSound(InputStream filename) {
        try {
            if (filename == null) return;
            audioIn = AudioSystem.getAudioInputStream(filename);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (IOException | LineUnavailableException | 
                UnsupportedAudioFileException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    /**
     * Loads the audio and returns true if it was successful and false if it
     * wasn't
     * @param filename The filename
     * @return If The audio has loaded or not
     * @throws UnsupportedAudioFileException Unsupported Audio exception
     * @throws IOException Inpput output exception
     * @throws LineUnavailableException 
     */
    public boolean loadAudio(String filename) throws 
            UnsupportedAudioFileException, IOException, 
            LineUnavailableException {
        if (filename == null) return false;
        audioIn = AudioSystem.getAudioInputStream(new File(filename));
        clip = AudioSystem.getClip();
        clip.open(audioIn);
            
        return clip.isOpen();
    }
    
    /**
     * If ever needed, this will stop the audio player from playing the sound
     */
    public void stop() {
        clip.stop();
    }
}
