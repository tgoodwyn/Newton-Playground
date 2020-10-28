/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Image;

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
