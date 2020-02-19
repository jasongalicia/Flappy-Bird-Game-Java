package game.gameobjects;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Jason Galicia
 */
public class Flappy extends GameObject {
    
    /**
     * Flappy Game Object
     * @param filename The inputstream or file path
     * @param startX The object's starting x coordinate
     * @param startY The objects starting y coordinate
     * @throws IOException Input output exception
     */
    public Flappy(InputStream filename, int startX, int startY) throws IOException {
        super(filename, startX, startY);
        
    }
    
    /**
     * Make flappy Jump / Move his y coordinate
     * @param data The amount to move the y coordinate by
     */
    public void jump(int data) {
        moveY(data);
    }
    
    /**
     * Apply gravity so if Flappy doesn't jump, he is in free fall
     * @param data 
     */
    public void applyGravity(int data) {
        moveY(data);
    }
}
