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

    //private final InputController ic;

    // the GameLevel owns the Simulation and the GameWorld
    // the GameWorld owns the physics objects 
    // that are controlled by the simulation
    
    private final GameWorld world;
    private final Simulation sim;
    private boolean winStatus = false;
    private Goal goal;
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
        sim = new Simulation(levelType, goalXCoord);
        goal = new Goal(goalXCoord, new SurfaceBoundary(0),
                200, 300, "/Win Tile (1).png");
        
        // the GameWorld contains the "physics" of the game
        world = new GameWorld(sim, goal, levelType);
        world.build();

        // might be removing InputController stuff
        //ic = new InputController(world.getBirdie());
        
   
    }
    


    @Override
    public void tick() {
        sim.simulate();
        
        
        int goalStart = goal.getX();
        int goalEnd = goal.getX()+goal.getWidth();
        int birdiePos = world.getBirdie().getX();
        if (birdiePos >= goalStart && birdiePos <= goalEnd) {
            winStatus = true;
        } else {
            winStatus = false;
        }
    }

    @Override
    public Simulation getSimulation() {
        return sim;
    }

    // might be removing InputController stuff
//    public InputController getInputController() {
//        return ic;
//    }
    public boolean getWinStatus() {
        return winStatus;
    }
    public GameWorld getWorld() {
        return world;
    }
}
