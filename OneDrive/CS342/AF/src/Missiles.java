import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.Timer;


public class Missiles 
{
	/*private Timer timer;
	private int missileSpeed;
	private ArrayList<Missile> missiles;
	private BufferedImage skin;
	private Board board;
	
	public Missiles(Board b, int missileSpeed, BufferedImage skin) 
	{
		this.board = b;
		this.skin = skin;
		this.missileSpeed = missileSpeed;
		missiles = new ArrayList<Missile>();
		ActionListener handle = new ActionListener() {
	      	public void actionPerformed(ActionEvent evt)
	      	{
	      		
	      	}
	  	};
		timer = new Timer(1000,handle);
		timer.start();
		
	}
	
	public void increaseSpeed()
	{
		++missileSpeed;
	}
	
	public void add(int x, int y, Direction direction )
	{
		Missile m = new Missile(skin,board,x,y,missileSpeed);

		switch(direction)
		{
    		case LEFT: m.setDx(-missileSpeed); break;
    		case RIGHT: m.setDx(missileSpeed); break;
    		case UP: m.setDy(-missileSpeed); break;
    		case DOWN: m.setDy(missileSpeed); break;
    		default: break;
    	}
		
		missiles.add(m);
	}

	public ArrayList<Missile> missiles()
	{
		return missiles;
	}*/
}
