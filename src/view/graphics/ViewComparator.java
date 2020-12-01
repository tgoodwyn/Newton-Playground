/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import java.util.Comparator;
import view.graphics.objects.DrawableObject;

/**
 *
 * @author tgood
 * 
 * A comparator for determining the z depth of DrawableObjects
 */
public class ViewComparator implements Comparator<DrawableObject> {
            public int compare(DrawableObject score1, DrawableObject score2) {

            int z1 = score1.getzVal();
            int z2 = score2.getzVal();

            if (z1 < z2){
                return -1;
            }else if (z1 > z2){
                return +1;
            }else{
                return 0;
            }
        }
}
