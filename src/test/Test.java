/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author team 2
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

        
        Window window = new Window();
        Screen game = new GameScreen();
        window.init(game);
        
//        BetterRect tyler = new BetterRect(50,50,50,50);
//        BetterRect lilla = new BetterRect(60,25,25, 10);
//        System.out.println(tyler.intersects(lilla));
        
    }
    
}
