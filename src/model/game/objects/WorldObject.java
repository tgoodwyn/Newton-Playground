/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.objects;

import model.physics.Simulation;
import model.physics.ISimulatable;
import utilities.math.BetterRect;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author team 2
 */
public abstract class WorldObject extends BetterRect implements ISimulatable {

    public Simulation sim;

    public WorldObject(int x, int y, int width, int height, Simulation s) {
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
