/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.GameWorld;
import controller.InputController;
import controller.InputController;

/**
 *
 * @author team 2
 */
public class GameLevel implements ISimulatable {

    private final InputController ic;

    // the GameLevel owns the Simulation and the GameWorld
    // the GameWorld owns the physics objects 
    // that are controlled by the simulation
    
    private final GameWorld world;
    private final Simulation sim;
    
    public enum LevelType {
        SAND, STONE, ICE, GRASS
    }
    
    // OPTIONAL: in addition to passing the starting coord
    // we could also pass in the dimensions of the goal area
    // right now they are defaulted to 500 x 300
    public GameLevel(LevelType levelType, int goalXCoord) {
        // the GameLevel constructor creates the simulation, the goal, 
        // and the Birdie. It then passes these, along with the specific 
        // level selected by the user to build  the GameWorld
        sim = new Simulation();
        Goal goal = new Goal(goalXCoord, new SurfaceBoundary(0),
                500, 300, "/block_brick.png");
        
        // the GameWorld contains the "physics" of the game
        world = new GameWorld(sim, goal, levelType);
        world.build();

        // might be removing InputController stuff
        ic = new InputController(world.getCamera());
        
   
    }

    public GameWorld getWorld() {
        return world;
    }

    @Override
    public void tick() {
        sim.simulate();
    }

    @Override
    public Simulation getSimulation() {
        return sim;
    }

    // might be removing InputController stuff
    public InputController getInputController() {
        return ic;
    }

}
