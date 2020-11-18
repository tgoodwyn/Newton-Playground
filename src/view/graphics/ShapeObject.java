/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import java.awt.Graphics;
import model.Simulation;

/**
 *
 * @author team 2
 */
public class ShapeObject extends DrawableObject {

    public ShapeObject(int x, int y, int width, int height, Simulation s) {
        super(x, y, width, height, s);
    }

    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
