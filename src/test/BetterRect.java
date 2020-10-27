/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.awt.Point;

/**
 *
 * @author tgood
 */
public class BetterRect {

    public BetterRect(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int x;
    public int y;
    public int width;
    public int height;
    
    public Boolean intersects(BetterRect r) {
        for (Point c : r.getCorners()) {
            if (this.contains(c)) return true;
        }
        return false;
    }
    
    public Point[] getCorners() {
        Point[] corners = new Point[]{
            new Point(x,y),
            new Point(x+width,y),
            new Point(x,y-height),
            new Point(x+width,y-height)
        };
        return corners;
    }
    
    public Boolean contains(Point p) {
        if (p.x >= this.x && p.x < this.x + this.width
            && p.y < this.y && p.y > this.y - this.height) return true;
        return false;
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
