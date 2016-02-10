import java.awt.event.KeyEvent;
import java.util.ArrayList;


public interface Moves 
{
    public ArrayList<Missile> getMissiles();
    public void fire();
    public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
}
