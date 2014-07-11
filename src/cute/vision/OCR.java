
package cute.vision;

import java.io.File;
import net.sourceforge.tess4j.Tesseract1;
import net.sourceforge.tess4j.TesseractException;
import org.apache.log4j.BasicConfigurator;

public class OCR {
    
    private File image;                                                         //image that ocr will be performed on
    private Tesseract1 tess;                                                    //tesseract object that performs ocr
    private String result;                                                      //result of ocr process
    
    public OCR(){
        image = null;
        tess = new Tesseract1();
        tess.setDatapath("external\\tesseract\\tessdata");
        BasicConfigurator.configure();
        result = null;
    }
    
    public OCR(String imagePath){                                               //overloaded constructor
        image = new File(imagePath);
        tess = new Tesseract1();
        tess.setDatapath("external\\tesseract\\tessdata");
        BasicConfigurator.configure();
        result = null;
    }
    
    public boolean setImage(String imagePath){                                  //set image for ocr to be performed on
        image = new File(imagePath);
        return image.canRead();
    }
    
    public File getImage(){                                                     //see what image is in OCR object
        return image;
    }
    
    public boolean run(){                                                       //run the ocr process on the image
        try{
            result = tess.doOCR(image);
            return true;
        }
        catch(TesseractException e){
            e.getMessage();
            return false;
        }
    }
    
    public String getResult(){                                                  //get the results of ocr process
        return result;
    }
    
}
