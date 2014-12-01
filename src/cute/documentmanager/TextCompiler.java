/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.documentmanager;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author forest
 */
//contains tools and methods to read/write to text file
public class TextCompiler {

    public static int numVariations;

    //takes in filepaths for sample handwriting 
    public ArrayList<String> initialize() {
        //TODO change to interact with GUI
        //make sure to count num pictures

        return null;
    }

    public ArrayList<String> askFilePaths() {
        //TODO change to interact with GUI

        return null;
    }

    //reads file, places IDs of chars needed to print file into arraylist
    public ArrayList<BufferedImage> processFile(Map<String, ArrayList<BufferedImage>> imageMap, String filePath) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        Random random = new Random();
        int numVariations = this.numVariations - 1, rand = random.nextInt(numVariations), charNum;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            charNum = reader.read();
            while (charNum != -1) {  //to end file reading 
                if (charNum == 32) { //changes char chosen after every space char
                    rand = random.nextInt(numVariations);
                }
                images.add(imageMap.get(Character.toString((char) charNum)).get(rand)); //gets a random character from map
                charNum = reader.read();
            }
        } catch (IOException e) {

        }

        return images;
    }

    //writes the images into a specified file
    public boolean writeFile(String filePath, ArrayList<BufferedImage> images) {
        int i = 0;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {
            for (BufferedImage b : images) {

                oos.writeObject(b);
                if (++i == 45) { //45 is a placeholder for when to newline
                    oos.writeChars("\n");
                    i = 0;
                }

            }
        } catch (IOException ex) {

        }

        return true;
    }

    //TODO change to interact with GUI
    public Color chooseFontColor() {

        //return black/blue/gray/red
        return null;
    }
    //idk if this is what you wanted
    public static Color setTextColor(Color color) {
        return color;
    }
    
    
    //comprehensive method 
    public boolean write(Map<String, ArrayList<BufferedImage>> imageMap) {
        ArrayList<String> filePaths = askFilePaths();

        return writeFile(filePaths.get(1), processFile(imageMap, filePaths.get(0)));
    }

}
