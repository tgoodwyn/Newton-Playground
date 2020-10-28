/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import test.InputController;

/**
 *
 * @author team 2
 */
public class GameLogic implements ISimulatable {

    private final InputController ic;
    private final GameLevel level;
    private final Simulation sim;

    public GameLevel getLevel() {
        return level;
    }

    public GameLogic() {
        // every game has an input controller and a simulation
        sim = new Simulation();
        // every game builds a level and attaches the simulation
        Goal goal = new Goal(100, new SurfaceBoundary(0),
                500, 300, "/block_brick.png");
        level = new GameLevel(sim, goal);
        level.build();
        ic = new InputController(level.getCamera());

    }

    public InputController getInputController() {
        return ic;
    }

    @Override
    public void tick() {
        sim.simulate();
    }

    @Override
    public Simulation getSimulation() {
        return sim;
    }

}
