import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

/*--------------------------------------------------------------
 *  There is only one instance of the Board (Singleton)
 * The board has all the ships and border blocks attached to it 
 * and is responsible for maintaining all updates by repainting
 * the canvas and checking boundaries of all ships
 *///-----------------------------------------------------------

public class Board extends JPanel implements ActionListener 
{
	private static final long serialVersionUID = 1L;
	private Timer timer;
    private PlayerCraft goodCraft;
    private SpriteSheet spriteSheet;
    public  ArrayList<Block> blocks;
    private ArrayList<EnemyCraft> badCrafts;
    private Rectangle boardRec;
    private boolean playerBlocked;
    private boolean freezeEnemies;
    
    public Board() 
    {
    	// Hit box for board boundaries
    	boardRec = new Rectangle(0,0,Application.BOARD_WIDTH,Application.BOARD_HEIGHT);
    	
    	// Add Keyboard Listener for player moves 
        addKeyListener(new TAdapter());
        
        // Set UI Board vars
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setSize(Application.BOARD_WIDTH,Application.BOARD_HEIGHT);
        
        playerBlocked = false;
        freezeEnemies = true;
        
    	spriteSheet = new SpriteSheet("SpriteSheet.png"); // set spritesheet
    	
    	// get all skins for player craft from the spritesheet
    	ArrayList<BufferedImage> goodSkin = new ArrayList<BufferedImage>();
    	
    	goodSkin.add(getSprite(1,4));
    	goodSkin.add(getSprite(4,4));
    	goodSkin.add(getSprite(2,4));
    	goodSkin.add(getSprite(3,4));
    	
    	// set all skins for the enemy crafts from the sprite sheet
    	ArrayList<ArrayList<BufferedImage>> badSkins = new ArrayList<ArrayList<BufferedImage>>();
    	for(int i= 3; i>0; --i)
    	{
    		ArrayList<BufferedImage> temp =  new ArrayList<BufferedImage>();
	    	for(int j=1; j<5; ++j) temp.add(getSprite(j,i));
	    	badSkins.add(temp);
    	}
    	
    	// initialize enemy crafts list
    	badCrafts = new ArrayList<EnemyCraft>();
        
    	// initialize playercraft
        goodCraft = new PlayerCraft(goodSkin,this,Application.SPAWN_LOWER_RIGHT.x,
        		 								  Application.SPAWN_LOWER_RIGHT.y,1);
        // initialize blocks list
        blocks = new ArrayList<Block>();
        
        int blockOffset =  Application.BIT_RES;
        int blockGap = Application.BIT_RES + blockOffset;
                
		for(int i=blockOffset; i<Application.BOARD_HEIGHT; i+=blockGap)
		{
			for(int j=blockOffset; j<Application.BOARD_WIDTH; j+=blockGap)
			{
				blocks.add(new Block(this,getSprite(1,5),i,j));
			}
		}
		
		// create enemies and place them at the top of the board
		for(int i=0; i<Application.BOARD_WIDTH; i+= blockGap)
		{
			int random = (int)(Math.random() * 1024) % 3;
			badCrafts.add(new EnemyCraft(badSkins.get(random),this,i,0,1));
		}
        
		//initialize timer to update board every 20 milliseconds
        timer = new Timer(20,this);
        timer.start();
    }

    /*-----------UPDATE BOARD GRAPHICS-------------------------------------------------------------------------------------------------*/
    public void paint(Graphics g) 
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        g2d.drawImage(goodCraft.getImage(), goodCraft.getX(), goodCraft.getY(), this);
        
        for(Block block: blocks)
        {
    		if(block.isVisible())
    		{
    			g2d.drawImage(block.getImage(), block.getX(), block.getY(), this);
    		}
        }
                
        for (Missile m: goodCraft.getMissiles()) 
        {
        	if(m.isVisible())
        	{
        		g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
        	}
        }
        
