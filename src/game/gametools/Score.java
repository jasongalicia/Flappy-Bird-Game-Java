package game.gametools;

/**
 *
 * @author Jason Galicia
 */
public class Score {
    
    private int score;
    private String name;
    
    /**
     * The Score object which tells us the Score along with the name of the user
     * @param name The name
     * @param score The score as an integer variable
     */
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    /**
     * Returns the score
     * @return The score as an integer
     */
    public int getScore() {
        return score;
    }
    
    /**
     * Returns the Name 
     * @return The name as a String
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returning the instance of the class as a String
     * @return A String of the name and the score in a particular format
     */
    @Override
    public String toString() {
        return name + ": " + score;
    }
}
