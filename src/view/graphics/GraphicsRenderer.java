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

/**
 *
 * @author team 2
 */
public class GraphicsRenderer {

    private final Camera cam;
    private final GameLevel level;

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

        // Put the draw code for static screen objects here
        if (level.getWinStatus()) {
            g.drawString("partner", 50, 50);
        } else {
            g.drawString("howdy", 50, 50);

        }

    }

}
