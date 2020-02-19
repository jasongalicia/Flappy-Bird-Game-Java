package game.launcher;

import game.globals.GlobalPath;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import game.ui.LoginUI;

/**
 *
 * @author Jason Galicia
 */
public class Launcher {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException Input output exception
     */
    public static void main(String[] args) throws IOException {
        // Find the Hour for the Game Background................................
        Date date = new Date();
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
        //......................................................................
        
        // Make a login first...
        LoginUI ui = new LoginUI(backgroundFile, upperPipeFile, lowerPipeFile);
    }
}
