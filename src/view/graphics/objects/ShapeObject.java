/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Graphics;
import model.physics.Simulation;

/**
 *
 * @author team 2
 */
public class ShapeObject extends DrawableObject {

    public ShapeObject(double x, double y, int width, int height, Simulation s) {
        super(x, y, width, height, s);
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect(screenX,screenY,width,height);
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}