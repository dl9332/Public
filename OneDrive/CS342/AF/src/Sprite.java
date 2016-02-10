import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public abstract class Sprite 
{
    protected int x,y,dx,dy,height,width;
    protected Direction currentDirection;
    protected Board board;
    protected ArrayList<BufferedImage> skins;
    protected BufferedImage skin;
    protected boolean visible;
    
    // Sprite Constructor
	public Sprite(ArrayList<BufferedImage> skins,Board b, int x, int y) 
	{
    	this.skins = skins;
    	this.height = skins.get(0).getHeight();
    	this.width = skins.get(0).getWidth();
        this.x = x;
        this.y = y;
        this.board = b;
    	visible = true;;
	}
	
	public Sprite(BufferedImage skin, Board b, int x, int y) 
	{
    	this.skin = skin;
    	this.height = skin.getHeight();
    	this.width = skin.getWidth();
        this.x = x;
        this.y = y;
        this.board = b;
    	visible = true;;
	}	
	
	// Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
    public BufferedImage getImage() { return skin; }
    public ArrayList<BufferedImage> getImages() { return skins; }
    public boolean isVisible() { return visible; }
    
    // Setters
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setDx(int dx){ this.dx = dx; } 
    public void setDy(int dy){ this.dy = dy; }
    
    public void boundsCondition(Objects objects)
    {
    	this.visible = false;
    }

}
