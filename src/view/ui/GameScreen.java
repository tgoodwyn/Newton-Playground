/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ui;

import view.graphics.GameGraphics;
import model.game.logic.GameLevel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import utilities.highScores.HighScoreManager;

/**
 *
 * @author team 2
 * 
 * This class inherits from JPanel and contains all graphical elements
 * pertaining to the display of the game
 * 
 * It also creates and run the "game loop", which is set by a Swing timer that 
 * calls the two main publisher loops: the physics loop and the graphics loop
 */
public class GameScreen extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 300;
    private final Timer stopwatch;
    public GameGraphics renderer;
    private GameLevel level;
    public static final HighScoreManager hsm = new HighScoreManager();
    
    public GameScreen() {
        //super(cm, name);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // light blue        
        this.setBackground(new Color(154, 218, 252));
        //setFocusable(true);

  
        //  passing in the X position where the goal begins
        int goalXStart = 750; // default
        level = new GameLevel(GameLevel.LevelType.GRASS, goalXStart, this);

        // create a renderer and attach the game logic, via the level camera
        renderer = new GameGraphics(level.getWorld().getCamera(), level);
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
        renderer.drawGame(g);

    }

    public void newLevel(int goalXStart, GameLevel.LevelType levelType) {
        level = new GameLevel(levelType, goalXStart, this);
        // create a renderer and attach the game logic, via the level camera
        renderer = new GameGraphics(level.getWorld().getCamera(), level);
    }



    public GameLevel getLevel() {
        return level;
    }

    public GameGraphics getRenderer() {
        return renderer;
    }

    public static HighScoreManager getHsm() {
        return hsm;
    }
    
    
}
