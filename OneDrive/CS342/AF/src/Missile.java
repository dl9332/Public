import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Missile extends Sprite
{	    
		private int missileSpeed;
	    public Missile(int x, int y, BufferedImage skin, Direction dir, Board board) 
	    {
	    	super(skin,board,x,y);
	    	missileSpeed = 2;

	    	switch(dir)
	    	{
	    		case LEFT: dx = -missileSpeed; dy = 0; break;
	    		case RIGHT: dx = missileSpeed; dy = 0; break;
	    		case UP: dx = 0; dy = -missileSpeed; break;
	    		case DOWN: dx = 0; dy = missileSpeed; break;
	    		default: break;
	    	}
	    	
	    }
	    
	    
	    public void setSpeed(int s)
	    {
	    	missileSpeed = s;
	    }
	    
	    
	    public void move() 
	    {
	    	x += dx;
	    	y += dy;
	    }

	}