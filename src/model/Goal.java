/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import view.Texture;

/**
 *
 * @author team 2
 */
class Goal {

    int x;
    SurfaceBoundary divideLine;
    int width;
    int height;

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

    SurfaceBoundary getBoundary() {
        return divideLine;
    }

}
