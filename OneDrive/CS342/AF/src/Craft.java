import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;

public class Craft extends Sprite
{
    protected ArrayList<Missile> missiles;
    protected int speed;
    protected Direction quedDirection;
    public boolean dead;
    private Timer flashTimer;
    int random;

    public Craft(ArrayList<BufferedImage> skins, Board b, int x, int y, int speed, int flashReference) 
    {
    	super(skins,b,x,y);
    	for(int i = flashReference; i<=flashReference+1; ++i)
    	{
    		for(int j=5; j<9; ++j)
    		{
    			skins.add(b.getSprite(j, i, 16));
    		}
    	}
    	missiles = new ArrayList<Missile>();
    	this.speed = speed;
    	quedDirection = Direction.NONE;
    	dead = false;
    	random = 1;
    }
    
    public boolean dead()
    {
    	return dead;
    }
    
    public void died()
    {
    	dead = true;
    }

    public void move() 
    {
        x += dx;
        y += dy;     
    }
    
    public void flash()
    {
    	if(random == 1)
    		random = 0;
    	else
    		random = 1;
    	
    	switch(currentDirection)
    	{
    		case RIGHT:
    			if(random == 1)
    				skin = skins.get(5);
    			else
    				skin = skins.get(9);
   			break;
    		case LEFT:
    			if(random == 1)
    				skin = skins.get(4);
    			else
    				skin = skins.get(8);
    		break;
    		case UP:
    			if(random == 1)
    				skin = skins.get(6);
    			else
    				skin = skins.get(10);
    		break;
    		case DOWN:
    			if(random == 1)
    				skin = skins.get(7);
    			else
    				skin = skins.get(11);
    			break;
    		case NONE:
    		break;
    	}
    	
    }
    
    
    public void freeze()
    {
		dx = 0; dy = 0; 
		currentDirection = Direction.LEFT; 
		skin = skins.get(0);
    }
    
    public void checkQue()
    {
    	if(quedDirection == Direction.NONE) return;
    	
		if(setDirection(quedDirection)) quedDirection = Direction.NONE;
		
    }
    
    public boolean setDirection(Direction direction)
    {
    	if(dead)
		{
			flash(); 
			return true;
		}
    	
    	int xC = x % 32;
    	int yC = y % 32;
    	
    	switch(direction)
    	{
    		case RIGHT: 
    	    	if(yC == 0)
    	    	{
    				dx = speed; dy = 0;
    				currentDirection = Direction.RIGHT; 
    				skin = skins.get(1);
    	    	} else
    	    	{
    	    		quedDirection = Direction.RIGHT;
    	    		return false; 
    	    	}
    		break;
    		
    		case LEFT:
    	    	if(yC == 0)
	    	    {    			
	    			dx = -speed; dy = 0; 
	    			currentDirection = Direction.LEFT; 
	    			skin = skins.get(0);
	    	    }
    	    	else 
	    		{
	    			quedDirection = Direction.LEFT;
	    			return false;
	    		}
    		break;
    		
    		case UP:
    	    	if(xC == 0)
    	    	{
    				dy = -speed; dx = 0;
    				currentDirection = Direction.UP; 
    				skin = skins.get(2);
    	    	}
    	    	else
	    		{
	    			quedDirection = Direction.UP;
	    			return false;
	    		}
    		break;
    		
    		case DOWN:
    	    	if(xC == 0)
    	    	{
    				dy = speed; dx =0; 
    				currentDirection = Direction.DOWN;
    				skin = skins.get(3);
    	    	}
    	    	else 
	    		{
	    			quedDirection = Direction.DOWN;
	    			return false;
	    		}
    		break;
    		
    		case NONE: quedDirection = Direction.NONE; break;
    	}
    	return true;
    }
    
    
    
    public void bounce()
    {
    	switch(currentDirection)
    	{
    		case RIGHT: 
    			while(this.x >= Application.BOARD_WIDTH - Application.BIT_RES) this.x -= 3;
    			setDirection(Direction.LEFT); 
    		break;
    		
    		case LEFT: 
    			while(this.x < 0) ++this.x; 
    			setDirection(Direction.RIGHT);
    		break;
    		
    		case UP:
    			while(this.y >= Application.BOARD_HEIGHT - Application.BIT_RES) this.y -= 2;
    			setDirection(Direction.DOWN);
    		break;
    		
    		case DOWN:
    			while(this.y < 0) ++this.y; 
    			setDirection(Direction.UP);
    		break;
    		
    		case NONE:  break;
    	}
    }
    
    public void removeMissile(int num)
    {
    	missiles.remove(num);
    }
    
    public void fire(int choice){ missiles.add(new Missile(x, y,board.getSprite(choice, 5),currentDirection,board)); }
    
    public ArrayList<Missile> getMissiles(){ return missiles; }
    
}