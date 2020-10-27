/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Graphics;
import java.awt.Point;
import static java.awt.event.KeyEvent.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author tgood
 */
public class Camera extends WorldObject implements IControllable {

    BetterRect viewPlane;
    private WorldObject followTarget;
    public ArrayList<DrawableObject> visibleObjects;
    private int deltaX;
    private int deltaY;

    private int speed = 2;
    int cameraWorldInitialX;
    int cameraWorldInitialY;
    private int cameraFollowOffsetX = -50;
    private int cameraFollowOffsetY = 100;

    public Camera(int x, int y, int w, int h, Simulation s) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, Screen.WIDTH, Screen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = new ShapeObject(50, 50, 100, 50, s);
        cameraWorldInitialX = followTarget.x + cameraFollowOffsetX;
        cameraWorldInitialY = followTarget.x + cameraFollowOffsetY;
        follow();

    }

    public void follow() {
        x = followTarget.x + cameraFollowOffsetX;
        y = followTarget.y + cameraFollowOffsetY;
    }
Boolean triggered = false;
Boolean triggered2 = false;
    public void snap() {
        viewPlane.x = x;
        viewPlane.y = y;
        if (!triggered)
        System.out.println("viewplane at (" + viewPlane.x + (", " + viewPlane.y + ")"));

    }

    public void tick() {
        x += deltaX;
        y += deltaY;
        visibleObjects.clear();
//        System.out.println("camera pos is ("+x+(", "+y+")"));
        //follow();
    }

    public void clip() {
        int c =0;
        for (DrawableObject i : sim.getAllDrawable()) {
//            System.out.println("iterate");
            
            if (viewPlane.intersects(i)) {
//                System.out.println("camera x when clipped is " + x);
                triggered = true;
                int screenX = i.x - (x );
                int screenY = -(i.y - (y ));
                if (!triggered2){
                System.out.println("object cords at (" + i.x + (", " + i.y + ")"));
                System.out.println("supposed screen cords at (" + screenX + (", " + screenY + ")"));
                triggered2 = true;
                }
                i.setScreenX(screenX);
                i.setScreenY(screenY);
                visibleObjects.add(i);
            }
        }
    }

    @Override
    public void update(int keycode, Action.ActionType a) {
        if (a == PRESSED) {
            switch (keycode) {
                case (VK_UP):
                    deltaY = 1 * speed;
                    break;
                case (VK_DOWN):
                    deltaY = -1 * speed;
                    break;
                case (VK_RIGHT):
                    deltaX = 1 * speed;
                    break;
                case (VK_LEFT):
                    deltaX = -1 * speed;
                    break;
            }
        } else if (a == RELEASED) {
            switch (keycode) {
                case (VK_UP):
                    deltaY = 0;
                    break;
                case (VK_DOWN):
                    deltaY = 0;
                    break;
                case (VK_RIGHT):
                    deltaX = 0;
                    break;
                case (VK_LEFT):
                    deltaX = 0;
                    break;
            }
        }
    }
}
