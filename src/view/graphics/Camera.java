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
    private WorldObject followTarget;
    public ArrayList<DrawableObject> visibleObjects;


    int cameraWorldInitialX;
    int cameraWorldInitialY;
    private int cameraFollowOffsetX = -50;
    private int cameraFollowOffsetY = 100;

    public Camera(int x, int y, int w, int h, Simulation s, Birdie birdie) {
        super(x, y, w, h, s);
        this.viewPlane = new BetterRect(x, y, Screen.WIDTH, Screen.HEIGHT);
        visibleObjects = new ArrayList<DrawableObject>();
        followTarget = birdie;
        cameraWorldInitialX = followTarget.x + cameraFollowOffsetX;
        cameraWorldInitialY = followTarget.x + cameraFollowOffsetY;
        follow();

    }

    public void follow() {
        x = followTarget.x + cameraFollowOffsetX;
        y = followTarget.y + cameraFollowOffsetY;
    }
    
    /* code for debug purposes
    Boolean triggered = false;
    Boolean triggered2 = false;
    */
    
    public void snap() {
        viewPlane.x = x;
        viewPlane.y = y;

        /* code for debug purposes
        if (!triggered)
        System.out.println("viewplane at (" + viewPlane.x + (", " + viewPlane.y + ")"));
         */
    }

    public void tick() {
        // follow code should go here
        follow();
        visibleObjects.clear();
    }

    public void clip() {
        int c = 0;
        for (DrawableObject i : sim.getAllDrawable()) {

            if (viewPlane.intersects(i)) {
                int screenX = i.x - (x);
                int screenY = -(i.y - (y));

                /* code for debug purposes
                triggered = true;
                if (!triggered2){
                System.out.println("object cords at (" + i.x + (", " + i.y + ")"));
                System.out.println("supposed screen cords at (" + screenX + (", " + screenY + ")"));
                triggered2 = true;
                }*/
                i.setScreenX(screenX);
                i.setScreenY(screenY);
                visibleObjects.add(i);
            }
        }
    }


}
