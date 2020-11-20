/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.graphics.objects.DrawableObject;
import java.util.ArrayList;
import view.graphics.Camera;

/**
 *
 * @author team 2
 */
public class Simulation {

    ArrayList<WorldObject> dynamics = new ArrayList<WorldObject>();
    ArrayList<WorldObject> statics = new ArrayList<WorldObject>();
    ArrayList<DrawableObject> allDrawable = new ArrayList<DrawableObject>();
    private double levelFrictionCoefficient;
    private Birdie birdie;
    private Camera camera;
    private int birdieDirection;
    private boolean inputAllowed;
    int goalX;

    public Simulation(GameLevel.LevelType levelType, int goalX) {
        // TODO: Fill out switch statement
        switch (levelType) {
            case STONE:
                this.levelFrictionCoefficient = .85;
                break;
            default:
                this.levelFrictionCoefficient = .75;
                break;

        }
        this.goalX = goalX;
        inputAllowed = true;
    }

    public ArrayList<DrawableObject> getAllDrawable() {
        return allDrawable;
    }

    public void simulate() {
        for (WorldObject o : dynamics) {
            o.tick();
        }
        if (birdie.isMoving() || camera.isFollowing()) {
            inputAllowed = false;
        } else {
            inputAllowed = true;
            int difference = goalX - birdie.getX();
            birdieDirection = (difference >= 0) ? 1 : -1;
        }
    }

    public void addDynamic(WorldObject po) {
        dynamics.add(po);
    }

    public void addStatic(WorldObject wo) {
        statics.add(wo);
    }

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
    
    

}
