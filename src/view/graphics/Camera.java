/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import utilities.BetterRect;
import controller.IControllable;
import view.ui.Screen;
import view.graphics.objects.DrawableObject;
import view.graphics.objects.ShapeObject;
import controller.Action;
import java.awt.Graphics;
import java.awt.Point;
import static java.awt.event.KeyEvent.*;
import java.util.ArrayList;
import java.util.Collections;
import model.Birdie;
import model.Simulation;
import model.WorldObject;
import view.ui.Screen;

/**
 *
 * @author team 2
 */
public class Camera extends WorldObject {

    BetterRect viewPlane;
    private Birdie followTarget;
    public ArrayList<DrawableObject> visibleObjects;

    private int cameraFollowOffsetX = -50;
    private int cameraFollowOffsetY = 400;

    private int followTrigger;
    private boolean following;

    public Camera(int x, int y, int w, int h, Simulation s, Birdie birdie) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, Screen.WIDTH, Screen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = birdie;
//        x = -50;
//        y = 800;
    }

    public void tick() {
        // follow code should go here
        int dir = sim.getBirdieDirection();
        System.out.println("dir = " + dir);

        boolean moving = followTarget.isMoving();
        int ballX = followTarget.x;
        if (moving) {
            if (dir == 1) {

                if (ballX > followTrigger && x < ballX) {
                    follow();
                }
            } else if (dir == -1) {

                if (ballX < followTrigger && x > ballX) {
                    follow();
                }
            } 
        } 

        visibleObjects.clear();
    }

    public void follow() {
        x += followTarget.getVelocity();
        System.out.println("should not be called");
    }

    public void snap() {
        viewPlane.x = x;
        viewPlane.y = y;
        System.out.println("camera x when snapped " + x);

    }

    boolean done = false;

    public void clip() {
        int c = 0;
        for (DrawableObject i : sim.getAllDrawable()) {
            if (viewPlane.intersects(i)) {
                int screenX = i.x - (x);
                int screenY = -(i.y - (y));
                i.setScreenX(screenX);
                i.setScreenY(screenY);
                visibleObjects.add(i);
                if (!done) {
                    System.out.println("top left box world coord x: " + i.x);
                    System.out.println("top left box world coord y: " + i.y);
                    done = true;
                }
            }
        }
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowTrigger(int followTrigger) {
        this.followTrigger = followTrigger;
    }

    public int getCameraOffset() {
        return cameraFollowOffsetX;
    }

}
