/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

import model.physics.Simulation;
import view.graphics.objects.SpriteObject;
import java.awt.Graphics;
import java.awt.Image;
import view.graphics.Texture;

/**
 *
 * @author team 2
 */
public class SurfaceBlock extends SpriteObject {
    
    
    public SurfaceBlock(double x, double y, int width, int height, 
                 Texture tex, Simulation sim, int z) {
        super(x, y, width, height,tex, sim, z);
    }

    @Override
    public void tick() {}


}
