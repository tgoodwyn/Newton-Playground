/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.physics;

/**
 *
 * @author team 2
 * 
 * This is the interface that all physics objects implement
 * It requires the implementation of a single method: tick
 * This method gets called for all physics object by
 * the instance of Simulation belonging to the GameLevel
 * 
 * It is the equivalent to an "update" method in the observer pattern, 
 * and objects that implement it are the observer objects for the physics loop
 * 
 */
public interface ISimulatable {
    
    public void tick();
    public Simulation getSimulation();
}
