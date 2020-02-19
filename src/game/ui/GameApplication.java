package game.ui;

import game.audioplayer.AudioPlayer;
import game.globals.GlobalPath;
import java.io.IOException;
import javax.swing.JFrame;
/**
 *
 * @author Jason Galicia
 */
public class GameApplication extends JFrame {
    
    private GameEngine engine;
    
    /**
     * The constructor which allows us to start the game with the necessary 
     * files
     * @param username The username of the person logging in
     * @param backgroundFile The background of the game
     * @param player The Audio Player which will play music
     * @param midFlap Flappy Bird's mid flap position
     * @param downFlap Flappy Bird's down flap position
     * @param upFlap Flappy Bird's up flap position
     * @param upperPipeFile The upper pipe file depending whether its night or 
     * day
     * @param lowerPipeFile The lower pipe file depending whether its night or 
     * day
     * @throws java.io.IOException
     */
    public GameApplication(String username, String backgroundFile, AudioPlayer player, 
            String midFlap, String downFlap, String upFlap, 
            String upperPipeFile,String lowerPipeFile) throws IOException {
        initialize(username, backgroundFile, player, midFlap, downFlap, upFlap,
                upperPipeFile, lowerPipeFile);
    }
    
    /**
     * Initializes the components for the JFrame
     * @param backgroundFile The background file
     * @param player The audio player
     * @param midFlap Flappy's mid flap position
     * @param downFlap Flappy's down flap position
     * @param upFlap Flappy's upflap position
     * @param upperPipeFile The upper pipe file depending whether its night or 
     * day
     * @param lowerPipeFile The lower pipe file depending whether its night or 
     * day
     */
    private void initialize(String username, String backgroundFile, 
            AudioPlayer player, String midFlap, String downFlap, String upFlap, 
            String upperPipeFile, String lowerPipeFile) throws IOException {
        engine = new GameEngine(this, username, backgroundFile, player, midFlap, downFlap, 
                upFlap, upperPipeFile, lowerPipeFile); 
        add(engine);
        
        setTitle(GlobalPath.APP_TITLE);
        setSize(GlobalPath.CANVAS_WIDTH, GlobalPath.CANVAS_HEIGHT);
        
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
