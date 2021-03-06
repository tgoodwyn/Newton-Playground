/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author team 2
 * 
 * This class allows us to quickly create images using only
 * the file path of the image
 * 
 */
public class Texture {

    Image tex;

    public Texture(String path) {
        try {
            this.tex = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException ex) {
            Logger.getLogger(Texture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Image getTexture() {
        return tex;
    }
}
