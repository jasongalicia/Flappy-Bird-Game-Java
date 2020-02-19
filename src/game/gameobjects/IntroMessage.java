package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class IntroMessage extends GameObject {
    
    /**
     * The Intro Message which shows the introduction message to the user
     * to prompt to start.
     * @param filename The input stream/filepath
     * @param startX The starting x-coordinate
     * @param startY The starting y-coordinate
     * @throws IOException Input output exception
     */
    public IntroMessage(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}
