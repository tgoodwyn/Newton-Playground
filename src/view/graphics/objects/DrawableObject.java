/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Graphics;
import model.physics.Simulation;
import model.physics.WorldObject;

/**
 *
 * @author team 2
 * 
 * The observer object for the graphics loop
 * 
 * 
 */
public abstract class DrawableObject extends WorldObject {

    int screenX, screenY;
    int zVal;
    public DrawableObject(double x, double y, int width, int height, Simulation s, int z) {
        super(x, y, width, height, s);
        screenX = (int) x;
        screenY = (int) y;
        this.zVal = z;
    }

    public abstract void draw(Graphics g);

    public void setScreenX(int adjustedX) {
        screenX = adjustedX;
    }

    public void setScreenY(int adjustedY) {
        screenY = adjustedY;
    }

    public int getzVal() {
        return zVal;
    }
    
    
}