        for(EnemyCraft bc: badCrafts)
        {
        	if(bc.isVisible())
        	{
        		g2d.drawImage(bc.getImage(), bc.getX(), bc.getY(), this);
        	}
        	
            for (Missile m: bc.getMissiles()) 
            {
            	if(m.isVisible())
            	{
            		g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
            	}
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    
    /*----------------------------------------------------------------------------------------------------------------*/
    
    public BufferedImage getSprite(int col, int row)
    {
    	return spriteSheet.getImage(col, row, Application.BIT_RES, Application.BIT_RES);	
    }
   
    
    public BufferedImage getSprite(int col, int row,int off)
    {
    	return spriteSheet.getImage(col, row, off, off);	
    }
    
    public boolean freeze() { return freezeEnemies; }
    public void unFreeze() { freezeEnemies = false; }

    /*----------------------------------------------------------------------------------------------------------------*/

    public void actionPerformed(ActionEvent e) 
    {
        ArrayList<Missile> ms = goodCraft.getMissiles();
    	
        for (int i=0; i<ms.size(); ++i ) 
        {   
        	Missile m = ms.get(i);
            if (m.isVisible()) 
                m.move();
            else ms.remove(m);
        }
        
        for (int i=0; i<blocks.size(); ++i)
        {
        	Block b = blocks.get(i);
        	
        	if(!b.isVisible())
        	{
        		blocks.remove(i);
        	}
        }
        
        // iterate through enemy crafts
        for(int i=0; i<badCrafts.size(); ++i)
        {
        	EnemyCraft bc = badCrafts.get(i);
        	ArrayList<Missile> bms = bc.getMissiles();

        	if((!bc.isVisible() || bc.dead) && bc.missiles.size() == 0)
        	{
        		badCrafts.remove(i);
        	}
        	else if(bc.isVisible() && !freezeEnemies) bc.move(goodCraft);
        	
        	//System.out.println(bc.missiles.size());
        	
            for (int j=0; j<bms.size(); ++j ) 
            {   
            	Missile m = bms.get(j);
                if (m.isVisible())
                {
                    m.move();
                }
                
                if(!m.isVisible()) bc.removeMissile(j);
            }
        }
        
        if(!playerBlocked) goodCraft.checkQue(); goodCraft.move();
        checkCollisons();
        repaint();  
    }
    /*-----------Handle-Collisions-of-crafts-and-board-boundaries/obstacles-----------------------------------------------------------------------------------------------------*/
    
    private void checkBoardEdges(Sprite thing)
    {
    	int yCenter = (int)thing.getBounds().getCenterY();
    	int xCenter = (int)thing.getBounds().getCenterX();
    	
    	if ( xCenter >= boardRec.getWidth()  || 
    		 yCenter >= boardRec.getHeight() ||
    		 xCenter < 0  	 				 ||
    		 yCenter < 0)
		{
    		thing.boundsCondition(Objects.BOARD);
		}

    }
    
    private void checkCollisons()
    {
    	playerBlocked = false;
    	Rectangle craftRec = goodCraft.getBounds();
    	
    	checkBoardEdges(goodCraft);
    	
    	
    	for(EnemyCraft badCraft: badCrafts)
    	{
    		Rectangle badRec = badCraft.getBounds();
    		if(craftRec.intersects(badRec))
    		{
    			badCraft.setVisible(false);
                System.out.println("Collision!");
    		}
    		
            ArrayList<Missile> ms = badCraft.getMissiles();

            for (Missile m: ms) 
            {
                Rectangle r1 = m.getBounds();
                
                checkBoardEdges(m);

                for (Block block: blocks) 
                {
                    Rectangle r2 = block.getBounds();

                    if (r1.intersects(r2)) 
                    {
                        m.setVisible(false);
                    }
                }
                
        		if(r1.intersects(goodCraft.getBounds()))
        		{
        			m.setVisible(false);
        			goodCraft.setVisible(false);
                    System.out.println("DEAD ENEMY");
        		}
        	
            }
    		
    		checkBoardEdges(badCraft);
    		
    	}
    	
        for (Block block: blocks) 
        {
            Rectangle r2 = block.getBounds();

            if (craftRec.intersects(r2)) 
            {
            	playerBlocked = true;
    	    	switch(goodCraft.currentDirection)
    	    	{
    	    		case RIGHT: --(goodCraft.x); break;
    	    		case LEFT: ++(goodCraft.x); break;
    	    		case UP: ++(goodCraft.y); break;
    	    		case DOWN: --(goodCraft.y); break;
    	    		case NONE: break;
    	    	}
            }
            
        	for(EnemyCraft badCraft: badCrafts)
        	{
        		if(r2.intersects(badCraft.getBounds()))
        		{
        			badCraft.boundsCondition(Objects.BLOCK);
        		}
        	}
        }
        
        ArrayList<Missile> ms = goodCraft.getMissiles();

        for (Missile m: ms) 
        {
            Rectangle r1 = m.getBounds();
            
            checkBoardEdges(m);

            for (Block block: blocks) 
            {
                Rectangle r2 = block.getBounds();

                if (r1.intersects(r2)) 
                {
                    m.setVisible(false);
                }
            }
            
        	for(EnemyCraft badCraft: badCrafts)
        	{
        		if(r1.intersects(badCraft.getBounds()))
        		{
        			m.setVisible(false);
        			badCraft.flash();
        			badCraft.died();
        			//badCraft.setVisible(false);
                    System.out.println("DEAD ENEMY");

        		}
        	}
        }
        
       
    }
    /*-----------Capture-and-Handle-Keyboard Events-----------------------------------------------------------------------------------------------------*/

    private class TAdapter extends KeyAdapter 
    {
        public void keyReleased(KeyEvent e) 
        {
            goodCraft.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) 
        {
        	goodCraft.keyPressed(e);   
        }   
    }
    /*----------------------------------------------------------------------------------------------------------------*/
    
}// END BOARD
