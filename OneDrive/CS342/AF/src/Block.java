import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Block extends Sprite
{
    public Block(Board b, BufferedImage image, int x, int y) 
    {
    	super(image,b,x,y);
    }
    
}