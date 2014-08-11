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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author forest
 */
//contains tools and methods to read/write to text file
public class TextCompiler {

    public static int numVariations;
    public static Scanner scanner = new Scanner(System.in);

    //placeholder constructor
    public TextCompiler() {

    }

    //takes in filepaths for sample handwriting 
    public ArrayList<String> initialize() {
        //TODO change to interact with GUI
        numVariations = 0;
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
        return filePaths;
    }

    //gets filepaths for the document that needs to be "edited" and the filepath where the user wants the file
    public ArrayList<String> askFilePaths() {
        //TODO change to interact with GUI
        ArrayList<String> filePaths = new ArrayList<>();

        System.out.print("Filepath for document: ");
        filePaths.add(scanner.nextLine());
        System.out.print("Filepath for finished image: ");
        filePaths.add(scanner.nextLine());

        //first element is for the document that needs to be edited
        //second element is for the finished image
        return filePaths;
    }

    //reads file, places IDs of chars needed to print file into arraylist
    public ArrayList<BufferedImage> processFile(Map<String, ArrayList<BufferedImage>> imageMap, String filePath) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        Random random = new Random();
        int numVariations = this.numVariations - 1, rand = random.nextInt(numVariations);
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int charNum = reader.read();
            while (charNum != -1) {
                if (charNum == 32) { //changes char chosen after every space char
                    rand = random.nextInt(numVariations);
                }
                images.add(imageMap.get(Character.toString((char) charNum)).get(rand)); //gets a random character from map
                charNum = reader.read();
            }
        } catch (FileNotFoundException e) {

            System.out.println("Oops. File not found.");
        } 
        catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return images;
    }

    //writes the images into a specified file
    public boolean writeFile(String filePath, String font, ArrayList<BufferedImage> images) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {
        //TODO find out how to change color
        } catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //write to file here
        return true;
    }
    
    //TODO change to interact with GUI
    public String chooseFont() {
        String s;

        System.out.print("Choose your font (pen or pencil):");
        s = scanner.nextLine();

        while (!(s.matches("pen|pencil|Pencil|Pen"))) {
            System.out.print("Please try again. Pen or pencil?");
            s = scanner.nextLine();
        }

        return s;
    }

    //comprehensive method 
    public boolean write(Map<String, ArrayList<BufferedImage>> imageMap) {
        ArrayList<String> filePaths = askFilePaths();
        
        return writeFile(filePaths.get(1), chooseFont(), processFile(imageMap, filePaths.get(0)));
    }

}
