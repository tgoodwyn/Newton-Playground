/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author tgood
 */
public class Surface {

    public static final int BLOCKSIZE = 50;
    int beginX;
    int endX;
    Goal goal;
    SurfaceBoundary boundary;
    Texture bodyTexture;
    Texture goalSurfaceTexture;
    
    public Surface(int begin, int end, SurfaceBoundary y, Goal g, String path) {
        this.beginX = begin;
        this.endX = end;
        this.boundary = y;
        this.goal = g;
        this.bodyTexture = new Texture(path);
        this.goalSurfaceTexture = g.getSurfaceTexture();
    }
    
    public Texture getBodyTexture() {
        return bodyTexture;
    }

    public SurfaceBoundary getBoundary() {
        return boundary;
    }

}
