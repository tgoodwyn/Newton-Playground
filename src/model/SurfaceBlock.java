/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.SpriteObject;
import java.awt.Graphics;
import java.awt.Image;
import view.Texture;

/**
 *
 * @author team 2
 */
public class SurfaceBlock extends SpriteObject {
    
    
    SurfaceBlock(int x, int y, int width, int height, 
                 Texture tex, Simulation sim) {
        super(x, y, width, height,tex, sim);
    }

    @Override
    public void tick() {}


}
