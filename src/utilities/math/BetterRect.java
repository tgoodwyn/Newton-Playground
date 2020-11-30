/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities.math;

import java.awt.Point;

/**
 *
 * @author team 2
 */
public class BetterRect {

    public BetterRect(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double x;
    public double y;
    public int width;
    public int height;
    
    public Boolean intersects(BetterRect r) {
        for (Point c : r.getCorners()) {
            if (this.contains(c)) return true;
        }
        
        
        return false;
    }
    
    public Point[] getCorners() {
        int a = (int) x;
        int b = (int) y;
        Point[] corners = new Point[]{
            
            new Point(a,b),
            new Point(a+width,b),
            new Point(a,b-height),
            new Point(a+width,b-height)
        };
        return corners;
    }
    
    public Boolean contains(Point p) {
        if (p.x >= this.x && p.x < this.x + this.width
            && p.y < this.y && p.y > this.y - this.height) return true;
        return false;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
