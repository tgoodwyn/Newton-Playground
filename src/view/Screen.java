/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import cards.Card;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 *
 * @author team 2
 */
public class Screen extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;
    
    public Screen() {
        // right now all screens will have same size
        //super("")
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
