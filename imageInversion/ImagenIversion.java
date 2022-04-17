
/**
 * Write a description of ImageInversion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class ImageInversion {
    
    public ImageResource makeInvert(ImageResource inImage) {
        int range = 255;
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()) {
           Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
           int invertR = range - inPixel.getRed();
           int invertB = range - inPixel.getBlue();
           int invertG = range - inPixel.getGreen();
          
           pixel.setRed(invertR);
           pixel.setBlue(invertB);
           pixel.setGreen(invertG);
        }
        return  outImage;
    }
    
    public void selectConvertAndSave() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource gray = makeInvert(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            gray.setFileName(newName);
            gray.draw();
            gray.save();
            
            
        }
    }
    
    

}
