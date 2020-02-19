package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class LowerPipe extends GameObject {
    
    /**
     * The Lower Pipe game object which ends the game if the 
     * user collides with it
     * @param filename The input stream / file path
     * @param startX The starting x-coordinate
     * @param startY The starting y-coordinate
     * @throws IOException Input output exception
     */
    public LowerPipe(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}
