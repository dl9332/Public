import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


public class SpriteSheet 
{
	public String path; // path of sprite sheet image
	public int width;
	public int height;
		
	public int[] pixels;
	
	BufferedImage image = null;

	public SpriteSheet(String path) 
	{
		image = null;
		
		// set image of Sprite object
		try{ image = ImageIO.read(getClass().getResource(path)); }
		catch (IOException e){ e.printStackTrace();}
		
		if (image==null) return;
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
	}

	// get particular image of sprite sheet 
	public BufferedImage getImage(int col, int row, int width, int height) 
	{
		BufferedImage img = image.getSubimage((col*16) - 16, (row*16) - 16, width, height);
		return img;
	}

}
