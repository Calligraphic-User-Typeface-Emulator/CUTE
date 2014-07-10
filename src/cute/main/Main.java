
package cute.main;

import java.io.File;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.BasicConfigurator;

public class Main {

    public static void main(String[] args){
        Tesseract1 tess = new Tesseract1();                                     //instantiate tesseract object
        tess.setDatapath("external\\tesseract\\tessdata");                      //set search path for tess4j package
        
        File image = new File("external\\tesseract\\samples\\eurotext.png");    //get picture to do ocr on
        
        BasicConfigurator.configure();                                          //configure log4j package
        
        try{
            System.out.println(image.canRead() + "\n");                         //see if the file is valid image
            String result = tess.doOCR(image);                                  //perform ocr
            System.out.print(result);                                           //show results
        }
        catch(TesseractException e){
            System.out.print(e.getMessage());
        }
    }
    
}
