package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class GameScore extends GameObject{
    
    /**
     * The Game Score Game Object, which tells us our score
     * @param filename The input stream or file path
     * @param startX The starting x coordinate
     * @param startY The starting y coordinate
     * @throws IOException Input output exception
     */
    public GameScore(InputStream filename, int startX, int startY) 
            throws IOException {
        super(filename, startX, startY);
    }
}