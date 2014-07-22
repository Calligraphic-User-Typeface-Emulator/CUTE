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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author forest
 */
//contains tools and methods to read/write to text file
public class TextCompiler {

    public static int numVariations = 1;
    
    //placeholder constructor
    public TextCompiler() {

    }

    //takes in filepaths for sample handwriting 
    public ArrayList<String> initialize() {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> filePaths = new ArrayList<>();
        boolean more = true;
        System.out.println("Enter a file path");
        filePaths.add(scanner.nextLine());
        while (more) {
            String s;
            System.out.println("Enter another file path or type \"@done\" to end input");
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
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> filePaths = new ArrayList<>();
        
        System.out.print("Filepath for document: ");
        filePaths.add(scanner.nextLine());
        System.out.print("Filepath for finished image: "); //may have to use println
        filePaths.add(scanner.nextLine());
        
        //first element is for the document that needs to be edited
        //second element is for the finished image
        return filePaths;
    }

    //reads file, places images of chars needed to print file into arraylist
    public ArrayList<BufferedImage> processFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

        } catch (FileNotFoundException e) {

            System.out.println("Oops. File not found.");
        } //check char to image here
        catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    //writes the images into a specified file
    public boolean writeFile(String filePath, ArrayList<BufferedImage> images) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)))) {

        } catch (IOException ex) {
            Logger.getLogger(TextCompiler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        //write to file here
        return true;
    }
    
    //comprehensive method 
    public boolean write(){
        ArrayList<String> filePaths = askFilePaths();
        return writeFile(filePaths.get(1), processFile(filePaths.get(0)));
    }

}
