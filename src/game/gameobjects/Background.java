package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class Background extends GameObject {
    
    /**
     * The Background GameObject
     * @param filename The input stream / path to the file
     * @param startX The starting X Coordinate
     * @param startY The Starting Y Coordinate
     * @throws IOException Input Output exception
     */
    public Background(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}
