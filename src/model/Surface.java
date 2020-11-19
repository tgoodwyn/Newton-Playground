/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Goal;
import view.graphics.Texture;

/**
 *
 * @author team 2
 */
public class Surface {

    public static final int BLOCKSIZE = 50;
    int beginX;
    int endX;
    Goal goal;
    SurfaceBoundary boundary;
    Texture bodyTexture;
    Texture goalSurfaceTexture;
    
    public Surface(int begin, int end, SurfaceBoundary y, Goal goal, String path) {
        this.beginX = begin;
        this.endX = end;
        this.boundary = y;
        this.goal = goal;
        this.bodyTexture = new Texture(path);
        this.goalSurfaceTexture = goal.getSurfaceTexture();
    }
    
    public Texture getBodyTexture() {
        return bodyTexture;
    }

    public SurfaceBoundary getBoundary() {
        return boundary;
    }

}
