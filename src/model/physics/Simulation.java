/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.physics;

import view.graphics.objects.DrawableObject;
import java.util.ArrayList;
import model.game.objects.Birdie;
import model.game.logic.GameLevel;
import model.game.objects.Goal;
import model.game.objects.WorldObject;
import view.graphics.Camera;

/**
 *
 * @author team 2
 */
public class Simulation {

    ArrayList<WorldObject> dynamics = new ArrayList<>();
    ArrayList<WorldObject> statics = new ArrayList<WorldObject>();
    ArrayList<DrawableObject> allDrawable = new ArrayList<DrawableObject>();
    private double levelFrictionCoefficient;
    private Birdie birdie;
    private Camera camera;
    private int birdieDirection;
    private boolean inputAllowed;
    int goalX;
    private Goal goal;
    private int goalDistance;

    public Simulation(GameLevel.LevelType levelType, Goal goal) {
        // TODO: Fill out switch statement
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
        this.goalX = goal.getX();
        goalDistance = goalX;
        this.goal = goal;
        inputAllowed = true;
    }

    public ArrayList<DrawableObject> getAllDrawable() {
        return allDrawable;
    }

    public void simulate() {
        for (WorldObject o : dynamics) {
            o.tick();
        }
        double difference = goalX - birdie.getX();
        birdieDirection = (difference >= 0) ? 1 : -1;
        if (birdie.isMoving() || camera.isFollowing()) {
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
        goalDistance = (birdieDirection > 0) ? goal.getLeftX() - x : x - goal.getRightX();
        return goalDistance;
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
