import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

/**
 *
 * @author Martin
 */
public class BufferedImageLoader 
{
    private HashMap<String, BufferedImage> imageBuffer;

    public BufferedImageLoader() 
    {
        imageBuffer = new HashMap<String, BufferedImage>();
    }

    /**
     * Returns image from path. It uses buffer.
     * @param path
     * @return loaded image
     * @throws IOException When image could not be loaded from path.
     */
    public BufferedImage getImage(String path) throws IOException 
    {
        // if hash map contains key, return value
        if (imageBuffer.containsKey(path)) 
        {
            System.out.println("hit");
            return imageBuffer.get(path);
        } 
        else 
        { // else load image and return value
            BufferedImage image = ImageIO.read(getClass().getResource(path));
            System.out.println("miss");
            imageBuffer.put(path, image);
            return image;
        }
    }
}
