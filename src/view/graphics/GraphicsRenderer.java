/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import view.graphics.objects.DrawableObject;
import java.awt.Graphics;
import java.util.ArrayList;
import model.GameLevel;
import model.Simulation;

/**
 *
 * @author team 2
 */
public class GraphicsRenderer {

    private final Camera cam;
    private final GameLevel level;
    
    private String name = "Anonymous";
    public GraphicsRenderer(Camera c, GameLevel level) {
        this.cam = c;
        this.level = level;
    }

    public void render(Graphics g) {

        cam.snap(); //
        cam.clip();
        for (DrawableObject d : cam.visibleObjects) {
            d.draw(g);
        }

        Simulation sim = level.getSimulation();
        int iDir = sim.getBirdieDirection();
        String dir = (iDir > 0) ? "Right" : "Left";
        // Put the draw code for static screen objects here
        g.drawString("Direction: " + dir, 150, 50);
        String a = (sim.isInputAllowed()) ? "Input yes" : "Input no";
        String b = "Ball x : " + (sim.getBirdie().getX());
        String c = "Camera x : " + (sim.getCamera().getX());
        String d = "PoleRight : " + (sim.getCamera().getPoleRight());
        String e = "PoleLeft : " + (sim.getCamera().getPoleLeft());
        g.drawString(a, 250, 50);
        g.drawString(b, 350, 50);
        g.drawString(c, 450, 50);
        g.drawString(d, 550, 50);
        g.drawString(e, 650, 50);
        if (level.getWinStatus()) {
            g.drawString("partner", 50, 50);
        } else {
            g.drawString("howdy", 50, 50);

        }

        g.drawString("Name: " + name, 150, 350);

    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set name called");
    }

}
