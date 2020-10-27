/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author tgood
 */
public abstract class WorldObject extends BetterRect 
        implements ISimulatable {

    Simulation sim;

    WorldObject(int x, int y, int width, int height, Simulation s) {
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
