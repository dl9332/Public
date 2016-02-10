import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class PlayerCraft extends Craft 
{

	public PlayerCraft(ArrayList<BufferedImage> skins, Board b, int x, int y, int speed) 
	{
    	super(skins,b,x,y,speed,3);
    	skin = skins.get(0);
	}
	
    public void move() 
    {
    	//System.out.println(x+", "+y);
        x += dx;
        y += dy;
    }
        
    @Override
    public void boundsCondition(Objects objects)
    {
    	if(objects == Objects.BOARD)
    	{
    		bounce();
    	}
    }
    
    public void keyPressed(KeyEvent e) 
    {
        int key = e.getKeyCode();

		
        this.board.unFreeze();
		
    	switch(key)
    	{
    		case KeyEvent.VK_SPACE:	fire(3);	 break;
	    	case KeyEvent.VK_LEFT:  setDirection(Direction.LEFT);  break;
	    	case KeyEvent.VK_RIGHT: setDirection(Direction.RIGHT); break;
	    	case KeyEvent.VK_UP: 	setDirection(Direction.UP);    break;
	    	case KeyEvent.VK_DOWN:	setDirection(Direction.DOWN);  break;
	    	case KeyEvent.VK_SHIFT: speed++;    				   break;
	    	case KeyEvent.VK_A: 	setDirection(Direction.LEFT);  break;
	    	case KeyEvent.VK_D: 	setDirection(Direction.RIGHT); break;
	    	case KeyEvent.VK_W:		setDirection(Direction.UP);    break;
	    	case KeyEvent.VK_S:		setDirection(Direction.DOWN);  break;
	    	default: break;
    	}
        
    }

    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

    	switch(key)
    	{
	    	case KeyEvent.VK_LEFT:  break;
	    	case KeyEvent.VK_RIGHT: break;
	    	case KeyEvent.VK_UP:  break;
	    	case KeyEvent.VK_DOWN:  break;
	    	case KeyEvent.VK_SPACE: break;
	    	case KeyEvent.VK_SHIFT: break;
	    	default: break;
    	}
        
    }

}
