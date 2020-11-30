/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;

import java.awt.Graphics;
import model.physics.Simulation;
import view.graphics.Texture;

/**
 *
 * @author team 2
 */
public class SpriteObject extends DrawableObject {
    
    protected Texture tex;
    
    public SpriteObject(double x, double y, int width, int height, 
            Texture tex, Simulation sim) {
        super(x, y, width, height, sim);
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
