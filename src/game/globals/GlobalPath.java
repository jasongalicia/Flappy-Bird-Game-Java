package game.globals;

/**
 *
 * @author Jason Galicia
 */
public class GlobalPath {
    
    // Game Objects.............................................................
    public static final String BACKGROUND_DAY = "/game/media/daybackground."
            + "png";
    public static final String BACKGROUND_NIGHT = "/game/media/nightbackground."
            + "png";
    public static final String GROUND = "/game/media/ground-pic.png";
    public static final String INTRO_MESSAGE = "/game/media/intro-game-message."
            + "png";
    public static final String GAME_OVER_MSG = "/game/media/message-gameover."
            + "png";
    
    public static final String BB_DFLAP = "/game/media/bb-downflap.png";
    public static final String BB_MFLAP = "/game/media/bb-midflap.png";
    public static final String BB_UFLAP = "/game/media/bb-upflap.png";
    
    public static final String RB_DFLAP = "/game/media/rb-downflap.png";
    public static final String RB_MFLAP = "/game/media/rb-midflap.png";
    public static final String RB_UFLAP = "/game/media/rb-upflap.png";
    
    public static final String YB_DFLAP = "/game/media/yb-downflap.png";
    public static final String YB_MFLAP = "/game/media/yb-midflap.png";
    public static final String YB_UFLAP = "/game/media/yb-upflap.png";
    
    public static final String LOWER_PIPE_GREEN = "/game/media/lower-pipe-green."
            + "png";
    public static final String LOWER_PIPE_RED = "/game/media/lower-pipe-red."
            + "png";
    public static final String UPPER_PIPE_GREEN = "/game/media/upper-pipe-green."
            + "png";
    public static final String UPPER_PIPE_RED = "/game/media/upper-pipe-red."
            + "png";
    
    public static final String SCORE_0 = "/game/media/score_0.png";
    public static final String SCORE_1 = "/game/media/score_1.png";
    public static final String SCORE_2 = "/game/media/score_2.png";
    public static final String SCORE_3 = "/game/media/score_3.png";
    public static final String SCORE_4 = "/game/media/score_4.png";
    public static final String SCORE_5 = "/game/media/score_5.png";
    public static final String SCORE_6 = "/game/media/score_6.png";
    public static final String SCORE_7 = "/game/media/score_7.png";
    public static final String SCORE_8 = "/game/media/score_8.png";
    public static final String SCORE_9 = "/game/media/score_9.png";
    
    // DATABASE.................................................................
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://us-cdbr-iron-east-04."
            + "cleardb.net/heroku_76662f7ef1da94f";
    public static final String DB_USERNAME = "bed7ff65051d2d";
    public static final String DB_PASSWORD = "d1faaab7";
    
    // SOUND EFFECTS............................................................
    public static final String FLAPPY_JUMP = "/game/media/flappy-jump-effect."
            + "wav";
    public static final String FLAPPY_COLLISION = "/game/media/flappy-collision"
            + "-effect.wav";
    public static final String FLAPPY_GAIN_POINT = "/game/media/flappy-gain-"
            + "point.wav";
    public static final String FLAPPY_DIE = "/game/media/flappy-die-effect."
            + "wav";
    
    // GLOBAL VARIABLES.........................................................   
    public static final int CANVAS_WIDTH = 288;//288
    public static final int CANVAS_HEIGHT = 512;//512
    public static final String APP_TITLE = "Flappy Bird";
    public static final String APP_LOGIN_NAME = "Login";
    
    public static final int DELAY = 25;
    
    public static final int SPEED = -2;
    public static final int PIPE_SPEED = -3;
    public static final int GRAVITY = 2;
    public static final int JUMP_AMOUNT = 10;
    public static final int PIPE_INSIDE_SPACE = 150;
    public static final int PIPE_SPACING = 150;
    public static final int PIPE_START_X = 450;
    
    public static final int JUMP_ANGLE = -36;
    
    public static final int BACKGROUND_START_X = 0;
    public static final int BACKGROUND_START_Y = 0;
    
    public static final int UPPER_PIPE_X = 0;
    public static final int UPPERPIPE_START_X = 0;
    
    public static final int FLAPPY_START_X = 60;
    public static final int FLAPPY_START_Y = 220;
    
    public static final int GROUND_START_X = 0;
    public static final int GROUND_START_Y =  400;
    
    public static final int GROUND_SPACE = 336;
    public static final int BACKGROUND_SPACE = 288;
    
    public static final int INTRO_MESSAGE_X = 48;
    public static final int INTRO_MESSAGE_Y = 50;
    
    public static final int GAMEOVER_MSG_X = 50;
    public static final int GAMEOVER_MSG_Y = 80;
}
