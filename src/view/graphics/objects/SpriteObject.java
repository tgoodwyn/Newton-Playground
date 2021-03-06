/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Graphics;
import model.physics.Simulation;
import utilities.Texture;

/**
 *
 * @author team 2
 * 
 * Objects drawn on screen that have an associated image file
 * (most objects in the game, e.g. the surface blocks and the Birdie)
 * 
 */
public class SpriteObject extends DrawableObject {
    
    protected Texture tex;
    
    public SpriteObject(double x, double y, int width, int height, 
            Texture tex, Simulation sim, int z) {
        super(x, y, width, height, sim, z);
        this.tex = tex;
    }

    @Override
    public void tick() {}

    public void setTexture(String path) {
        this.tex = new Texture(path);
    }
    
    public void draw(Graphics g) {
        g.drawImage(tex.getTexture(),screenX,screenY,width,height,null);
    }
}
