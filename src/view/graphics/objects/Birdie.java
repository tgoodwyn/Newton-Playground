/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics.objects;


import model.physics.Simulation;

import utilities.Texture;
import view.graphics.objects.SpriteObject;
import view.graphics.Camera;

/**
 *
 * @author team 2
 * 
 * The Birdie class is the object that the user launches and is trying
 * to land on top of the goal (checkered surface) in the game
 * 
 * It has all of physics properties of the object, including the mass 
 * which can be set dynamically by GUI ("Ball type")
 * The other physical properties are determined by how much force the user inputs,
 * calculated with the Newtonian law: f=ma
 * 
 * Once the acceleration is calculated, this is used to calculate the velocity
 * In order to ensure the ball comes to a stop, the acceleration and velocity
 * are multiplied by the friction coefficient set by the level type
 * 
 */
public class Birdie extends SpriteObject {

    private int mass;
    private double velocity;
    private double acceleration;
    private Simulation sim;
    private boolean movingStatus = false;
    private double frictionCoefficient;
    private Camera camera;
    private double center;

    public Birdie(double x, double y, int width, int height, String imagePath, int mass,
            Simulation s, int z) {
        super(x, y, width, height, new Texture(imagePath), s, z);
        this.mass = mass;
        frictionCoefficient = s.getLevelFriction();
        s.setBirdie(this);
        sim = s;
        center = (x + width) / 2;

    }

    @Override
    public void tick() {
        camera = sim.getCamera();

        if (movingStatus == true) {
            acceleration *= frictionCoefficient;
            velocity += acceleration;
            velocity *= frictionCoefficient;
            x += velocity;
            center = x + (width / 2);
            if (Math.abs(velocity) < 0.05) {
                movingStatus = false;

            }

        }

    }

    public void launch(int force) {
        if (sim.isInputAllowed()) {
            movingStatus = true;
            double rawAcceleration = force / mass;
            int dir = sim.getBirdieDirection();
            acceleration = (dir > 0) ? rawAcceleration : -rawAcceleration;
            velocity = 0;
        }

    }

    public boolean isMoving() {
        return movingStatus;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getCenter() {
        return center;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public void setMovingStatus(boolean movingStatus) {
        this.movingStatus = movingStatus;
    }
    
    

}
