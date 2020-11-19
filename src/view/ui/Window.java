/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.ui;

import javax.swing.JFrame;

/**
 *
 * @author team 2
 */
public class Window extends JFrame{
    

    
    public Window() {
    }
    
    public void init(Screen startScreen) {
        // boilerplate 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        // set the starting JPanel for the window
        this.add(startScreen);
        this.pack();

        // put window in middle of screen and show it
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}