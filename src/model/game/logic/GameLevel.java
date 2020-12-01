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
import view.graphics.objects.Birdie;
import model.physics.ISimulatable;
import model.physics.Simulation;
import model.game.objects.Goal;
import model.game.objects.SurfaceBoundary;
import view.graphics.Camera;
import view.ui.GameScreen;
import view.ui.MainWindow;

/**
 *
 * @author team 2
 * the GameLevel class is the container for the GameWorld
 * and the caller of the Simulation.simulate method
 * A new GameLevel is created every time the "new game" 
 * button is clicked
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

    /**
     *the GameLevel constructor creates the simulation, the goal, 
     *and the Birdie. It then passes these, along with the specific 
     *level selected by the user to build  the GameWorld
     * @param levelType
     * @param goalXCoord
     * @param gs
     */
    public GameLevel(LevelType levelType, int goalXCoord, GameScreen gs) {
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
        checkFail();
    }

    public void checkFail() {
        Birdie birdie = world.getBirdie();
        double birdiePos = birdie.getCenter();
        Camera cam = sim.getCamera();
        if (birdiePos >= world.WORLD_END) {
            birdie.setMovingStatus(false);
            birdie.setX(world.WORLD_END);
            winStatus = false;
            gameOver = true;
            cam.setFollowing(false);
            cam.setX(world.WORLD_END - 500);
        }
        if (birdiePos <= world.WORLD_BEGIN) {
            birdie.setMovingStatus(false);
            birdie.setX(world.WORLD_BEGIN);
            winStatus = false;
            gameOver = true;

            cam.setFollowing(false);
            cam.setX(world.WORLD_BEGIN - 500);

        }

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
