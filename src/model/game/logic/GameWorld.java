/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.logic;

import model.physics.Simulation;
import model.game.objects.Birdie;
import view.graphics.Camera;
import view.graphics.Texture;
import model.game.objects.Goal;
import model.game.objects.Surface;
import model.game.objects.SurfaceBlock;
import view.graphics.objects.ShapeObject;

/**
 *
 * @author team 2
 */
public class GameWorld {

    private final Camera camera;
    private final Birdie birdie;

    private final Simulation sim;
    private Surface levelSurface;
    private final Goal levelGoal;
    GameLevel.LevelType levelType;

    public Camera getCamera() {
        return camera;
    }

    public GameWorld(Simulation sim, Goal goal, GameLevel.LevelType levelType) {
        this.levelType = levelType;
        this.sim = sim;
        this.levelGoal = goal;

        // code for creating level-specific birdie goes here
        // might be moved into its own function at some point
        // TODO: fill out rest of the switch statement
        switch (levelType) {
            case STONE:
                // its x,y,w,h
                this.birdie = new Birdie(0.0, 50.0, 50, 50, "/Ball.png",
                        // its mass
                        50, sim);
                break;
            default:
                this.birdie = new Birdie(0, 50, 50, 50, "/cannon_ball.png",
                        50, sim);
                break;
        }
        this.camera = new Camera(-50, 200, 0, 0, sim, this.birdie);

    }


    /* not used for now    
    private void createBirdieAndCamera(Simulation sim) {

    }
     */
    public void build() {
        addStaticObjects();
        addDynamicObjects();
        sim.separateDrawables();
    }

    public void addStaticObjects() {
        createSurface();
        addSurfaceToSimulation();
        sim.addStatic(new ShapeObject(0,0,50,50,sim));
    }

    public void addDynamicObjects() {
        // add any dynamic objects
        sim.addDynamic(this.camera);
        sim.setCamera(camera);
        sim.addDynamic(this.birdie);
        sim.setBirdie(birdie);
    }

    public void createSurface() {
        // initialize surface based on levelType
        String surfaceImagePath = "";
        // TODO: add other surface types
        switch (levelType) {
            case STONE:
                surfaceImagePath = "/Stone.png";
                break;
        }

        levelSurface = new Surface(-500, 10000,
                levelGoal.getBoundary(), levelGoal, surfaceImagePath);
    }

    // add data for the surface of the level to the simulation
    private void addSurfaceToSimulation() {
        // variables created here to delineate the start and end points
        // of the while loops that are the bulk of this function
        int step = Surface.BLOCKSIZE;
        int xStart = levelSurface.beginX;
        int xEnd = levelSurface.endX;
        int goalStart = levelGoal.getX();
        int goalEnd = levelGoal.getX() + levelGoal.getWidth();
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

                SurfaceBlock block = new SurfaceBlock(
                        (double)x, (double)y, step, step, curTex, sim);
                // add the block to the simulation
                sim.addStatic(block);
                y -= step;
            }
            x += step;
        }
    }

    public Birdie getBirdie() {
        return birdie;
    }

}
