/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.logic;

import java.awt.Color;
import model.physics.Simulation;
import view.graphics.objects.Birdie;
import view.graphics.Camera;
import utilities.Texture;
import model.game.objects.Goal;
import model.game.objects.Surface;
import view.graphics.objects.SurfaceBlock;
import view.graphics.objects.ShapeObject;
import view.graphics.objects.SpriteObject;

/**
 *
 * @author team 2
 * the GameWorld class is what actually populates the objects 
 * belonging to the physics simulation at the time of a level being created
 */
public class GameWorld {

    public static final int WORLD_BEGIN = - 10000;
    public static final int WORLD_END = 20000;
    public static final int CAMERA_START_Y = 200;
    public static final int CAMERA_START_X = -50;

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
        this.birdie = new Birdie(0.0, 50.0, 50, 50, "/balls/Red.png",
                // its mass
                50, sim, 2);
        this.camera = new Camera(CAMERA_START_X, CAMERA_START_Y, 0, 0, sim, this.birdie);

    }

    /**
     * BUILDs the "physical" world
     * ie, generates data for all objects that will be controlled by the simulation
     */
    public void build() {
        addStaticObjects();
        addDynamicObjects();
        sim.separateDrawables();
    }

    private void addStaticObjects() {
        //createAndAddBackground();
        createSurface();
        addSurfaceToSimulation();
        sim.addStatic(new ShapeObject(0, 0, 50, 10, Color.WHITE, sim, 2));
    }

    private void addDynamicObjects() {
        // add any dynamic objects
        sim.addDynamic(this.camera);
        sim.setCamera(camera);
        sim.addDynamic(this.birdie);
        sim.setBirdie(birdie);
    }

    private void createAndAddBackground() {
        String bgImagePath = "/Background 1.png";
        Texture tex = new Texture(bgImagePath);
        int bgWidth = 600;
        int bgHeight = 200;
        
        for (int x = WORLD_BEGIN; x < WORLD_END; x += bgWidth) {
        SpriteObject bg = new SpriteObject(
                (double) x, (double) CAMERA_START_Y, bgWidth, bgHeight, tex, sim, 0);
        // add the block to the simulation
        sim.addStatic(bg);
        }
    }

    private void createSurface() {
        // initialize surface based on levelType
        String surfaceImagePath = "";
        switch (levelType) {
            case STONE:
                surfaceImagePath = "/Stone.png";
                break;
            case ICE:
                surfaceImagePath = "/ICE.png";
                break;
            case GRASS:
                surfaceImagePath = "/GRASS.png";
                break;
            case SAND:
                surfaceImagePath = "/SAND.png";
                break;

        }

        levelSurface = new Surface(WORLD_BEGIN, WORLD_END,
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
                        (double) x, (double) y, step, step, curTex, sim, 1);
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
