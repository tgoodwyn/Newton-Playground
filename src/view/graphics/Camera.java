/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import utilities.BetterRect;
import view.graphics.objects.DrawableObject;
import java.util.ArrayList;
import java.util.Collections;
import view.graphics.objects.Birdie;
import model.physics.Simulation;
import model.physics.WorldObject;
import view.ui.GameScreen;

/**
 *
 * @author team 2
 * 
 * The most complicated class in the project...
 * 
 * Performs the following jobs:
 * - Determines which DrawableObjects are actually visible
 * - Transforms visibleObject's world coordinates to screen coordinates
 * - Follows the Birdie dynamically as it moves along the level
 * - Follow code is purely a luxury but is designed to reduce motion sickness!
 * 
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
    // the two "poles" are the boundaries that the ball must cross
    // in order for the camera to begin following it
    // this area inside the poles is referred to as the camera "trap"
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
            // the velocity and direction (memdir) at time of the ball
            // exceeding the boundaries of the camera "trap" 
            // are stored and used in the follow function
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

    /**
     * The way the camera follows the Birdie would be considered a combination
     * of a "trap" camera and "look-ahead" camera
     * These types of camera are described in the following video:
     * https://www.youtube.com/watch?v=l9G6MNhfV7M
     */
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
                    if (x < ballX + offset - GameScreen.WIDTH) {
                        following = false;
                        terminalVelocity = 0;
                        poleRight = ballX;
                        poleLeft = x + offset;
                    }
                } else if (memdir == 1) {
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
            // the camera slows down once the ball stops moving
            // this speed is called the damp speed
            // and is the speed the camera moves while catching up to the ball
            // and while readjusting itself so that the greatest visible area
            // is in the direction of the goal
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

    public void setFollowing(boolean following) {
        this.following = following;
    }
    
    

}
