import java.security.SecureRandom;


public class EW_Sensor implements Runnable 
{
	// sensor state
	private boolean on;
	private boolean run;
	private boolean random;
	private int sleepyTime;
	private static final SecureRandom generator = new SecureRandom();
	
	public EW_Sensor(int time) 
	{
		// EW sensor starts on because NS starts green
		on = true;
		run = true;
		if( time == 0)
		{
			sleepyTime = 0;
			random = true;
		}
		else
		{
			sleepyTime = time * 1000;
			random = false;
		}
	}

	@Override
	public void run() 
	{
		// infinite loop
		while(run)
		{
			// always checks sensor unless EW currently green
			if(on)
			{
				
				if(random) sleepyTime = generator.nextInt(20000)+20000;

				
				// wait for 20 seconds to alert NW to switch
				try 
				{
					Thread.sleep(sleepyTime);
				}
				catch (InterruptedException e) 
				{
					Thread.currentThread().interrupt();
				}
				
				System.out.println("\nSensed traffic after " + Integer.toString(sleepyTime / 1000) + " seconds" );
				
				// alert traffic controller to switch
				System.out.println("Sensor Activated");
				signalController();
			}
			else
			{
				// cannot have infinite loop that executes nothing, sleep for a nanosecond
				try 
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e) 
				{
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	public int getSleepyTime()
	{
		return sleepyTime / 1000;
	}
	// stop loop
	public void stop() 
	{
		Thread.currentThread().interrupt();
		run = false; 
	}
	
	// return status if EW_sensor is on or not
	public boolean checkSignal(){ return on; }
	
	// turn on EW_Sensor
	public void on(){ on = true; }
	
	// turn off EW_Sensor, triggering traffic controller to switch EW to green
	private void signalController(){ on = false; }

}
