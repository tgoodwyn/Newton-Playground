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
    
    public static final int triggerOffset = 200;
    private int followTrigger;
    private boolean following;

    public Camera(int x, int y, int w, int h, Simulation s, Birdie birdie) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, Screen.WIDTH, Screen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = birdie;
    }

    public void tick() {
        int dir = sim.getBirdieDirection();
        //System.out.println("dir = " + dir);

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
    }

    public void snap() {
        viewPlane.x = x;
        viewPlane.y = y;

    }

    /* generic debug code 
    boolean done = false;
                if (!done) {
                done = true;
                }
    */

    public void clip() {
        int c = 0;
        for (DrawableObject i : sim.getAllDrawable()) {
            if (viewPlane.intersects(i)) {
                int screenX = i.x - (x);
                int screenY = -(i.y - (y));
                i.setScreenX(screenX);
                i.setScreenY(screenY);
                visibleObjects.add(i);
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
