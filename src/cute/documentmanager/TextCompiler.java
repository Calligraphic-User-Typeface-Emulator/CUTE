/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.documentmanager;

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
import java.util.Scanner;

/**
 *
 * @author forest
 */
//contains tools and methods to read/write to text file
public class TextCompiler {

    public static int numVariations;
    private final Scanner scanner = new Scanner(System.in);

    //placeholder constructor
    public TextCompiler() {

    }

    //takes in filepaths for sample handwriting 
    public ArrayList<String> initialize() {
        //TODO change to interact with GUI

        //make sure to count num pictures
        /* numVariations = 0;
         ArrayList<String> filePaths = new ArrayList<>();
         System.out.print("Enter a file path: ");
         filePaths.add(scanner.nextLine());
         numVariations++;
         while (true) {
         String s;
         System.out.print("Enter another file path or type \"@done\" to end input: ");
         if ((s = scanner.nextLine()).equals("@done")) {
         break;
         } else {
         filePaths.add(s);
         numVariations++;
         }
         }
         return filePaths;*/
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
            while (charNum != -1) {
                if (charNum == 32) { //changes char chosen after every space char
                    rand = random.nextInt(numVariations);
                }
                images.add(imageMap.get(Character.toString((char) charNum)).get(rand)); //gets a random character from map
                charNum = reader.read();
            }
        } catch (FileNotFoundException e) {
            //TODO change to relay msg via gui
            System.out.println("Oops. File not found.");
        } catch (IOException ex) {

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
    public String chooseFont() {

        //return black/blue/gray/red
        return "";
    }

    //comprehensive method 
    public boolean write(Map<String, ArrayList<BufferedImage>> imageMap) {
        ArrayList<String> filePaths = askFilePaths();

        return writeFile(filePaths.get(1), processFile(imageMap, filePaths.get(0)));
    }

}
