package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class UpperPipe extends GameObject {
    
    /**
     * The Upper Pipe game object which ends the game if the 
     * user collides with it
     * @param filename The input stream / file path
     * @param startX The starting x-coordinate
     * @param startY The starting y-coordinate
     * @throws IOException Input output exception
     */
    public UpperPipe(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}
