import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Display extends JPanel
{
	private static final long serialVersionUID = 1L;
	private EW_Sensor ewSensor;
	private TLS_Controller controller;
	Timer overallTime;
	private double time;

	
	public Display() 
	{

		// Get user input for number of iterations
		Scanner sc = new Scanner(System.in);
		System.out.println("Each Iteration goes from ");
		System.out.println("NS: Green to EW: Green");
		System.out.println("and back to  NS: Green\n");
		System.out.print("Enter Number of Iterations: ");
		
		int iterations = sc.nextInt();	// set iterations
		
		System.out.println("\nRandom East West Sensor Time?");
		System.out.print("Enter \"yes\" or \"no\" : ");
		
		String answer = sc.nextLine();
		answer = sc.nextLine();
		int sensorTime;
		
		if(answer.contentEquals("no"))
		{
			System.out.print("Sensor Time = ");
			sensorTime = sc.nextInt();
		}
		else sensorTime = 0;
		
		sc.close();	// close scanner
		
		// UI initializers for extended JPanel
		setFocusable(true);
	    setBackground(Color.blue);
	    setDoubleBuffered(true);
	    this.setLayout(new FlowLayout(FlowLayout.LEFT,50,10));
	    
	    // create labels
	    JLabel nsLabel = new JLabel("North South");
	    JLabel timeLabel = new JLabel("");
	    JLabel ewLabel = new JLabel("East West");
	    
	    // set font of labels
	    nsLabel.setFont(new Font("Verdana",Font.BOLD,16));
	    timeLabel.setFont(new Font("Verdana",Font.BOLD,16));
	    ewLabel.setFont(new Font("Verdana",Font.BOLD,16));
	    
	    nsLabel.setForeground(Color.black);
	    timeLabel.setForeground(Color.black);
	    ewLabel.setForeground(Color.black);
	    
	    // add labels to JPanel
	    add(nsLabel);
	    add(timeLabel);
	    add(ewLabel);
	    
	    // instantiate sensor and traffic controller
		ewSensor = new EW_Sensor(sensorTime);
		controller = new TLS_Controller(ewSensor,this,iterations);
		
		// create new thread for each process
		Thread ewThread =  new Thread(ewSensor);
		Thread controllerThread = new Thread(controller);
		
		time = 0.0;
		
		ActionListener handle = new ActionListener() 
		{
			// Increment clock every 10th of a second
	      	public void actionPerformed(ActionEvent evt)
	      	{
	      		time += .1;
	      		time = (double)Math.round(time * 10) / 10;
	      		timeLabel.setText(Double.toString(time));
	      	}
	    };
		overallTime = new Timer(99, handle);
		
		// start the threads
		ewThread.start();
		controllerThread.start();
		overallTime.start();
	}
	
	public void stopTimer() { overallTime.stop(); }
	
    public void paint(Graphics g) 
    {
    	// inherit base methods of paint
        super.paint(g);
        
        // cast to better version of graphics
        Graphics2D g2d = (Graphics2D)g;
        
        // draw current traffic light
        controller.getEW().draw(g2d);
        controller.getNS().draw(g2d);
    }
}
