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

        offset = 130;
        poleLeft = -70;
        poleRight = Screen.WIDTH - offset;
    }

    public void tick() {
        dir = sim.getBirdieDirection();
        ballX = followTarget.getCenter();
        if (ballX > poleRight && followTarget.isMoving()) {
            terminalVelocity = followTarget.getVelocity();
            following = true;
        } else if (ballX < poleLeft && followTarget.isMoving()) {
            terminalVelocity = followTarget.getVelocity();
            following = true;
        }
        if (following) {
            follow();
            //System.out.println("following");
        }
        visibleObjects.clear();
    }

    public void follow() {
        //int center = followTarget.getCenter();
        if (!followTarget.isMoving()) {
            if (dir == 1) {
                // this code is purely a guard rail
                if (x > ballX) {
                    x = ballX - offset;
                    following = false;
                    terminalVelocity = 0;
                    poleRight = x + Screen.WIDTH - offset;
                    poleLeft = ballX;
                }
                //ball needs to be greater than camX+offset
                // ballX > camX + offset
                // 140 > 0 + 20
                if (x < ballX - offset) {
                    following = false;
                    terminalVelocity = 0;
                    poleRight = x + Screen.WIDTH - offset;
                    poleLeft = ballX;
                };
            }
            if (dir == -1) {
                // same guard rail
                if (x < ballX) {
                    x = ballX + offset - Screen.WIDTH;
                    following = false;
                    terminalVelocity = 0;
                    poleRight = ballX;
                    poleLeft = x + offset;
                }
                //ball needs to be less than camX+ScreenWidth - offset
                if (x > ballX + offset - Screen.WIDTH) {
                    following = false;
                    terminalVelocity = 0;
                    poleLeft = x + offset;
                    poleRight = ballX;
                }

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
