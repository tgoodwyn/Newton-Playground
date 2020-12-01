/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

import java.awt.Image;
import utilities.Texture;

/**
 *
 * @author team 2
 * The Goal class has all the data pertaining to
 * the "goal" for the level - where its located, its width,
 * and its texture (image)
 */
public class Goal {

    SurfaceBoundary divideLine;
    private int width;
    private int height;
    private int leftX;
    private int rightX;



    Texture surfaceTexture;
    Texture objectTexture;

    public Goal(int x, SurfaceBoundary y, int width, int height, String path) {
        this.leftX = x;
        this.divideLine = y;
        this.width = width;
        this.height = height;
        this.surfaceTexture = new Texture(path);
    }

    public Texture getSurfaceTexture() {
        return surfaceTexture;
    }

    public SurfaceBoundary getBoundary() {
        return divideLine;
    }

        public int getX() {
        return leftX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public double getCenter() {
        return leftX + (width / 2);
    }

    public int getLeftX() {
        return leftX;
    }

    public int getRightX() {
        return leftX + width;
    }
    
    
}
