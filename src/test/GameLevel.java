/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author team 2
 */
public class GameLevel {

    private final Camera camera;
    private final Simulation sim;
    private Surface levelSurface;
    private final Goal levelGoal;

    public Camera getCamera() {
        return camera;
    }

    public GameLevel(Simulation sim, Goal g) {
        this.camera = new Camera(0, 0, 0, 0, sim);
        this.sim = sim;
        this.levelGoal = g;
    }

    public void build() {
        addStaticObjects();
        addDynamicObjects();
        sim.separateDrawables();
    }

    public void addStaticObjects() {
        createSurface();
        addSurfaceToSimulation();
    }

    public void addDynamicObjects() {
        // add any dynamic objects
        sim.addDynamic(this.camera);
    }

    public void createSurface() {
        // initialize surface 
        levelSurface = new Surface(-500, 10000,
                levelGoal.getBoundary(), levelGoal, "/stoneWall.png");
    }

    // add data for the surface of the level to the simulation
    private void addSurfaceToSimulation() {
        // variables created here to delineate the start and end points
        // of the while loops that are the bulk of this function
        int step = Surface.BLOCKSIZE;
        int xStart = levelSurface.beginX;
        int xEnd = levelSurface.endX;
        int goalStart = levelGoal.x;
        int goalEnd = levelGoal.x + levelGoal.width;
        int yTop = levelSurface.getBoundary().getYCoord();
        int yBottom = -600;
        // different texture will be assigned based on location
        // of the surface block
        Texture curTex;

        // nested while loops that fill in the surface model,
        // separating goal blocks from non-goal blocks
        int x = xStart;
        while (x < xEnd) {
            int y = yTop;
            while (y > yBottom) {

                // if x is between the start and end of goal
                if (x >= goalStart && x < goalEnd) {
                    curTex = levelGoal.getSurfaceTexture();
                } // otherwise use regular texture
                else {
                    curTex = levelSurface.getBodyTexture();
                }
//                System.out.println("block instantiated at (" + x + (", " + y + ")"));

                SurfaceBlock block = new SurfaceBlock(
                        x, y, step, step, curTex, sim);
                // add the block to the simulation
                sim.addStatic(block);
                y -= step;
            }
            x += step;
        }
    }

}
