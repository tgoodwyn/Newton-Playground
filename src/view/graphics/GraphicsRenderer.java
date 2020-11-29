/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import view.graphics.objects.DrawableObject;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.game.logic.GameLevel;
import model.physics.Simulation;

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
        String b = "Ball x : " + (int)(sim.getBirdie().getX());
        String c = "Camera x : " + (int)(sim.getCamera().getX());
        String d = "PoleRight : " + (int)(sim.getCamera().getPoleRight());
        String e = "PoleLeft : " + (int)(sim.getCamera().getPoleLeft());
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
        
        Image tex = null;
        try {
            tex = ImageIO.read(getClass().getResourceAsStream("/Background 1.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicsRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }

        g.drawString("Name: " + name, 150, 80);
        //g.drawImage(tex, 0, 0, 1000, 300, null);

    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set name called");
    }

}
