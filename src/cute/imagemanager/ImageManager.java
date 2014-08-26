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
import javax.imageio.ImageIO;

/**
 *
 * @author forest
 */
//contains tools and methods to work with images
public class ImageManager {

    private ArrayList<String> CHARS = new ArrayList<>();
    private ArrayList<BufferedImage> space = null;   
    private final int imageDivider = 5; // thats a place holder
    private Map<String, ArrayList<BufferedImage>> images = new HashMap<>();

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
        fileNames.forEach((s) -> {
            try {
                images.add(ImageIO.read(new File(s)));
            } catch (IOException ex) {
               
            }
        });

        return images;
    }

    //make sure the picture is valid for our program
    public boolean validify(ArrayList<BufferedImage> images) {
        return false;
    }

    //split images into individual pictures of characters
    public void splitImages(ArrayList<BufferedImage> images) {
        int height, width, x, y;
        

        CHARS.forEach(s -> {
            this.images.put(s, new ArrayList<>());
        });

        for (BufferedImage b : images) {
            x = 0;
            y = 0;
            height = b.getHeight() / imageDivider;
            width = b.getWidth() / imageDivider;

            for (String s : CHARS) {
                if (x + width >= b.getWidth() && y + height >= b.getHeight()) {
                    break;
                }
                this.images.get(s).add(b.getSubimage(x, y, height, width)); //adds image of each letter to corresponding array part

                if (x + width >= b.getWidth()) {                       //iterates through height/width 
                    y += height;
                    x = 0;
                } else {
                    x += width;
                }

            }
        }

    }

    //cut the image so that the individual images are printable
    //(make sure there isnt excess white space)
    public Map<String, ArrayList<BufferedImage>> trim(Map<String, ArrayList<BufferedImage>> images) {
        return null;
    }

    public Map<String, ArrayList<BufferedImage>> getCharMap() {
        return this.images;
    }

    //runs everything 
    public Map<String, ArrayList<BufferedImage>> run(ArrayList<String> fileNames) {
        //if (validify)
//        splitImages(removeBackground(read(fileNames)));
//        return trim(this.images);
        return null;
    }

}
