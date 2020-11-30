/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
    private static final int statusBarLabelY = 25;
    private static final int statusBarBorderY = 2;
    private static final int statusBarBorderH = 35;
    private static final int statusBarContentY = 65;
    //25,40,65

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
        //drawEND(g);

    }

    public void drawScreenObjects(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Simulation sim = level.getSimulation();
        g2.setFont(new Font("Arial", Font.PLAIN, 20));
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3));
        drawDistance(g2, sim);
        drawStatus(g2, sim);
        drawScore(g2, sim);
                g2.setFont(new Font("Arial", Font.PLAIN, 24));

                    g2.drawString("Now playing...", 625, 25);
                    g2.drawString("Newton Golf!", 625, 55);
            g2.drawString("", 650, 150);

        if (level.getWinStatus()) {
            g2.drawString("partner", 650, 250);
        } else {
            g2.drawString("howdy", 650, 250);

        }
    }

    
    public void drawScore(Graphics2D g2, Simulation sim) {
        g2.drawRect(445, statusBarBorderY, 124, statusBarBorderH);
        g2.drawString("# STROKES", 450, statusBarLabelY);
        String strokes =  String.valueOf(level.getStrokeCount()) ;
        g2.drawString(strokes, 500, statusBarContentY);
    }
    public void drawDistance(Graphics2D g2, Simulation sim) {
        // first get direction to goal as an int
        int iDir = sim.getBirdieDirection();
        // translate to being either "Right" or "Left"
        String dir = (iDir > 0) ? ">>>>>" : "<<<<<";

        // Put the draw code for static screen objects here
        g2.drawRect(44, statusBarBorderY, 188, statusBarBorderH);
        g2.drawString("DISTANCE", 80, statusBarLabelY);

        if (iDir > 0) {
            g2.drawString((int) (sim.getDistanceToGoal()) + " meters  " + dir, 50, statusBarContentY);
        } else {
            g2.drawString(dir + "  " + (int) (sim.getDistanceToGoal()) + " meters", 50, statusBarContentY);
        }
    }

    public void drawStatus(Graphics2D g2, Simulation sim) {

        g2.drawRect(295, statusBarBorderY, 108, statusBarBorderH);
        g2.drawString("STATUS", 310, statusBarLabelY);
        String launch = (sim.isInputAllowed()) ? "  READY" : " WAITING";
        g2.drawString(launch, 300, statusBarContentY);
    }

    public void drawEND(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 28));

        //g.fillRect(200, 100, 400, 160);
        drawBG(g, popupX, popupY, popupWidth, popupHeight);
        g.drawRect(popupX, popupY, popupWidth, popupHeight);
        //g.setColor(Color.WHITE);

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
