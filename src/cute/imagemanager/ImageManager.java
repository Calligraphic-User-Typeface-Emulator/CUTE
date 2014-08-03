/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.imagemanager;

import java.awt.Color;
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
    private BufferedImage space = null;   //will be an arraylist
    private final int imageDivider = 5; // thats a place holder
    private final Color transparent = new Color(0, 0, 0, 0);

    //initializes CHARS arraylist and SPACE arraylist
    public ImageManager() {

        try (Scanner scanner = new Scanner(new File("Characters")).useDelimiter(" ");) {

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
    //assumes picture background is black, user uses different color to write their letters
    // *NOTE* may be super slow, if so, may use threads 
    public ArrayList<BufferedImage> removeBackground(ArrayList<BufferedImage> images) {
        int whiteRGB = Color.WHITE.getRGB(), blackRGB = Color.BLACK.getRGB();       
        ArrayList<BufferedImage> alteredImages = new ArrayList<>();
        
        images.forEach((b) -> {                  //dat lambda expression tho :3
            int height = b.getHeight(), width = b.getWidth();   //apparently lambda expressions can only access FINAL local vars
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (b.getRGB(i, j) == blackRGB || b.getRGB(i, j) == whiteRGB) { //makes everything except for user's writng
                        b.setRGB(i, j, transparent.getRGB());                       //transparent for easier printing

                    }
                }
            }
            alteredImages.add(b);

        });

        return alteredImages;
    }

    //make sure the picture is valid for our program
    public boolean validify(ArrayList<BufferedImage> images) {
        return false;
    }

    //split images into individual pictures of characters
    public Map<String, ArrayList<BufferedImage>> splitImages(ArrayList<BufferedImage> images) {
        int height, width, x, y;
        ArrayList<ArrayList<BufferedImage>> allSubimages = new ArrayList<>(); //contains arraylist of arraylist of images
        Map<String, ArrayList<BufferedImage>> allImages = new HashMap<>();

        for(int i =0; i < CHARS.size(); i++){
            allSubimages.add(new ArrayList<>());
        } 
            
        
        for (BufferedImage b : images) {
            x = 0;
            y = 0;
            height = b.getHeight() / imageDivider;
            width = b.getWidth() / imageDivider;

            for (ArrayList<BufferedImage> a : allSubimages) {
                if (x + width >= b.getWidth() && y + height >= b.getHeight()) {
                    break;
                }

                a.add(b.getSubimage(x, y, height, width));      //adds image of each letter to corresponding array part

                if (x + width >= b.getWidth()) {                       //iterates through height/width 
                    y += height;
                    x = 0;
                } else {
                    x += width;
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
