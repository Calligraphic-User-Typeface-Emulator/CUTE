
package cute.vision;

import java.io.File;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.BasicConfigurator;

public class OCR {
    
    private File image;
    private Tesseract1 tess;
    private String result;
    
    public OCR(){
        image = null;
        tess = new Tesseract1();
        tess.setDatapath("external\\tesseract\\tessdata");
        BasicConfigurator.configure();
        result = null;
    }
    
    public OCR(String imagePath){
        image = new File(imagePath);
        tess = new Tesseract1();
        tess.setDatapath("external\\tesseract\\tessdata");
        BasicConfigurator.configure();
        result = null;
    }
    
    public boolean setImage(String imagePath){
        image = new File(imagePath);
        return image.canRead();
    }
    
    public File getImage(){
        return image;
    }
    
    public boolean run(){
        try{
            result = tess.doOCR(image);
            return true;
        }
        catch(TesseractException e){
            e.getMessage();
            return false;
        }
    }
    
    public String getResult(){
        return result;
    }
    
}
