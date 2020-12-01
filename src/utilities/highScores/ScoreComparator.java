/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.highScores;

import java.util.Comparator;
/**
 *
 * @author team 2
 * 
 * Custom comparator - sorts scores by lowest first,
 * since our scoring system is akin to that of golf
 */
public class ScoreComparator implements Comparator<Score> {
            public int compare(Score score1, Score score2) {

            int sc1 = score1.getScore();
            int sc2 = score2.getScore();

            if (sc1 < sc2){
                return -1;
            }else if (sc1 > sc2){
                return +1;
            }else{
                return 0;
            }
        }
}
