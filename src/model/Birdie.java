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

/**
 *
 * @author team 2
 */
public class Birdie extends SpriteObject implements IControllable {

    // the birdie has mass
    private int mass;
    private double velocity;
    private double acceleration;
    private double force = 100;
    private boolean movingStatus = false;
    private double frictionCoefficient;
    private int dampener;
    private Camera camera;
    private int center;

    public Birdie(int x, int y, int width, int height, String imagePath, int mass,
            Simulation s) {
        super(x, y, width, height, new Texture(imagePath), s);
        this.mass = mass;
        frictionCoefficient = sim.getLevelFriction();
        sim.setBirdie(this);
        camera = sim.getCamera();

    }

    @Override
    public void tick() {
        if (movingStatus == true) {
//            if (dampener < 10) {
//                velocity += acceleration * frictionCoefficient;
//
//            } else {
//                velocity += acceleration;
//            }
//
//            if (dampener > 30) {
//                velocity *= frictionCoefficient;
//                acceleration *= frictionCoefficient;
//            }
            acceleration *= frictionCoefficient;
            velocity += acceleration;
            velocity *= frictionCoefficient;
            x += velocity;
            center = (x + width) / 2;
//            dampener++;
            if (Math.abs(velocity) < 0.05) {
                movingStatus = false;
//                dampener = 0;
                int dir = sim.getBirdieDirection();
                int newTrigger = camera.getCameraOffset() * dir + center;
                camera.setFollowTrigger(newTrigger);
            }

        }

    }

    @Override
    public void update(int keycode, Action.ActionType a) {
        if (a == PRESSED) {
            if (keycode == VK_UP && sim.isInputAllowed() == true) {
                movingStatus = true;
                acceleration = force / mass;
                velocity = 0;
                System.out.println("up pressed");
                System.out.println("dampener = " + dampener);

            }

            if (keycode == VK_DOWN && sim.isInputAllowed() == true) {
                movingStatus = true;
                acceleration = -(force / mass);
                velocity = 0;
                System.out.println("down pressed");
                System.out.println("dampener = " + dampener);
            }

        }
    }

    public boolean isMoving() {
        return movingStatus;
    }

    public double getVelocity() {
        return velocity;
    }

}
