import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


public class TLS_Controller implements Runnable 
{
	// TrafficLights to be controlled by this controller
	private TrafficLight ns;
	private TrafficLight ew;
	
	// instance of EW_Sensor
	private EW_Sensor sensor;
	
	// instance of Display of Lights
	private Display d;
	
	// 30 second timer to change changeable to true
	private Timer timer;
	private boolean changeable;
	
	private int sequenceIterations;
	private int sleepyTimeTotal;
	
	// get instances of Traffic Lights
	public TrafficLight  getEW() { return ew; }
	public TrafficLight getNS() { return ns; }
	
	public TLS_Controller(EW_Sensor sensor, Display d, int iterations) 
	{
		// number of sequences (NS->EW->NS) that the program will iterate through
		this.sequenceIterations = iterations;
		
		sleepyTimeTotal = 0;
		
		// get references of EW_Sensor and display objects
		this.sensor = sensor;
		this.d = d;
		
		// initialize NS being able to change to EW to false
		changeable = false;
		
		ns = new TrafficLight(50,50); // Left Light, NS Light
		ew = new TrafficLight(280,50); // Right Light, EW Light
		
		// initialize NS to green and EW to red
		ew.changeToRed();
		ns.changeToGreen();
		
	}
	
	@Override
	public void run() 
	{
		// 30 second trigger to allow NS to change states
		ActionListener handle = new ActionListener() 
		{
	      	public void actionPerformed(ActionEvent evt)
	      	{	
	      		// allow NS to change and stop timer
	      		changeable = true;
	      		timer.stop();
	      	}
	  	};
		
	  	// set time of timer to 30 seconds and link it to action handle
		timer = new Timer(30000,handle);
		timer.start();
		
		boolean waiter = true;
		boolean finished = false;
		// number of light sequences to iterate through
		int index = 1;
		while(!finished)
		{
			if(!sensor.checkSignal())
			{
				if(changeable)
				{
					// Change NS to yellow and wait 3 seconds
					ns.changeToYellow();
					d.repaint();
					
					try
					{
						Thread.sleep(3000);
					}
					catch(InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
					
					// Change NS to red and EW to red, repaint to show changes
					ns.changeToRed();
					ew.changeToGreen();
					d.repaint();
					
					
					// EW stays green for 20 seconds
					try
					{
						Thread.sleep(20000);
					}
					catch(InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
					
					// change to yellow for 3 seconds
					ew.changeToYellow();
					d.repaint();
					
					try
					{
						Thread.sleep(3000);
					}
					catch(InterruptedException e)
					{
						Thread.currentThread().interrupt();
					}
					
					// Change lights back
					ew.changeToRed();
					ns.changeToGreen();
					d.repaint();
					
					++index;
					
					if(sensor.getSleepyTime() < 30) sleepyTimeTotal += 30;
					else  sleepyTimeTotal += sensor.getSleepyTime();

					
					if(index > sequenceIterations)
					{
						d.stopTimer();
						sensor.stop();
						finished = true;
					}
										
					// set sensor switch values
					sensor.on();
					changeable = false;
					timer.start();

					waiter = true;
				}
				else if(waiter) 
				{
					System.out.println("\nWaiting");
					waiter = false;
				}
			}
			
			try 
			{
				Thread.sleep(1);
			}
			catch (Exception e) 
			{
				Thread.currentThread().interrupt();
			}

		}
		
		// Sensor total sleep time + (2 Yellow light cycles and 20 second EW Green time * number of iterations)
		int totalTime = sleepyTimeTotal + (26 * sequenceIterations );
		
		System.out.println("\nTime Should be " + totalTime + " seconds");
		
		System.out.println("\nDone!");
	}

}
