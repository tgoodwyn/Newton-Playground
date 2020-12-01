/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

/**
 *
 * @author team 2
 * 
 * This class is mainly used to improve readability - it only
 * has 1 member variable
 * Its purpose is to clearly delineate the y coordinate where the Birdie rests
 * 
 */
public class SurfaceBoundary {
    int yCoord;

    public SurfaceBoundary(int y) {
        yCoord = y;
    }
    
    public int getYCoord() {
        return yCoord;
    }
}
