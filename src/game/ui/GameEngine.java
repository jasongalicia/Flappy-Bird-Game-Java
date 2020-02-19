package game.ui;

import game.audioplayer.AudioPlayer;
import game.gameobjects.Background;
import game.gameobjects.Flappy;
import game.gameobjects.GameObject;
import game.gameobjects.GameScore;
import game.gameobjects.Ground;
import game.gameobjects.LowerPipe;
import game.gameobjects.UpperPipe;
import game.globals.GlobalPath;
import game.scores.SaveScore;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jason Galicia
 */
public class GameEngine extends JPanel implements ActionListener, KeyListener, 
        MouseListener {
    // All of the instance variables............................................
    // The username of the user who is logged in
    private String username;
    private GameApplication app;
    // Rendering Background, Ground and Pipe purposes
    private ArrayList<Background> bgList;
    private ArrayList<Ground> gList;
    private ArrayList<UpperPipe> upperPipeList;
    private ArrayList<LowerPipe> lowerPipeList;
    private ArrayList<GameScore> displayScoreList;    
    // Game Pieces
    private Flappy flappy;
    private Background background;
    private String backgroundFile;
    private Ground ground;
    private GameObject introMsg;
    private GameObject gameOverMsg;
    private AudioPlayer player;
    private UpperPipe uPipe;
    private LowerPipe lPipe;
    private String midFlap, downFlap, upFlap;
    private String upperPipeFile, lowerPipeFile;
   
    // Needed to run the game smoothly upon a timer 
    private Timer timer;
    private int ticks;
    
    // Check the state of the game
    private boolean gameOver;
    private boolean gameStarted;
    private int playerPoints;
    private int yMotion;
    private int angleCounter;
    private Random random;
    
    /**
     * The constructor to initiate the game
     * @param app The JFrame for the actual game
     * @param username Username of the player that is playing
     * @param backgroundFile The background for the game
     * @param player The audio player for sound effects
     * @param midFlap The mid flap position of the player
     * @param downFlap The down flap position of the player
     * @param upFlap The up flap position of the player
     * @param upperPipeFile The upper pipe file
     * @param lowerPipeFile The lower pipe file
     * @throws java.io.IOException
     */
    public GameEngine(GameApplication app, String username, 
            String backgroundFile, AudioPlayer player, String midFlap, 
            String downFlap, String upFlap, String upperPipeFile, 
            String lowerPipeFile) throws IOException {
        this.app = app;
        this.username = username;
        this.player = player;
        this.backgroundFile = backgroundFile;
        this.midFlap = midFlap;
        this.downFlap = downFlap;
        this.upFlap = upFlap;
        this.upperPipeFile = upperPipeFile;
        this.lowerPipeFile = lowerPipeFile;
        random = new Random();
        
        initialize();
    }
   
   /**
    * Finalizes or just removes the Mouse and Key Listener
    * @throws Throwable 
    */
   @Override
   protected void finalize() throws Throwable {
        try {
            removeAll();
            removeMouseListener(this);
            removeKeyListener(this);
        } finally {
            super.finalize();
        }
    }
   
   /**
    * Initializes all the game pieces when the user wants to run the program
    * again
    * @param midFlap Flappy's Mid Flap Position
    * @param upFlap Flappy's Up Flap Position
    * @param downFlap Flappy's Down Flap Position
    * @param backgroundFile The background file
    * @param upperPipe The upper pipe file
    * @param lowerPipe The lower pipe file
    */
    private void initialize(String midFlap, String upFlap, String downFlap, 
            String backgroundFile, String upperPipe, String lowerPipe) 
            throws IOException {
        this.midFlap = midFlap;
        this.downFlap = downFlap;
        this.upFlap = upFlap;
        this.upperPipeFile = upperPipe;
        this.lowerPipeFile = lowerPipe;
        
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        
        yMotion = 0;
        angleCounter = 0;
        playerPoints = 0;
        ticks = 0;
        gameOver = false; 
        gameStarted = false;
     
        flappy = new Flappy(GameEngine.class.getResourceAsStream(midFlap), 
                GlobalPath.FLAPPY_START_X, GlobalPath.FLAPPY_START_Y);
        background = new Background(GameEngine.class.getResourceAsStream
                (backgroundFile), 
                GlobalPath.BACKGROUND_START_X, GlobalPath.BACKGROUND_START_Y);
        ground = new Ground(GameEngine.class.getResourceAsStream
        (GlobalPath.GROUND), GlobalPath.GROUND_START_X, 
                GlobalPath.GROUND_START_Y);
        introMsg = new GameObject(GameEngine.class.getResourceAsStream
                (GlobalPath.INTRO_MESSAGE), GlobalPath.INTRO_MESSAGE_X, 
                    GlobalPath.INTRO_MESSAGE_Y);
        gameOverMsg = new GameObject(GameEngine.class.getResourceAsStream
                (GlobalPath.GAME_OVER_MSG), GlobalPath.GAMEOVER_MSG_X, 
                    GlobalPath.GAMEOVER_MSG_Y);
        
        displayScoreList = new ArrayList<>();
        bgList = new ArrayList<>();
        gList = new ArrayList<>();
        
        lowerPipeList = new ArrayList<>();
        upperPipeList = new ArrayList<>();
        
        gList.add(ground);
        bgList.add(background);
        
        Background bgtemp = new Background(GameEngine.class.getResourceAsStream
            (backgroundFile), GlobalPath.BACKGROUND_START_X, 
                GlobalPath.BACKGROUND_START_Y);
        bgtemp.setX(background.getX() + GlobalPath.BACKGROUND_SPACE);
        bgList.add(bgtemp);
        
        Ground gtemp = new Ground(GameEngine.class.getResourceAsStream
            (GlobalPath.GROUND), GlobalPath.GROUND_START_X,
                GlobalPath.GROUND_START_Y);
        gtemp.setX(ground.getX() + GlobalPath.GROUND_SPACE);
        gList.add(gtemp);
        
        renderStartingPipes();
        loadScoreImages();
        
        timer = new Timer(GlobalPath.DELAY, this);
        start();
    }
    
    /**
     * Initializes all game pieces when the user first opens the application
     */
    private void initialize() throws IOException {
        // Mouse and key listeners
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        
        yMotion = 0;
        angleCounter = 0;
        playerPoints = 0;
        ticks = 0;
        gameOver = false; 
        gameStarted = false;
        
        flappy = new Flappy(GameEngine.class.getResourceAsStream(midFlap), 
                GlobalPath.FLAPPY_START_X, GlobalPath.FLAPPY_START_Y);
        background = new Background(GameEngine.class.getResourceAsStream
                (backgroundFile), GlobalPath.BACKGROUND_START_X, 
                GlobalPath.BACKGROUND_START_Y);
        ground = new Ground(GameEngine.class.getResourceAsStream
                (GlobalPath.GROUND), GlobalPath.GROUND_START_X, 
                GlobalPath.GROUND_START_Y);
        introMsg = new GameObject(GameEngine.class.getResourceAsStream
                (GlobalPath.INTRO_MESSAGE), GlobalPath.INTRO_MESSAGE_X, 
                GlobalPath.INTRO_MESSAGE_Y);
        gameOverMsg = new GameObject(GameEngine.class.getResourceAsStream
                (GlobalPath.GAME_OVER_MSG), GlobalPath.GAMEOVER_MSG_X, 
                GlobalPath.GAMEOVER_MSG_Y);
        
        displayScoreList = new ArrayList<>();
        bgList = new ArrayList<>();
        gList = new ArrayList<>();
        
        lowerPipeList = new ArrayList<>();
        upperPipeList = new ArrayList<>();
        
        gList.add(ground);
        bgList.add(background);
        
        Background bgtemp = new Background(GameEngine.class.getResourceAsStream
            (backgroundFile), GlobalPath.BACKGROUND_START_X, 
                GlobalPath.BACKGROUND_START_Y);
        bgtemp.setX(background.getX() + GlobalPath.BACKGROUND_SPACE);
        bgList.add(bgtemp);
        
        Ground gtemp = new Ground(GameEngine.class.getResourceAsStream
            (GlobalPath.GROUND), GlobalPath.GROUND_START_X,
                GlobalPath.GROUND_START_Y);
        gtemp.setX(ground.getX() + GlobalPath.GROUND_SPACE);
        gList.add(gtemp);
        
        renderStartingPipes();
        loadScoreImages();
        
        timer = new Timer(GlobalPath.DELAY, this);
        start();
    }
    
    /**
     * Loads the numbers for the score
     */
    private void loadScoreImages() throws IOException {
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_0), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_1), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_2), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_3), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_4), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_5), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_6), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_7), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_8), 0 ,0));
        displayScoreList.add(new GameScore(GameEngine.class.getResourceAsStream
            (GlobalPath.SCORE_9), 0 ,0));
    }
    
    /**
     * Saves the score to the database
     */
    private void saveScoreToDB() {
        String msg = "You scored " + playerPoints + " point(s)! Would you like "
                + "to save your score?";
        int option = JOptionPane.showConfirmDialog(null, msg, "Confirm Answer", 
                JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            SaveScore ss = new SaveScore();
            ss.insertNameAndScore(username, playerPoints);
        }
        else {
            JOptionPane.showMessageDialog(null, "Score will not be saved.");
        }
    }
    
     /**
     * Method to execute once the player has collided with the ground/a pipe
     * @throws Throwable 
     */
    private void gameOver() throws Throwable  {
        Date date = new Date();
        player.playSound(GameEngine.class.getResourceAsStream
            (GlobalPath.FLAPPY_COLLISION));
        player.playSound(GameEngine.class.getResourceAsStream
            (GlobalPath.FLAPPY_DIE));
        repaint(gameOverMsg.getX(), gameOverMsg.getY(), gameOverMsg.getWidth(), 
                gameOverMsg.getHeight());
        
        if (username.equals("")) {
            JOptionPane.showMessageDialog(null, "You scored " + playerPoints 
                    + " point(s). You should consider signing up!");
        }
        else {
            if (playerPoints != 0 && !username.equals("")) saveScoreToDB();
            else JOptionPane.showMessageDialog(null, "No points scored."
                + "This can't be saved. Sorry!");
        }
        
        int playAgainOption = JOptionPane.showConfirmDialog(null, "Would you "
                + "like to play again?", "Confirm Answer", 
                JOptionPane.YES_NO_OPTION);
            if (playAgainOption == JOptionPane.YES_OPTION) {
                finalize();
                
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.setTime(date);
                int hour = calendar.get(Calendar.HOUR_OF_DAY);
        
                String backgroundFile = "";
                String upperPipeFile = "";
                String lowerPipeFile = "";
        
                if (hour < 18 && hour > 6) {
                    backgroundFile = GlobalPath.BACKGROUND_DAY;
                    upperPipeFile = GlobalPath.UPPER_PIPE_GREEN;
                    lowerPipeFile = GlobalPath.LOWER_PIPE_GREEN;
                }
                else {
                    backgroundFile = GlobalPath.BACKGROUND_NIGHT;
                    upperPipeFile = GlobalPath.UPPER_PIPE_RED;
                    lowerPipeFile = GlobalPath.LOWER_PIPE_RED;
                }        
                 // Audio Player................................................
                 AudioPlayer player = new AudioPlayer();
      
                // Randomize the color of the bird..............................
                String midFlap = "";
                String downFlap = "";
                String upFlap = "";
        
                Random random = new Random();
                int value = random.nextInt(3)+1;
            
                // Switch statement used because it's faster compared to
                // and if statement
                switch (value) {
                    case 1:
                        // Blue Bird
                        midFlap = GlobalPath.BB_MFLAP;
                        downFlap = GlobalPath.BB_DFLAP;
                        upFlap = GlobalPath.BB_UFLAP;
                        break;
                    case 2:
                        // Red Bird
                        midFlap = GlobalPath.RB_MFLAP;
                        downFlap = GlobalPath.RB_DFLAP;
                        upFlap = GlobalPath.RB_UFLAP;
                        break;
                    default:
                        // Yellow Bird
                        midFlap = GlobalPath.YB_MFLAP;
                        downFlap = GlobalPath.YB_DFLAP;
                        upFlap = GlobalPath.YB_UFLAP;
                        break;
                }
                initialize(midFlap, upFlap, downFlap, backgroundFile, 
                        upperPipeFile, lowerPipeFile);
            }
            else {
                app.dispose();
                LoginUI ui = new LoginUI(backgroundFile, upperPipeFile, 
                        lowerPipeFile);
            }
                
    }
    
    /**
     * Paints all the game pieces onto the JPanel (this)
     * @param g  Graphics object
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        Toolkit.getDefaultToolkit().sync();
    }
    
    /**
     * Renders the game pieces to the screen
     * @param g Graphics object
     */
    private void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform t = AffineTransform.getTranslateInstance(flappy.getX(), 
                flappy.getY());
        
        // Background List to render............................................
        for (int i = 0; i < bgList.size(); i++) {
            g2d.drawImage(bgList.get(i).getImage(), bgList.get(i).getX(), 
                    bgList.get(i).getY(), this);
        }
        
        // Rendering flappy and his angle for animations........................
        if (ticks % 2 == 0 && gameStarted == true) {
            if (angleCounter > 90) angleCounter = 90;
            else if (angleCounter <= -30) angleCounter = -30;
            angleCounter+= 3; 
        }
        t.rotate(Math.toRadians(angleCounter));
        g2d.drawImage(flappy.getImage(), t, this);
        
        // Ground List to render................................................
        for (int i = 0; i < gList.size(); i++) {
            g2d.drawImage(gList.get(i).getImage(), gList.get(i).getX(), 
                    gList.get(i).getY(), this);
        }
        
        // Rendering the upperPipeList and lowerPipeList........................
        for (int i = 0; i < upperPipeList.size(); i++) {
            g2d.drawImage(upperPipeList.get(i).getImage(), 
                    upperPipeList.get(i).getX(), GlobalPath.UPPER_PIPE_X, 
                    upperPipeList.get(i).getWidth(), 
                    upperPipeList.get(i).getHeight(), this);
            g2d.drawImage(lowerPipeList.get(i).getImage(), 
                    lowerPipeList.get(i).getX(), lowerPipeList.get(i).getY(), 
                    lowerPipeList.get(i).getWidth(), 
                    lowerPipeList.get(i).getHeight(), this);
        }
        
        // Rendering the scores to the screen...................................
        if (gameStarted == true && gameOver == false) {
            String digits = playerPoints + "";
            
            for (int i = 0; i < digits.length(); i++) {
                if (digits.length() == 1) {
                    g2d.drawImage(displayScoreList.get(digits.charAt(i) - '0').
                            getImage(),GlobalPath.CANVAS_WIDTH/2-20, 50, this);
                }
                else if (digits.length() == 2 && playerPoints < 20) {
                    g2d.drawImage(displayScoreList.get(digits.charAt(0) - '0').
                            getImage(),GlobalPath.CANVAS_WIDTH/2-28, 50, this);
                    g2d.drawImage(displayScoreList.get(digits.charAt(1) - '0').
                            getImage(),GlobalPath.CANVAS_WIDTH/2-11, 50, this);
                }
                else if (digits.length() == 2) {
                    g2d.drawImage(displayScoreList.get(digits.charAt(0) - '0').
                            getImage(),GlobalPath.CANVAS_WIDTH/2-35, 50, this);
                    g2d.drawImage(displayScoreList.get(digits.charAt(1) - '0').
                            getImage(),GlobalPath.CANVAS_WIDTH/2-11, 50, this);
                }
            }
        }
        
        // Rendering the intro message to the screen............................
        if (gameStarted == false && gameOver == false) {
            g2d.drawImage(introMsg.getImage(), GlobalPath.INTRO_MESSAGE_X, 
                GlobalPath.INTRO_MESSAGE_Y, this);
        }
        
        // If the game is over, render the game over message....................
        if (gameOver) 
        g2d.drawImage(gameOverMsg.getImage(), gameOverMsg.getX(), 
                gameOverMsg.getY(), gameOverMsg.getWidth(), 
                gameOverMsg.getHeight(), this);
        
    }
    
    /**
     * Create and display the starting pipes off the screen
     */
    private void renderStartingPipes() throws IOException {
        int height = setTopPipeHeight();
        uPipe = new UpperPipe(GameEngine.class.getResourceAsStream
            (upperPipeFile), GlobalPath.PIPE_START_X, 0);
        uPipe.setHeight(height);
        
        lPipe = new LowerPipe(GameEngine.class.getResourceAsStream
            (lowerPipeFile), GlobalPath.PIPE_START_X, 
                uPipe.getHeight() + GlobalPath.PIPE_INSIDE_SPACE);
        lPipe.setHeight(GlobalPath.GROUND_START_Y - (uPipe.getHeight() + 
                GlobalPath.PIPE_INSIDE_SPACE));
        
        upperPipeList.add(uPipe);
        lowerPipeList.add(lPipe);
        
        UpperPipe np1 = new UpperPipe(GameEngine.class.getResourceAsStream
            (upperPipeFile), uPipe.getX() 
                + GlobalPath.PIPE_SPACING, 0);
        UpperPipe np2 = new UpperPipe(GameEngine.class.getResourceAsStream
            (upperPipeFile), np1.getX() 
                + GlobalPath.PIPE_SPACING, 0);
        UpperPipe np3 = new UpperPipe(GameEngine.class.getResourceAsStream
            (upperPipeFile), np2.getX() 
                + GlobalPath.PIPE_SPACING, 0);
        UpperPipe np4 = new UpperPipe(GameEngine.class.getResourceAsStream
            (upperPipeFile), np3.getX() 
                + GlobalPath.PIPE_SPACING, 0);
        
        int height1 = setTopPipeHeight();
        int height2 = setTopPipeHeight();
        int height3 = setTopPipeHeight();
        int height4 = setTopPipeHeight();
        
        np1.setHeight(height1);
        np2.setHeight(height2);
        np3.setHeight(height3);
        np4.setHeight(height4);
        
        LowerPipe np5 = new LowerPipe(GameEngine.class.getResourceAsStream
            (lowerPipeFile), lPipe.getX() 
                + GlobalPath.PIPE_SPACING, uPipe.getHeight() 
                        + GlobalPath.PIPE_INSIDE_SPACE);
        LowerPipe np6 = new LowerPipe(GameEngine.class.getResourceAsStream
            (lowerPipeFile), np5.getX() 
                + GlobalPath.PIPE_SPACING, np1.getHeight() 
                        + GlobalPath.PIPE_INSIDE_SPACE);
        LowerPipe np7 = new LowerPipe(GameEngine.class.getResourceAsStream
            (lowerPipeFile), np6.getX() 
                + GlobalPath.PIPE_SPACING, np2.getHeight() 
                        + GlobalPath.PIPE_INSIDE_SPACE);
        LowerPipe np8 = new LowerPipe(GameEngine.class.getResourceAsStream
            (lowerPipeFile), np7.getX() 
                + GlobalPath.PIPE_SPACING, np3.getHeight() 
                        + GlobalPath.PIPE_INSIDE_SPACE);
        
        np5.setHeight(GlobalPath.GROUND_START_Y - (uPipe.getHeight() 
                + GlobalPath.PIPE_INSIDE_SPACE));
        np6.setHeight(GlobalPath.GROUND_START_Y - (np1.getHeight()
                + GlobalPath.PIPE_INSIDE_SPACE));
        np7.setHeight(GlobalPath.GROUND_START_Y - (np2.getHeight()
                + GlobalPath.PIPE_INSIDE_SPACE));
        np8.setHeight(GlobalPath.GROUND_START_Y - (np3.getHeight()
                + GlobalPath.PIPE_INSIDE_SPACE));
        
        upperPipeList.add(np1);
        upperPipeList.add(np2);
        upperPipeList.add(np3);
        upperPipeList.add(np4);
        
        lowerPipeList.add(np5);
        lowerPipeList.add(np6);
        lowerPipeList.add(np7);
        lowerPipeList.add(np8);
    }
    
    /**
     * Sets the height of the top pipe which will determine how big the lower 
     * pipe will be
     * @return The height for the upper pipe
     */
    private int setTopPipeHeight() {
        return random.nextInt(((GlobalPath.CANVAS_HEIGHT/2 - 100) - 85) 
                + 1) + 85;
    }
    
    /**
     * Starts the time
     */
    private void start() {
        timer.start();
    }
    
    /**
     * Stops the timer
     */
    private void stop() {
        timer.stop();
    }
    
    /**
     * This method executes every (GlobalPath.DELAY) ms.
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (gameOver && !gameStarted) {
            stop();
            try {
                gameOver();
            } catch (Throwable ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        else if (!gameOver){
            try {
                checkForCollision();
                update();
                currentRender();
                checkScore();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
    
    /**
     * Checks whether the user has gone through a pipe and if it has, then
     * give the player a point.
     */
    private void checkScore() {
        for (int i = 0; i < upperPipeList.size(); i++) {
            if (upperPipeList.get(i).getX() == 48) {
                player.playSound(GameEngine.class.getResourceAsStream
                    (GlobalPath.FLAPPY_GAIN_POINT));
                playerPoints++;
            }
        }
    }
    
    /**
     * Checks to see if the player has collided with a pipe or the ground
     */
    private void checkForCollision() {
        Rectangle flappyBounds = flappy.getBounds();
        
        for (int i = 0; i < gList.size(); i++) {
            Rectangle groundBounds = gList.get(i).getBounds();
            if (groundBounds.intersects(flappyBounds)) {
                gameOver = true; 
                gameStarted = false;
            }
        }
        for (int i = 0; i < upperPipeList.size(); i++) {
            Rectangle upperPipe = upperPipeList.get(i).getBounds();
            Rectangle lowerPipe = lowerPipeList.get(i).getBounds();
            
            if (lowerPipe.intersects(flappyBounds) || 
                    upperPipe.intersects(flappyBounds)) {
                gameOver = true;
                gameStarted = false;
            }
        }
    }
    
    /**
     * Renders the background so it is infinitely looping to make it look like
     * the bird is moving
     */
    private void renderBackground() throws IOException {
        for (int i = 0; i < bgList.size(); i++) {
            repaint(bgList.get(i).getX()-1, bgList.get(i).getY()-1, 
                    bgList.get(i).getWidth()+2, bgList.get(i).getHeight()+2);
        }
        if (bgList.get(0).getX() == -GlobalPath.CANVAS_WIDTH) {
            bgList.remove(0);
            Background temp = new Background(GameEngine.class.
                    getResourceAsStream(backgroundFile), 0, 0);
            temp.setX(bgList.get(0).getX() + GlobalPath.BACKGROUND_SPACE);
            bgList.add(temp);
        }
    }
    
    /**
     * Renders the ground so it is infinitely looping to make it look like
     * the bird is moving
     */
    private void renderGround() throws IOException {
        for (int i = 0; i < gList.size(); i++) {
            repaint(gList.get(i).getX()-1, gList.get(i).getY()-1, 
                    gList.get(i).getWidth()+2, gList.get(i).getHeight()+2);
        }
        if (gList.get(0).getX() == -GlobalPath.GROUND_SPACE) {
            gList.remove(0);
            Ground temp = new Ground(GameEngine.class.getResourceAsStream
                    (GlobalPath.GROUND), 
                    GlobalPath.GROUND_START_X, GlobalPath.GROUND_START_Y);
            temp.setX(gList.get(0).getX() + GlobalPath.GROUND_SPACE);
            gList.add(temp);
        }
    }
    
    /**
     * Renders the image of flappy depending on his position and if the player
     * is jumping
     */
    private void renderFlappy() throws IOException {
        if (flappy.getY() < 0) flappy.setY(0);
        if (ticks % 14 == 0) flappy.setImage(GameEngine.class.
                getResourceAsStream(downFlap));
        else if (ticks % 10 == 0)flappy.setImage(GameEngine.class.
                getResourceAsStream(upFlap));
        else if (ticks % 12 == 0)flappy.setImage(GameEngine.class.
                getResourceAsStream(midFlap));
    }
    
    /**
     * Renders the image of the upper and lower pipes
     */
    private void renderPipes() throws IOException {
        if (gameStarted && !gameOver){
            for (int i = 0; i < upperPipeList.size(); i++) {
            repaint(upperPipeList.get(i).getX()-1, 
                    upperPipeList.get(i).getY()-1, 
                    upperPipeList.get(i).getWidth()+2, 
                    upperPipeList.get(i).getHeight()+2);
            repaint(lowerPipeList.get(i).getX()-1, 
                    lowerPipeList.get(i).getY()-1, 
                    lowerPipeList.get(i).getWidth()+2, 
                    lowerPipeList.get(i).getHeight()+2);
        }
        
        if (upperPipeList.get(0).getX() <= -52 && 
                lowerPipeList.get(0).getX() <= -52) {
            upperPipeList.remove(0);
            lowerPipeList.remove(0);
            
            UpperPipe temp = new UpperPipe(GameEngine.class.getResourceAsStream
                (upperPipeFile), 0, 0);
            temp.setX(upperPipeList.get(3).getX() + GlobalPath.PIPE_SPACING);
            int tempHeight = setTopPipeHeight();
            temp.setHeight(tempHeight);
            upperPipeList.add(temp);
            
            LowerPipe temp2 = new LowerPipe(GameEngine.class.getResourceAsStream
                (lowerPipeFile), 0, temp.getHeight() 
                    + GlobalPath.PIPE_INSIDE_SPACE);
            temp2.setX(upperPipeList.get(3).getX() + GlobalPath.PIPE_SPACING);
            temp2.setHeight(GlobalPath.GROUND_START_Y - (temp.getHeight() 
                    + GlobalPath.PIPE_INSIDE_SPACE));
            lowerPipeList.add(temp2);
        }
        }
    }
    
    /**
     * Renders everything 
     */
    private void currentRender() throws IOException {
        renderBackground();
        renderGround();
        renderPipes();
        renderFlappy();
    }
    
    /**
     * Updates the position of the game pieces and gravity
     */
    private void update() {
        for (int i = 0; i < bgList.size(); i++) {
            bgList.get(i).moveX(GlobalPath.SPEED);
        }
        for (int i = 0; i < gList.size(); i++) {
            gList.get(i).moveX(GlobalPath.SPEED);
        }
        
        ticks++;
        
        if (gameStarted) {
            for (int i = 0; i < upperPipeList.size(); i++) {
                upperPipeList.get(i).moveX(GlobalPath.PIPE_SPEED);
                lowerPipeList.get(i).moveX(GlobalPath.PIPE_SPEED);
            }
            if (ticks % 2 == 0  && yMotion < 15) {
            yMotion += GlobalPath.GRAVITY;
        }
        flappy.applyGravity(yMotion);
    }
}
    /**
     * Changes the players position into a jumping motion
     */
    private void jump() throws IOException {
        player.playSound(GameEngine.class.getResourceAsStream
            (GlobalPath.FLAPPY_JUMP));
        if (yMotion > 0) yMotion = 0;
        yMotion -= GlobalPath.JUMP_AMOUNT;  
        angleCounter = GlobalPath.JUMP_ANGLE;
        if (yMotion < 0) flappy.setImage(GameEngine.class.getResourceAsStream
            (downFlap));
        flappy.jump(yMotion);
    }
    
    /**
     * If a key is typed, execute this code
     * @param ke Key event
     */
    @Override
    public void keyTyped(KeyEvent ke) {}
    
    /**
     * If a Key is pressed, execute this code
     * @param ke Key Event
     */
    @Override
    public void keyPressed(KeyEvent ke) {}
    
    /**
     * If the space bar is released, the player will jump
     * @param ke Key Event
     */
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_SPACE && !gameStarted && !gameOver) {
            gameStarted = true;
            try {
                jump();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        else if (ke.getKeyCode() == KeyEvent.VK_SPACE && gameStarted 
                && gameOver) {
            gameStarted = false;
        }
        else if (ke.getKeyCode() == KeyEvent.VK_SPACE && !gameStarted){
            gameStarted = true;
            try {
                jump();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        else {
            try {
                jump();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
    
    /**
     * If the mouse is clicked, the player will jump
     * @param me Mouse Event
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if (!gameStarted && !gameOver) {
            gameStarted = true;
            try {
                jump();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
        else if (gameStarted && gameOver) {
            gameStarted = false;
        }
        else if (gameStarted){
            try {
                jump();
            } catch (IOException ex) {
                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
    
    /**
     * If the mouse is pressed, execute this code
     * @param me Mouse Event
     */
    @Override
    public void mousePressed(MouseEvent me) {}
    
    /**
     * If the mouse key is released, execute this code
     * @param me Mouse Event
     */
    @Override
    public void mouseReleased(MouseEvent me) {}
    
    /**
     * If the mouse is entered, execute this code
     * @param me Mouse Event
     */
    @Override
    public void mouseEntered(MouseEvent me) {}
    
    /**
     * If the mouse is exited, then execute this code
     * @param me Mouse Event
     */
    @Override
    public void mouseExited(MouseEvent me) {}
}
