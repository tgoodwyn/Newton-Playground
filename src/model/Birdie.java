/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.Action;
import controller.IControllable;
import static controller.IControllable.PRESSED;
import static controller.IControllable.RELEASED;
import java.awt.Graphics;
import java.awt.Image;
import static java.awt.event.KeyEvent.VK_DOWN;
import static java.awt.event.KeyEvent.VK_LEFT;
import static java.awt.event.KeyEvent.VK_RIGHT;
import static java.awt.event.KeyEvent.VK_UP;
import view.graphics.Texture;
import view.graphics.objects.SpriteObject;

/**
 *
 * @author team 2
 */
public class Birdie extends SpriteObject implements IControllable {

    // the birdie has mass
    private int mass;
    private int deltaX;
    private int deltaY;
    private int speed = 2;

    public Birdie(int x, int y, int width, int height, String imagePath, int mass,
            Simulation s) {
        super(x, y, width, height, new Texture(imagePath), s);
        this.mass = mass;
    }

    @Override
    public void tick() {
        x += deltaX;
        y += deltaY;
    }

//    public void draw(Graphics g, int screenX, int screenY) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    @Override
    public void update(int keycode, Action.ActionType a) {
        if (a == PRESSED) {
            switch (keycode) {
                case (VK_UP):
                    deltaY = 1 * speed;
                    break;
                case (VK_DOWN):
                    deltaY = -1 * speed;
                    break;
                case (VK_RIGHT):
                    deltaX = 1 * speed;
                    break;
                case (VK_LEFT):
                    deltaX = -1 * speed;
                    break;
            }
        } else if (a == RELEASED) {
            switch (keycode) {
                case (VK_UP):
                    deltaY = 0;
                    break;
                case (VK_DOWN):
                    deltaY = 0;
                    break;
                case (VK_RIGHT):
                    deltaX = 0;
                    break;
                case (VK_LEFT):
                    deltaX = 0;
                    break;
            }
        }
    }

}
