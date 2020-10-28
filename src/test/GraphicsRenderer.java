/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author team 2
 */
public class GraphicsRenderer {
    private final Camera cam;
    
    GraphicsRenderer(Camera c){
        this.cam = c;
    }
    
    public void render(Graphics g){
        cam.snap();
        cam.clip();
        for (DrawableObject d : cam.visibleObjects) {
            d.draw(g);
        }

    }
    
}
