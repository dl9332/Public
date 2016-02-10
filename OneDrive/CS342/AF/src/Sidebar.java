import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class Sidebar extends JPanel 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle sidebarRec;
	private JLabel level;
	private JLabel score;
	
	public Sidebar() 
	{
		super();
        
    	sidebarRec = new Rectangle(0,0,100,100);
    	
        
        // Set UI Board vars
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        setSize(100,100);

        level = new JLabel("This");
        
        score = new JLabel("This2");


        add(level);
        add(score);   
    }

	public Sidebar(LayoutManager arg0) {
		super(arg0);
    	sidebarRec = new Rectangle(0,0,100,100);
    	
        
        // Set UI Board vars
        setFocusable(true);
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        //setSize(100,100);

        level = new JLabel("Nigger", FlowLayout.LEFT);
        
        score = new JLabel("Nigger", FlowLayout.RIGHT);


        add(level);
        add(score);
        //add(new Button("Hey"), BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}

	public Sidebar(boolean arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public Sidebar(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

}
