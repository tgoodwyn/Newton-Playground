/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

import model.game.objects.Goal;
import utilities.Texture;

/**
 *
 * @author team 2
 * 
 * The Surface class is only used when the physics world (instance of 
 * GameWorld) is created.  It has data for the sprite used by the surface
 * as well as the boundary
 */


public class Surface {

    public static final int BLOCKSIZE = 50;
    public int beginX;
    public int endX;
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
