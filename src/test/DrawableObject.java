/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;

/**
 *
 * @author tgood
 */
public abstract class DrawableObject extends WorldObject {

    int screenX, screenY;

    public DrawableObject(int x, int y, int width, int height, Simulation s) {
        super(x, y, width, height, s);
        screenX = x;
        screenY = y;
    }

    public abstract void draw(Graphics g);

    void setScreenX(int adjustedX) {
        screenX = adjustedX;
    }

    void setScreenY(int adjustedY) {
        screenY = adjustedY;
    }
}
