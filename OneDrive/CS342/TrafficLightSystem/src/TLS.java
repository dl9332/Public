/****************************************************
 * Devin Lown
 * 675103132
 * dlown2@uic.edu
 * 
 * CS342 4/30/2015
 * 
 * Project 5 TrafficLightSystem
 * 
 * see trafficReadMe.txt
 * 
 *///************************************************


import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;



public class TLS extends JFrame
{

	private static final long serialVersionUID = 1L;
	public static boolean clockHit;
	
	public TLS() 
	{
		initUI();	
	}

	public static void main(String[] args) 
	{		
		
        EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
        		TLS tls = new TLS();
        		tls.setVisible(true);
            }
        });
	}
	
	
    private void initUI() 
    {   
		setResizable(false);
        setTitle("Traffic Light System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setPreferredSize(new Dimension(430, 350));
        pack();
        add(new Display());
    }    

}




