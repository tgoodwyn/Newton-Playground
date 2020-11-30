/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Color;
import java.awt.Graphics;
import model.physics.Simulation;

/**
 *
 * @author team 2
 */
public class ShapeObject extends DrawableObject {
    
    private Color color;
    public ShapeObject(double x, double y, int width, int height, Color color, Simulation s, int z) {
        super(x, y, width, height, s, z);
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(screenX,screenY,width,height);
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}