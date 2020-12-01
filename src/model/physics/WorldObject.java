/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.physics;

import model.physics.Simulation;
import model.physics.ISimulatable;
import utilities.BetterRect;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author team 2
 * 
 * The second highest level in the object hierarchy
 * All WorldObjects implement ISimulatable and are therefore
 * part of the physics loop
 * 
 */
public abstract class WorldObject extends BetterRect implements ISimulatable {

    public Simulation sim;

    public WorldObject(double x, double y, int width, int height, Simulation s) {
        super(x, y, width, height);
        this.sim = s;
    }

    @Override
    public abstract void tick();

    @Override
    public Simulation getSimulation() {
        return sim;
    }

}
