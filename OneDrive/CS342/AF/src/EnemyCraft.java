import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class EnemyCraft extends Craft 
{
	private Timer timer;
	boolean firable;
	
	public EnemyCraft(ArrayList<BufferedImage> skins, Board b, int x, int y, int speed) 
	{
		super(skins, b, x, y, speed,1);
		
    	setDirection(Direction.DOWN);
		skin = skins.get(3);
    	firable = false;
		ActionListener handle = new ActionListener() 
		{
	      	public void actionPerformed(ActionEvent evt)
	      	{	      
	      		firable = true;
	      	}
	  	};
		timer = new Timer(6000,handle);
		timer.start();
		
	}
	
	private void killOpponent(PlayerCraft pc)
	{
		if (pc.getX() == this.getX()) 
		{
			if (pc.getY() > this.getY())
				setDirection(Direction.DOWN);
			else
				setDirection(Direction.UP);
			if(firable)
			{
				fire(2);
				firable = false;
			}
		} 
		else if (pc.getY() == this.getY()) 
		{
			if (pc.getX() > this.getX())
				setDirection(Direction.RIGHT);
			else
				setDirection(Direction.LEFT);
			if(firable)
			{
				fire(2);
				firable = false;
			}
		}
	}
	
	public void scatter(int randoLeftover)
	{
		switch(randoLeftover % 4)
		{
			case 0: setDirection(Direction.LEFT); break;
			case 1: setDirection(Direction.RIGHT); break;
			case 2: setDirection(Direction.DOWN); break;
			case 3: setDirection(Direction.UP); break;
		}
		
	}
	
	public void move(PlayerCraft pc)
	{
    	if(dead)
    	{
    		flash();
    		
    		return;
		}
    	
		x += dx;
		y += dy;
		
		int random = (int)(Math.random()*1024);
		
		if(random < 10) scatter(random);
		else if( random < 600) killOpponent(pc);
	}

	
    @Override
    public void boundsCondition(Objects objects)
    {
    	if(objects == Objects.BOARD || objects == Objects.BLOCK)
    	{
    		bounce();
    	}

    }
    
}
