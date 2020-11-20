/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Action;
import controller.IControllable;
import static controller.IControllable.PRESSED;
import static controller.IControllable.RELEASED;
import java.awt.Graphics;
import java.awt.Image;
import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_SPACE;
import static java.awt.event.KeyEvent.VK_UP;
import view.graphics.Texture;
import view.graphics.objects.SpriteObject;
import java.lang.Math;
import view.graphics.Camera;
import view.ui.Screen;

/**
 *
 * @author team 2
 */
public class Birdie extends SpriteObject implements IControllable {

    // the birdie has mass
    private int mass;
    private double velocity;
    private double acceleration;
    private double force = 900;
    private Simulation sim;
    private boolean movingStatus = false;
    private double frictionCoefficient;
    private int dampener;
    private Camera camera;
    private int center;

    public Birdie(int x, int y, int width, int height, String imagePath, int mass,
            Simulation s) {
        super(x, y, width, height, new Texture(imagePath), s);
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

//                System.out.println("current ball pos = " + x);
//                System.out.println("new trigger = " + newTrigger);
            }

        }

    }

    @Override
    public void update(int keycode, Action.ActionType a) {
        if (a == PRESSED) {
            if (keycode == VK_UP && sim.isInputAllowed() == true) {
                movingStatus = true;
                double rawAcceleration = force / mass;
                int dir = sim.getBirdieDirection();
                acceleration = (dir > 0) ? rawAcceleration : -rawAcceleration;
                velocity = 0;

            }

        }
    }

    public boolean isMoving() {
        return movingStatus;
    }

    public double getVelocity() {
        return velocity;
    }

    public int getCenter() {
        return center;
    }

}
