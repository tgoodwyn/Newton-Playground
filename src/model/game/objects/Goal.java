/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

import java.awt.Image;
import view.graphics.Texture;

/**
 *
 * @author team 2
 */
public class Goal {

    private int x;
    SurfaceBoundary divideLine;
    private int width;
    private int height;



    Texture surfaceTexture;
    Texture objectTexture;

    public Goal(int x, SurfaceBoundary y, int width, int height, String path) {
        this.x = x;
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
        return x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
