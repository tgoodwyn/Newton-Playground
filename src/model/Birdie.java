/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics;
import java.awt.Image;
import view.graphics.Texture;
import view.graphics.objects.SpriteObject;

/**
 *
 * @author team 2
 */
public class Birdie extends SpriteObject  {
    
    // the birdie has mass
    private int mass;
    
    public Birdie(int x, int y, int width, int height, String imagePath , int mass, 
            Simulation s) {
        super( x, y, width, height,new Texture(imagePath), s);
        this.mass = mass;
    }

    @Override
    public void tick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    public void draw(Graphics g, int screenX, int screenY) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    

}
