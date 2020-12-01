/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.physics;

import view.graphics.objects.DrawableObject;
import java.util.ArrayList;
import view.graphics.objects.Birdie;
import model.game.logic.GameLevel;
import model.game.objects.Goal;
import view.graphics.Camera;

/**
 *
 * @author team 2
 * 
 * The Simulation class is the publisher for the physics loop -
 * its simulate method calls the tick method on all ISimulatable objects
 * 
 * It also:
 * -sets the friction for the level based on selected level type
 * -determines whether or not movement is allowed based on whether
 * objects (namely, the Camera and the Birdie) are still moving,
 * or whether or not the game is over
 */
public class Simulation {

    ArrayList<WorldObject> dynamics = new ArrayList<>();
    ArrayList<WorldObject> statics = new ArrayList<>();
    ArrayList<DrawableObject> allDrawable = new ArrayList<>();
    private double levelFrictionCoefficient;
    private Birdie birdie;
    private Camera camera;
    private int birdieDirection;
    private boolean inputAllowed;
    double goalX;
    private Goal goal;
    private double goalDistance;
    private boolean gameOver = false;
    private GameLevel level;
    public Simulation(GameLevel.LevelType levelType, Goal goal, GameLevel level) {
        switch (levelType) {
            case ICE:
                this.levelFrictionCoefficient = .95;
                break;
            case STONE:
                this.levelFrictionCoefficient = .92;
                break;
            case GRASS:
                this.levelFrictionCoefficient = .88;
                break;
            case SAND:
                this.levelFrictionCoefficient = .85;
                break;

        }
        this.goalX = goal.getCenter();
        goalDistance = goalX;
        this.goal = goal;
        inputAllowed = true;
        this.level = level;
    }

    public ArrayList<DrawableObject> getAllDrawable() {
        return allDrawable;
    }

    /**
     * The equivalent to the "notify observers" method
     * for the observer pattern
     * 
     * Also, since the simulation knows whether any object is still moving, 
     * it performs the check for whether or not input is allowed
     * (Input not allowed if objects still moving)
     */
    public void simulate() {
        for (WorldObject o : dynamics) {
            o.tick();
        }
        checkInput();
    }

    public void checkInput() {
        gameOver = level.isGameOver();
        double difference = goalX - birdie.getX();
        birdieDirection = (difference >= 0) ? 1 : -1;
        if (birdie.isMoving() || camera.isFollowing() || gameOver) {
            inputAllowed = false;
        } else {
            inputAllowed = true;

        }
    }

    public void addDynamic(WorldObject po) {
        dynamics.add(po);
    }

    public void addStatic(WorldObject wo) {
        statics.add(wo);
    }

    /**
     * Called when the GameWorld is created
     * Used to reduce the size of objects that the 
     * Camera has to check whether or not they are visible
     */
    public void separateDrawables() {
        ArrayList<WorldObject> all = new ArrayList<WorldObject>();
        all.addAll(dynamics);
        all.addAll(statics);
        for (WorldObject o : all) {
            if (o instanceof DrawableObject) {
                allDrawable.add((DrawableObject) o);
            }
        }
    }

    public double getLevelFriction() {
        return levelFrictionCoefficient;
    }

    public Birdie getBirdie() {
        return birdie;
    }

    public int getDistanceToGoal() {
        int x = (int) birdie.getCenter();
        goalDistance = (birdieDirection > 0) ? goal.getCenter() - x : x - goal.getCenter();
        return (int)goalDistance;
    }

    public void setBirdie(Birdie birdie) {
        this.birdie = birdie;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public int getBirdieDirection() {
        return birdieDirection;
    }

    public void setBirdieDirection(int birdieDirection) {
        this.birdieDirection = birdieDirection;
    }

    public boolean isInputAllowed() {
        return inputAllowed;
    }

    public void setInputAllowed(boolean inputAllowed) {
        this.inputAllowed = inputAllowed;
    }

    public Goal getGoal() {
        return goal;
    }

}
