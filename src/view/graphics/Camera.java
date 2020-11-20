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

    private int dir;
    private int ballX;
    private boolean following = false;
    private int offset;
    private double terminalVelocity;
    private int poleLeft;
    private int poleRight;

    public Camera(int x, int y, int w, int h, Simulation s, Birdie birdie) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, Screen.WIDTH, Screen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = birdie;
        ballX = followTarget.getCenter();

        offset = (ballX - x) + 80;
        poleLeft = x + offset;
        poleRight = Screen.WIDTH - offset;
    }

    public void tick() {
        dir = sim.getBirdieDirection();
        ballX = followTarget.getCenter();
        //boolean moving = followTarget.isMoving();

        //if (!following && moving) {
        //if (dir == 1) {
        if (ballX > poleRight) {
            terminalVelocity = followTarget.getVelocity();
            following = true;
        }
        //} else if (dir == -1) {
        if (ballX < poleLeft) {
            terminalVelocity = followTarget.getVelocity();
            following = true;

            //}
            //}
        }
        if (following) {
            follow();
        }
        visibleObjects.clear();
    }

    public void follow() {
        //int center = followTarget.getCenter();
        if (!followTarget.isMoving()) {
            int newPoleLeft = (ballX - offset);
            int newPoleRight = (ballX + offset - Screen.WIDTH);
            boolean ballLeft = (x > newPoleLeft);
            boolean ballRight = (x < newPoleRight);
            if (dir == 1 && ballLeft) {
                following = false;
                terminalVelocity = 0;
            }
            if (dir == -1 && ballRight) {
                following = false;
                terminalVelocity = 0;
            }
        }
        if (following) {
            int dampAbs = 1;
            int dampAmt = (terminalVelocity > 0) ? dampAbs : -dampAbs;
            if (followTarget.isMoving()) {
                x += terminalVelocity;
            } else {
                x += dampAmt;
            }
        }
    }

    public void setNewFollowTrigger(int center) {
        int dir = sim.getBirdieDirection();
        int newTrigger = (dir > 0)
                ? (x + Screen.WIDTH) - center : x + center;
        //this.followTrigger = newTrigger;
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

    public int getFollowTrigger() {
        return 0;
        //followTrigger;
    }

    public int getCameraOffset() {
        return offset;
    }

    public int getPoleLeft() {
        return poleLeft;
    }

    public int getPoleRight() {
        return poleRight;
    }

}
