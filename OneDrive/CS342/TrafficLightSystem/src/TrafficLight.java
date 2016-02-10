import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class TrafficLight extends Rectangle
{
	private static final long serialVersionUID = 1L;
	
	// dark colors used to represent off state
	private final Color darkRed = new Color(50,0,0);
	private final Color darkYellow = new Color(75,75,0);
	private final Color darkGreen = new Color(0,50,0);	
	
	// 3 lights of a traffic light
	Light greenLight;
	Light yellowLight;
	Light redLight;

	public TrafficLight(int x, int y) 
	{
		// create frame of traffic light
		this.x = x;
		this.y = y;
		this.width = 100;
		this.height = 280;
		
		// instantiate lights and their positions relative to rectangular frame 
		redLight = new Light(x+10,y+10,80,Color.red);
		yellowLight = new Light(x+10,y+100,80,Color.yellow);
		greenLight =  new Light(x+10,y+190,80,Color.green);
	}
	
	
	// draw current state of traffic light
	public void draw(Graphics2D g2d)
	{
		g2d.setPaint(Color.black);
		g2d.draw(this);
		g2d.fill(this);
		
		// Draw Red Light
		g2d.setColor(redLight.color);
		g2d.draw(redLight.circle);
		g2d.fill(redLight.circle);

		// Draw Yellow Light
		g2d.setColor(yellowLight.color);
		g2d.draw(yellowLight.circle);
		g2d.fill(yellowLight.circle);
		
		// Draw Green Light
		g2d.setColor(greenLight.color);
		g2d.draw(greenLight.circle);
		g2d.fill(greenLight.circle);
	}
	
	// Red Light Only
	public void changeToRed()
	{
		greenLight.color = darkGreen;
		yellowLight.color = darkYellow;
		redLight.color = Color.red;
	}
	
	// Green Light Only
	public void changeToGreen()
	{
		greenLight.color = Color.green;
		yellowLight.color = darkYellow;
		redLight.color = darkRed;
	}
	
	// Yellow Light Only
	public void changeToYellow() 
	{
		greenLight.color = darkGreen;
		yellowLight.color = Color.yellow;
		redLight.color = darkRed;
	}
		
}
