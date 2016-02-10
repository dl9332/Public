import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.util.Scanner;
import javax.swing.JFrame;

/*----------------------------------------------------
 * Things To Do
 * 
 * - Add Player Craft Lives and handle being shot
 * - Pull dead skins from sprite sheet and add to animate deaths
 * - Create different difficulties of AI depending on skin
 * - Add level selection and level advancement
 * - Add power ups
 * 		+ slow down time
 * 		+ shoot from all directions
 * 		+ ray gun
 * 		+ shield
 * 		+ mines/c4 maybe
 * 		+ clone
 * - Add sound
 * - Improve graphic animations
 * - Error Check
 * 
 *///-------------------------------------------------

public class Application extends JFrame 
{
    // global game constants1
	private static final long serialVersionUID = 1L;
	public static int BOARD_HEIGHT;
	public static int BOARD_WIDTH;
	public static int NUMBER_OF_ENEMIES;
	public static int PLAYER_SPEED = 1;
	public static Point SPAWN_LOWER_RIGHT;
	public static int GAP;
	public static int BIT_RES;
	public static int NUMBER_OF_LIVES;

	public Application() 
	{
		//get input from user to determine number of players
		Scanner sc = new Scanner(System.in);
		System.out.print("Number of Enemies = ");
		NUMBER_OF_ENEMIES = sc.nextInt();
		sc.close();		

		BIT_RES = 16; // bit resolution, each image is 16 bits
		
		BOARD_HEIGHT = NUMBER_OF_ENEMIES * 32 + BIT_RES;
		BOARD_WIDTH = NUMBER_OF_ENEMIES * 32 + BIT_RES;
		
		int x = BOARD_WIDTH - BIT_RES ;
		int y = BOARD_HEIGHT - BIT_RES;
		
		SPAWN_LOWER_RIGHT = new Point(x,y);
        initUI();
    }

	// initializes UI component
    private void initUI() 
    {        
		setResizable(false);
        setTitle("Alien Force");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setPreferredSize(new Dimension(BOARD_WIDTH+100, BOARD_HEIGHT));
        pack();
        System.out.println(this.getSize());
        getContentPane().add(new Board());
    }    
    
    public static void main(String[] args) 
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() 
            {
            	// starts the runnable app
                Application application = new Application();
                application.setVisible(true);
            }
        });
    }
}