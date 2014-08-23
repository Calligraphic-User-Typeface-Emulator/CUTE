/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cute.imagemanager;

import static cute.imagemanager.ImageManager.CHARS;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

enum Colors {

    RED, GRAY, BLACK, BLUE
};

/**
 *
 * @author Forest
 */
//deals with changing colors
public class HueRevisor {

    private final Color transparent = new Color(0, 0, 0, 0);

    //remove background of big image
    //assumes picture background is black, user uses different color to write their letters
    // *NOTE* may be super slow, if so, may use threads 
    public ArrayList<BufferedImage> removeBackground(ArrayList<BufferedImage> images) {
        int whiteRGB = Color.WHITE.getRGB(), blackRGB = Color.BLACK.getRGB();
        ArrayList<BufferedImage> alteredImages = new ArrayList<>();

        images.forEach((b) -> {                  //dat lambda expression tho :3
            int height = b.getHeight(), width = b.getWidth();   //apparently lambda expressions can only access FINAL local vars
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (b.getRGB(i, j) == blackRGB || b.getRGB(i, j) == whiteRGB) { //makes everything except for user's writng
                        b.setRGB(i, j, transparent.getRGB());                       //transparent for easier printing

                    }
                }
            }
            alteredImages.add(b);

        });

        return alteredImages;
    }

    public Map<String, ArrayList<BufferedImage>> changeFont(Map<String, ArrayList<BufferedImage>> originalImages, String fontColor) {
        CHARS.forEach(s -> {
            originalImages.get(s).forEach(b -> {
                int height = b.getHeight(), width = b.getWidth();   //apparently lambda expressions can only access FINAL local vars
                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        if (b.getRGB(i, j) != transparent.getRGB()) { //makes everything except for user's writng
                            //b.setRGB(i, j, transparent.getRGB());                       //transparent for easier printing
                            //TODO finish this
                        }
                    }
                }

            });
        });
        return null;
    }

}
