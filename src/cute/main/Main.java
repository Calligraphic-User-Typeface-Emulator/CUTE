
package cute.main;

import java.io.File;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;

public class Main {

    public static void main(String[] args){
        File image = new File("external\\tesseract\\eurotext.tif");
        Tesseract1 tess = new Tesseract1();
        tess.setDatapath("external\\tesseract\\tessdata");
        
        try{
            System.out.println(image.canRead());
            String result = tess.doOCR(image);
            System.out.print(result);
        }
        catch(TesseractException e){
            System.out.print(e.getMessage());
        }
    }
    
}
