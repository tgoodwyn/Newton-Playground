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

    public Birdie(int x, int y, int width, int height, String imagePath, int mass,
            Simulation s) {
        super(x, y, width, height, new Texture(imagePath), s);
        this.mass = mass;
        frictionCoefficient = sim.getLevelFriction();
        sim.setBirdie(this);

    }

    @Override
    public void tick() {

        if (movingStatus == true) {
            if (dampener < 10) {
                velocity += acceleration * frictionCoefficient;

            } else {
                velocity += acceleration;
            }
            x += velocity;
            dampener++;
        }
        if (dampener > 30) {
            velocity *= frictionCoefficient;
            acceleration *= frictionCoefficient;
        }
        if (Math.abs(velocity) < 0.05) {
            movingStatus = false;
            dampener = 0;
        }

    }

    @Override
    public void update(int keycode, Action.ActionType a) {
        if (a == PRESSED) {
            if (keycode == VK_UP && movingStatus == false) {
                movingStatus = true;
                acceleration = force / mass;
                velocity = 0;
                System.out.println("up pressed");
                System.out.println("dampener = " + dampener);

            }

            if (keycode == VK_DOWN && movingStatus == false) {
                movingStatus = true;
                acceleration = -(force / mass);
                velocity = 0;
                System.out.println("down pressed");
                System.out.println("dampener = " + dampener);
            }

        }
    }

}

//switch (keycode) {
//                case (VK_UP):
//                    deltaY = 1 * speed;
//                    break;
//                case (VK_DOWN):
//                    deltaY = -1 * speed;
//                    break;
//                case (VK_RIGHT):
//                    velocityX = 1 * speed;
//                    break;
//                case (VK_LEFT):
//                    velocityX = -1 * speed;
//                    break;
//            }
//        } else if (a == RELEASED) {
//            switch (keycode) {
//                case (VK_UP):
//                    deltaY = 0;
//                    break;
//                case (VK_DOWN):
//                    deltaY = 0;
//                    break;
//                case (VK_RIGHT):
//                    velocityX = 0;
//                    break;
//                case (VK_LEFT):
//                    velocityX = 0;
//                    break;
//            }
