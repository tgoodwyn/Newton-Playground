/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

/**
 *
 * @author team 2
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
