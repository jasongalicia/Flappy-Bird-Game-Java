package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class Ground extends GameObject{
    
    /**
     * The Ground object which moves and ends the game if user collides with it
     * @param filename The file path or input stream
     * @param startX The x-coordinate
     * @param startY The y-coordinate
     * @throws IOException Input output exception
     */
    public Ground(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}
