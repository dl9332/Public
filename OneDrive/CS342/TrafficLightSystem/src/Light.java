import java.awt.Color;
import java.awt.geom.Ellipse2D;


public class Light 
{
	Color color;
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	Ellipse2D circle;
	
	public Light(int x, int y, int radius, Color c) 
	{
		circle = new Ellipse2D.Double(x,y,radius,radius);
		color = c;
	}

}
