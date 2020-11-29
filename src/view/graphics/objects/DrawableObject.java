/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Graphics;
import model.physics.Simulation;
import model.game.objects.WorldObject;

/**
 *
 * @author team 2
 */
public abstract class DrawableObject extends WorldObject {

    int screenX, screenY;

    public DrawableObject(int x, int y, int width, int height, Simulation s) {
        super(x, y, width, height, s);
        screenX = x;
        screenY = y;
    }

    public abstract void draw(Graphics g);

    public void setScreenX(int adjustedX) {
        screenX = adjustedX;
    }

    public void setScreenY(int adjustedY) {
        screenY = adjustedY;
    }
}
