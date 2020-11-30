/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
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
    //200, 60, 400, 160
    private static final int popupWidth = 300;
    private static final int popupHeight = 160;
    private static final int popupX = 250;
    private static final int popupY = 60;
    private static final int popupTextX = popupX + (popupWidth / 6);
    private static final int popupTextY = popupY + (popupHeight / 3);

    private String name = "Anonymous";

    public GraphicsRenderer(Camera c, GameLevel level) {
        this.cam = c;
        this.level = level;
    }

    public void render(Graphics g) {
        Image tex = null;
        try {
            tex = ImageIO.read(getClass().getResourceAsStream("/Background 1.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicsRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(tex, 0, 0, 800, 300, null);
        cam.snapViewToCamera(); //
        cam.transformWorldCoordsToScreen();
        cam.visibleObjects.forEach(d -> {
            d.draw(g);
        });
        drawScreenObjects(g);
        drawEND(g);

    }

    public void drawScreenObjects(Graphics g) {
        Simulation sim = level.getSimulation();

        // first get direction to goal as an int
        int iDir = sim.getBirdieDirection();
        // translate to being either "Right" or "Left"
        String dir = (iDir > 0) ? "Right" : "Left";
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        // Put the draw code for static screen objects here
        g.drawString("Direction: " + dir, 150, 50);
        String a = (sim.isInputAllowed()) ? "Input yes" : "Input no";
        String b = "Ball x : " + (int) (sim.getBirdie().getX());
        String c = "Camera x : " + (int) (sim.getCamera().getX());
        String d = "PoleRight : " + (int) (sim.getCamera().getPoleRight());
        String e = "PoleLeft : " + (int) (sim.getCamera().getPoleLeft());
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

        g.drawString("Name: " + name, 150, 80);
    }

    public void drawEND(Graphics g) {
        g.setColor(Color.WHITE);

        //g.fillRect(200, 100, 400, 160);
        drawBG(g, popupX, popupY, popupWidth, popupHeight);
        g.drawRect(popupX, popupY, popupWidth, popupHeight);
        //g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 28));

        g.drawString("Game over, mate", popupTextX, popupTextY);
    }

    public void drawBG(Graphics g, int x, int y, int w, int h) {
        Image tex = null;
        try {
            tex = ImageIO.read(getClass().getResourceAsStream("/Background 4.png"));
        } catch (IOException ex) {
            Logger.getLogger(GraphicsRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(tex, x, y, w, h, null);
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("set name called");
    }

}
