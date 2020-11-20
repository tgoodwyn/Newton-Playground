/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ui;

import cardMgt.CardManager;
import cards.Card;
import view.graphics.GraphicsRenderer;
import model.GameLevel;
import java.awt.Color;
import java.awt.Dimension;
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
import static view.ui.Screen.HEIGHT;
import static view.ui.Screen.WIDTH;

/**
 *
 * @author team 2
 */
public class GameScreen extends Card implements KeyListener {

    private final Timer stopwatch;
    public GraphicsRenderer renderer;
    private final GameLevel level;
    
    public GameScreen(CardManager cm, String name) {
        super(cm, name);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // light blue        
        this.setBackground(new Color(154, 218, 252));
        // these two methods allow key events
        addKeyListener(this);
        setFocusable(true);
        
        // TODO: code for selecting current level goes here
        // user selection will be from an enum GameLevel.Levels
        // defaulting here to STONE
        // also passing in the X position where the goal begins
        int goalXStart = 1800; // default
        level = new GameLevel(GameLevel.LevelType.STONE, goalXStart);
        
        // create a renderer and attach the game logic, via the level camera
        renderer = new GraphicsRenderer(level.getWorld().getCamera(), level);
        // starts the game loop
        stopwatch = new Timer(17, gameTimer);
        stopwatch.start();

    }

    ActionListener gameTimer = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            level.tick(); // physics update
            repaint(); // graphics update

        }
    };

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderer.render(g);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        level.getInputController().keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        level.getInputController().keyReleased(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}

        // determine which parameters 
        // get passed into method for creating new GameLevel
        // parameters: goalXStart - the x coordinate that the goal begins
        //             ball - an object of type Birdie that has the appropriate
        //                    mass, texture, and size for the level