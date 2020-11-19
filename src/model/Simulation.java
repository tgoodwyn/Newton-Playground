/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.graphics.objects.DrawableObject;
import java.util.ArrayList;

/**
 *
 * @author team 2
 */
public class Simulation {

    ArrayList<WorldObject> dynamics = new ArrayList<WorldObject>();
    ArrayList<WorldObject> statics = new ArrayList<WorldObject>();
    ArrayList<DrawableObject> allDrawable = new ArrayList<DrawableObject>();
    private double levelFrictionCoefficient;

    public Simulation(GameLevel.LevelType levelType) {
        
        // TODO: Fill out switch statement
        switch (levelType) {
            case STONE:
                this.levelFrictionCoefficient = .85;
                break;
            default:
                this.levelFrictionCoefficient = .75;
                break;

        }
    }

    public ArrayList<DrawableObject> getAllDrawable() {
        return allDrawable;
    }

    public void simulate() {
        for (WorldObject o : dynamics) {
            o.tick();
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
    
    

}
