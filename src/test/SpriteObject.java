/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;

/**
 *
 * @author team 2
 */
public class SpriteObject extends DrawableObject {
    
    protected final Texture tex;
    
    SpriteObject(int x, int y, int width, int height, 
            Texture tex, Simulation sim) {
        super(x, y, width, height, sim);
        this.tex = tex;
    }

    @Override
    public void tick() {}

    
    public void draw(Graphics g) {
        g.drawImage(tex.getTexture(),screenX,screenY,width,height,null);
    }
}
