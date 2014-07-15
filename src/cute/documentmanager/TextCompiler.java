/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.documentmanager;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
Import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author forest
 */

//contains tools and methods to read/write to text file
public class TextCompiler {

    BufferedReader reader = null;
    BufferedWriter writer = null;
    
    //reads file, places images of chars needed to print file into arraylist
    public ArrayList<BufferedImage> processFile(String filePath) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
        } catch (FileNotFoundException e) {

            System.out.println("Oops. File not found.");
        }

        //check char to image here
        try {
            reader.close();
        } catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    //writes the images into a specified file
    public void writeFile(String filePath, ArrayList<BufferedImage> images) {

        try {
            writer = new BufferedWriter(new FileWriter(new File(filePath)));
        } catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //write to file
        
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
