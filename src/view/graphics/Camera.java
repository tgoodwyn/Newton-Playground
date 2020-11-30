/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import utilities.math.BetterRect;
import view.graphics.objects.DrawableObject;
import java.util.ArrayList;
import java.util.Collections;
import model.game.objects.Birdie;
import model.physics.Simulation;
import model.game.objects.WorldObject;
import view.ui.GameScreen;

/**
 *
 * @author team 2
 */
public class Camera extends WorldObject {

    BetterRect viewPlane;
    private Birdie followTarget;
    public ArrayList<DrawableObject> visibleObjects;

    private int dir;
    private double ballX;
    private boolean following = false;
    private int memdir = 0;
    private int offset;
    private double terminalVelocity;
    private double poleLeft;
    private double poleRight;

    public Camera(int x, int y, int w, int h, Simulation s, Birdie birdie) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, GameScreen.WIDTH, GameScreen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = birdie;
        ballX = followTarget.getCenter();

        offset = 130;
        poleLeft = -70;
        poleRight = GameScreen.WIDTH - offset;
    }
    
    public void sortVisible() {
        ViewComparator comparator = new ViewComparator();
        Collections.sort(visibleObjects, comparator);
        
    }

    public void tick() {
        dir = sim.getBirdieDirection();
        ballX = followTarget.getCenter();
        if (ballX > poleRight && followTarget.isMoving()) {
            terminalVelocity = followTarget.getVelocity();
            memdir = 1;
            following = true;
        } else if (ballX < poleLeft && followTarget.isMoving()) {
            terminalVelocity = followTarget.getVelocity();
            memdir = -1;
            following = true;
        }
        if (following) {
            follow();
        }
        visibleObjects.clear();
    }

    public void follow() {
        if (!followTarget.isMoving()) {
            if (dir == 1) {
                if (memdir == 1) {
                    // this code is purely a guard rail
                    if (x > ballX - offset) {
                        following = false;
                        terminalVelocity = 0;
                        poleRight = x + GameScreen.WIDTH - offset;
                        poleLeft = ballX;
                    }
                } else if (memdir == -1) {
                    if (x < ballX - offset) {
                        following = false;
                        terminalVelocity = 0;
                        poleRight = x + GameScreen.WIDTH - offset;
                        poleLeft = ballX;
                    }
                }
            }
            if (dir == -1) {
                if (memdir == -1) {
                    // same guard rail
                    if (x < ballX + offset - GameScreen.WIDTH) {
                        following = false;
                        terminalVelocity = 0;
                        poleRight = ballX;
                        poleLeft = x + offset;
                    }
                } else if (memdir == 1) {

                    //ball needs to be less than camX+ScreenWidth - offset
                    if (x > ballX + offset - GameScreen.WIDTH) {
                        following = false;
                        terminalVelocity = 0;
                        poleLeft = x + offset;
                        poleRight = ballX;
                    }
                }

            }

        }
        if (following) {
            int dampAbs = 3;
            int dampAmt = (terminalVelocity > 0) ? dampAbs : -dampAbs;
            if (followTarget.isMoving()) {
                x += terminalVelocity;
            } else {
                x += dampAmt;
            }
        }
    }

    public void snapViewToCamera() {
        viewPlane.x = x;
        viewPlane.y = y;

    }

    /* generic debug code 
    boolean done = false;
                if (!done) {
                done = true;
                }
     */
    public void transformWorldCoordsToScreen() {
        int c = 0;
        for (DrawableObject obj : sim.getAllDrawable()) {
            if (viewPlane.intersects(obj)) {
                double screenX = obj.x - (x);
                double screenY = -(obj.y - (y));
                obj.setScreenX((int)screenX);
                obj.setScreenY((int)screenY);
                visibleObjects.add(obj);
            }
        }
    }

    public boolean isFollowing() {
        return following;
    }



    public int getCameraOffset() {
        return offset;
    }

    public double getPoleLeft() {
        return poleLeft;
    }

    public double getPoleRight() {
        return poleRight;
    }

}
