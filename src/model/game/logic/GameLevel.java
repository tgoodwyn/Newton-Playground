/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.logic;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import model.physics.ISimulatable;
import model.physics.Simulation;
import model.game.objects.Goal;
import model.game.objects.SurfaceBoundary;
import view.ui.GameScreen;
import view.ui.MainWindow;

/**
 *
 * @author team 2
 */
public class GameLevel implements ISimulatable {

    //private final InputController ic;
    // the GameLevel owns the Simulation and the GameWorld
    // the GameWorld owns the physics objects 
    // that are controlled by the simulation
    private final GameWorld world;
    private final Simulation sim;
    private boolean winStatus = false;
    private Goal goal;
    private int strokeCount;
    private boolean gameOver = false;
    private GameScreen gamePanel;
    private boolean updated = false;

    public enum LevelType {
        SAND, STONE, ICE, GRASS
    }

    // OPTIONAL: in addition to passing the starting coord
    // we could also pass in the dimensions of the goal area
    // right now they are defaulted to 500 x 300
    public GameLevel(LevelType levelType, int goalXCoord, GameScreen gs) {
        // the GameLevel constructor creates the simulation, the goal, 
        // and the Birdie. It then passes these, along with the specific 
        // level selected by the user to build  the GameWorld
        goal = new Goal(goalXCoord, new SurfaceBoundary(0),
                200, 300, "/WinTile.png");
        sim = new Simulation(levelType, goal, this);
        gamePanel = gs;
        // the GameWorld contains the "physics" of the game
        world = new GameWorld(sim, goal, levelType);
        world.build();
        strokeCount = 0;
        updated = false;
    }

    @Override
    public void tick() {
        sim.simulate();
        checkWin();
    }

    public void checkWin() {
        int goalStart = goal.getX();
        int goalEnd = goal.getX() + goal.getWidth();
        double birdiePos = world.getBirdie().getCenter();
        if (!world.getBirdie().isMoving()) {
            if (birdiePos >= goalStart && birdiePos <= goalEnd) {
                winStatus = true;
                gameOver = true;
                if (!updated) {
                updateScoresDB();
                }
            }
        }
    }

    public void updateScoresDB() {
        MainWindow topFrame = (MainWindow) SwingUtilities.getWindowAncestor(gamePanel);
        String name = topFrame.getPlayerName();
        int score = strokeCount;
        gamePanel.getHsm().addScore(name, score);
        JTextArea scoreBoard = topFrame.getScoreBoard();
        scoreBoard.setText(gamePanel.getHsm().getHighscoreString());
        updated = true;
    }

    public void addToStrokeCount() {
        strokeCount++;
    }

    @Override
    public Simulation getSimulation() {
        return sim;
    }

    public boolean getWinStatus() {
        return winStatus;
    }

    public GameWorld getWorld() {
        return world;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
    
}
