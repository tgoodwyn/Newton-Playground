/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import view.DrawableObject;
import java.util.ArrayList;

/**
 *
 * @author team 2
 */
class Simulation  {
    ArrayList<WorldObject> dynamics = new ArrayList<WorldObject>();
    ArrayList<WorldObject> statics = new ArrayList<WorldObject>();
    ArrayList<DrawableObject> allDrawable = new ArrayList<DrawableObject>();

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
            if (o instanceof DrawableObject) allDrawable.add((DrawableObject)o);
        }
    }
    
}
