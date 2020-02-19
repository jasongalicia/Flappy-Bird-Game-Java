package game.scores;

import game.globals.GlobalPath;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.swing.JOptionPane;

/**
 *
 * @author Jason Galicia
 */
public class SaveScore {
    
    // The driver and other connection variables
    private Connection con;
    private String driver;
    private String url;
    private String username;
    private String password;
    
    /**
     * Method is save the score into a database.
     */
    public SaveScore() {
        initConnectVars();
        createDB();
    }
    
    /**
     * Initializes the connection variables to the database.....................
     */
    private void initConnectVars() {
        driver = GlobalPath.DB_DRIVER;
        url = GlobalPath.DB_URL;
        username = GlobalPath.DB_USERNAME;
        password = GlobalPath.DB_PASSWORD;
    }
    
    /**
     * Runs the process creating the table needed if it does not exist for
     * whatever reason....
     */
    private void createDB() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connected To The Database Successfully.");
            
            // Create the table if it does not exist............................
            String statement = "CREATE TABLE IF NOT EXISTS flappy_scores"
                    + "(id int NOT NULL AUTO_INCREMENT, name varchar(255), "
                    + "score INT," + " PRIMARY KEY(id))";
            PreparedStatement create = con.prepareStatement(statement);
            create.executeUpdate();
            System.out.println("Table has/already has been created.");
        }
        catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Runs the process inserting information into the database....
     * @param name The name of the player.
     * @param score The score they achieved.
     */
    public void insertNameAndScore(String name, int score) {
        try {
            // Get today's date
            LocalDate date = LocalDate.now();
          // Insert the name and scores into the database.....................
            String insertStatement = "INSERT INTO flappy_scores (username, "
                    + "score, date_scored)"
                    + "VALUES ('"+name+"','"+score+"', '"+date+"')";
            PreparedStatement insert = con.prepareStatement(insertStatement);
            insert.executeUpdate();
            System.out.println("Data has been entered into the system.");
            JOptionPane.showMessageDialog(null, "Your score was saved!");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Your score was not saved. "
                    + "Error on server side.");
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
