/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.GameLogic;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Timer;

/**
 *
 * @author team 2
 */
public class GameScreen extends Screen implements KeyListener {

    private final Timer stopwatch;
    public GraphicsRenderer renderer;
    private final GameLogic gl;

    public GameScreen() {
        super();
        // light blue        
        this.setBackground(new Color(154, 218, 252));
        // these two methods allow key events
        addKeyListener(this);
        setFocusable(true);

        // create the model for the game
        gl = new GameLogic();
        // create a renderer and attach the game logic, via the level camera
        renderer = new GraphicsRenderer(gl.getLevel().getCamera());
        // starts the game loop
        stopwatch = new Timer(17, gameTimer);
        stopwatch.start();

    }

    ActionListener gameTimer = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            gl.tick(); // physics update
            repaint(); // graphics update

        }
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gl.getInputController().keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gl.getInputController().keyReleased(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
