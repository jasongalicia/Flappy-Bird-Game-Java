package game.sort;

import game.gametools.Score;
import java.util.ArrayList;

/**
 *
 * @author Jason Galicia
 */
public class Sorter {
    
    /**
     * Sorts the top 5 scores
     * @param scores The array list
     * @return An array
     */
    public static Score[] sortTop5Scores(ArrayList<Score> scores) {
        Score top5[] = new Score[5];
        ArrayList<Score> list = scores;
        
        for (int i = 0; i < list.size()-1; i++) {
            for (int j = 0; j < list.size()-i-1; j++) {
                if (list.get(j).getScore() > list.get(j+1).getScore()) {
                    Score temp = list.get(j);
                    list.set(j, list.get(j+1));
                    list.set(j+1, temp);
                }
            }
        }
        int count = 0;
        for (int i = list.size()-1; i >= list.size()-5; i--) {
            top5[count] = list.get(i);
            count++;
        }
        return top5;
    }
}

