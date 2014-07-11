
package cute.main;

import cute.vision.OCR;
import net.sourceforge.tess4j.TesseractException;

public class Main {

    public static void main(String[] args){
        OCR ocr = new OCR();
        
        ocr.setImage("external\\tesseract\\samples\\eurotext.png");
        ocr.run();
        
        System.out.print(ocr.getResult());
    }
    
}
