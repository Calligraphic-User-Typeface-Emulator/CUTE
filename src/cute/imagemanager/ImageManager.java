/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cute.imagemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author forest
 */

//contains tools and methods to work with images
public class ImageManager {
    
    
    public ArrayList<BufferedImage> read(ArrayList<String> fileNames){
        ArrayList<BufferedImage> images = new ArrayList<>();
        for(int i =0; i < fileNames.size(); i ++){
            try {
                images.add(ImageIO.read(new File(fileNames.get(i))));
            } catch (IOException ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return images;
    }
    
    //remove background of big image
    public void removeBackground(ArrayList<BufferedImage> images){
        
    }
    
    //make sure the picture is valid for our program
    public void validify(ArrayList<BufferedImage> images){
        
    }
    
    //split images into individual pictures of characters
    public Map<String, ArrayList<BufferedImage>> splitImages(ArrayList<BufferedImage> images){
        return null;
    }
    
    //cut the image so that the individual images are printable
    //(make sure there isnt excess white space)
    public void trim(Map<String, ArrayList<BufferedImage>> images){
        
    }
    
    
}
