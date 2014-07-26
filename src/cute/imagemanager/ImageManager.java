/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.imagemanager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author forest
 */
//contains tools and methods to work with images
public class ImageManager {

    public static ArrayList<String> CHARS = new ArrayList<>();
    public static BufferedImage SPACE = null;   //will be an arraylist

    public static int IMAGE_DIVIDER = 100; // thats a place holder

    //initializes CHARS arraylist and SPACE arraylist
    public ImageManager() {

        try (Scanner scanner = new Scanner(new File("Characters")).useDelimiter("@");) {

            while (scanner.hasNextLine()) {

                CHARS.add(scanner.next());

            }

        } catch (FileNotFoundException e) {

        }

        //////// have to distort space as well, then add the distorted spaces into an arraylist /////////
       /* try {
            SPACE = ImageIO.read(new File("space.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
        }*/

    }

    //takes in user input of sample handwriting
    public ArrayList<BufferedImage> read(ArrayList<String> fileNames) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        for (String s : fileNames) {
            try {
                images.add(ImageIO.read(new File(s)));
            } catch (IOException ex) {
                Logger.getLogger(ImageManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return images;
    }

    //remove background of big image
    public ArrayList<BufferedImage> removeBackground(ArrayList<BufferedImage> images) {
        return null;
    }

    //make sure the picture is valid for our program
    public boolean validify(ArrayList<BufferedImage> images) {
        return false;
    }

    //split images into individual pictures of characters
    public Map<String, ArrayList<BufferedImage>> splitImages(ArrayList<BufferedImage> images) {
        int height, width, x, y;
        ArrayList<ArrayList<BufferedImage>> allSubimages = new ArrayList<>();
        Map<String, ArrayList<BufferedImage>> allImages = new HashMap<>();

        for (BufferedImage b : images) {
            x = 0;
            y = 0;
            height = b.getHeight() / IMAGE_DIVIDER;
            width = b.getWidth() / IMAGE_DIVIDER;

            for (ArrayList<BufferedImage> a : allSubimages) {
                if (x >= b.getHeight() && y >= b.getWidth()) {
                    break;
                }
                
                a.add(b.getSubimage(x, y, height, width));      //adds image of each letter to corresponding array part
                
                if (x == b.getHeight()) {                       //iterates through height/width 
                    y += width;
                    x = 0;
                } else {
                    x += height;
                }

            }
        }

        for (int i = 0; i < CHARS.size(); i++) {
            allImages.put(CHARS.get(i), 
                    allSubimages.get(i));
        }

        return allImages;
    }

    //cut the image so that the individual images are printable
    //(make sure there isnt excess white space)
    public Map<String, ArrayList<BufferedImage>> trim(Map<String, ArrayList<BufferedImage>> images) {
        return null;
    }

    //runs everything 
    public Map<String, ArrayList<BufferedImage>> run(ArrayList<String> fileNames) {
        //if (validify)
        return trim(splitImages(removeBackground(read(fileNames))));

    }

}
