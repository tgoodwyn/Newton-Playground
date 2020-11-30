/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ui;

import view.graphics.GraphicsRenderer;
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
 */
public class GameScreen extends JPanel {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 300;
    private final Timer stopwatch;
    public GraphicsRenderer renderer;
    private GameLevel level;
    public static final HighScoreManager hsm = new HighScoreManager();
    
    public GameScreen() {
        //super(cm, name);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // light blue        
        this.setBackground(new Color(154, 218, 252));
        // these two methods allow key events
        //addKeyListener(this);
        setFocusable(true);

        // TODO: code for selecting current level goes here
        // user selection will be from an enum GameLevel.Levels
        // defaulting here to STONE
        // also passing in the X position where the goal begins
        int goalXStart = 400; // default
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

    public void newLevel(int goalXStart, GameLevel.LevelType levelType) {
        level = new GameLevel(levelType, goalXStart);
        // create a renderer and attach the game logic, via the level camera
        renderer = new GraphicsRenderer(level.getWorld().getCamera(), level);
    }

    public String getMe() {
        return "HELLO FROM SCREEN";
    }

    public GameLevel getLevel() {
        return level;
    }

    public GraphicsRenderer getRenderer() {
        return renderer;
    }
}
