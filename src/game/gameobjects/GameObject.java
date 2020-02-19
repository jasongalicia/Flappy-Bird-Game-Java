package game.gameobjects;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Jason Galicia
 */
public class GameObject {
    
    private int x;
    private int y;
    
    private int w;
    private int h;
    
    private Image image;
    
    /**
     * The Main Game Object class where every Game Image falls to.
     * @param filename The input stream or file path
     * @param startX The Game Object's starting X
     * @param startY The Game Object's starting Y 
     * @throws IOException Input output exception
     */
    public GameObject(InputStream filename, int startX, int startY) throws IOException {
        loadImage(filename);
        setX(startX);
        setY(startY);
    }
    
    /**
     * Loads the image for display
     * @param filename The filename/path through the inputstream
     * @throws IOException Input output exception
     */
    private void loadImage(InputStream filename) throws IOException {
        image = ImageIO.read(filename);
        
        w = image.getWidth(null);
        h = image.getHeight(null);
    }
    
    /**
     * Returns the x coordinate of the game object
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Returns the y coordinate of the game object
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Returns the width of the game object
     * @return the width coordinate
     */
    public int getWidth() {
        return w;
    }
    
    /**
     * Returns the height of the game object
     * @return the height
     */
    public int getHeight() {
        return h;
    }
    
    /**
     * Returns the image of the game object
     * @return the image
     */
    public Image getImage() {
        return image;
    }
    
    /**
     * Sets the width of the game object
     * @param data The data for the width
     */
    public void setWidth(int data) {
        w = data;
    }
    
    /**
     * Sets the height of the game object
     * @param data The data for the height
     */
    public void setHeight(int data) {
        h = data;
    }
    
    /**
     * Sets the image of the game object
     * @param newfilename The file path
     * @throws java.io.IOException Input output exception
     */
    public void setImage(InputStream newfilename) throws IOException {
        loadImage(newfilename);
    }
    
    /**
     * Moves the x coordinate by a certain amount
     * @param data The data/amount
     */
    public void moveX(int data) {
        x += data;
    }
    
    /**
     * Moves the y coordinate by a certain amount
     * @param data The data/amount
     */
    public void moveY(int data) {
        y += data;
    }
    
    /**
     * Set's the x coordinate
     * @param data The data
     */
    public void setX(int data) {
        x = data;
    }
    
    /**
     * Sets the Y Coordinate
     * @param data The data
     */
    public void setY(int data) {
        y = data;
    }
    
    /**
     * Returns the rectangle bounds of the image for collision purposes
     * @return Rectangle object
     */
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }
}
