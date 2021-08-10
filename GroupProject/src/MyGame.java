//-----------------------------------------------------------------------------
// The GamePanel class implements the game loop and asynchronous input system
//-----------------------------------------------------------------------------
// At the moment this is also where details for specific games are coded 
//-----------------------------------------------------------------------------




import java.awt.*;
import java.awt.event.*;

//-----------------------------------------------------------------------------

public class MyGame extends GamePanel
{
    // CENTER POSITIONS. Change the big numbers whenever you change the window size.
    int cx = 1900 / 2;
    int cy = 1070 / 2;
        
	Player player = new Player(cx - (150 / 2), cy - (250 / 2), 100, 250);

        
        // backgrounds usually have a position of 0,0
        ImageLayer bg_mountain = new ImageLayer(0, 0, 3, "Mountains.png");
	
	//Bullet bullet  = new Bullet(-1000, 0, 0);
		
    //-------------------------------------------------------------------------

    @Override
    public void initialize()
    {
        //image.getGraphics().setvi;
        Camera.intialize((int)player.px, (int)player.py);
    }
    
    //-------------------------------------------------------------------------
    //   The boolean variables reflecting the keyboard state are queried and
    //   user controlled entities are updated accordingly
    //-------------------------------------------------------------------------
   
  
    public void respond_To_User_Input()
    {	
     	//if(pressing[UP])  tank.moveForward(5);
    	//if(pressing[DN])  tank.moveBackward(3);
    	//if(pressing[LT])  player.moveLeft(3);
    	//if(pressing[RT])  player.moveRight(3);
        player.idle();
    	if(pressing[LT]) {
            player.moveLeft(3);
            Camera.moveLeft(3);
        }
    	if(pressing[RT])  {
            player.moveRight(3);
            Camera.moveRight(3);
        }
    	
    	//if(pressing[SPACE])  player.shoot(bullet);
    	

    	
    }
    
	//-------------------------------------------------------------------------

     
    public void move_Computer_Controlled_Entities()
    { 
    	//bullet.move();
    }

    //-------------------------------------------------------------------------

    public void resolve_Collisions()
    {
    	
    }
    
    //-------------------------------------------------------------------------
    // paint the screen when the O.S. calls according to the code below
    //-------------------------------------------------------------------------
    
	public void paint(Graphics pen)
	{
		pen.clearRect(0, 0,2560, 1536);

                bg_mountain.draw(pen);
                
		player.draw(pen);
		
		
		//bullet.draw(pen);
				
		/* Rect Editor
		for(int i = 0; i <= count; i++)
		
			rect[i].draw(pen);
		
    	pen.drawString("(" + nx +", " + ny + ")", nx, ny);
		//*/
	}
	
	//-------------------------------------------------------------------------
	// When the left mouse button is pressed 
	//    Get the coordinates (mx, my) where the mouse was when pressed
	//    Check if (mx, my) is inside any of the Soldiers or the BattleLord
	///   If so make put that entity under the user's control by assigning
	//    it to my_sprite

	//-------------------------------------------------------------------------

	//*  Rectangle Editor
	int mx = 0;
	int my = 0;
	
	int nx = 0;
	int ny = 0;
	
	/*
	Rect[] rect = new Rect[100];
	int count = -1;

	//*/
	
	
    public void mousePressed(MouseEvent e)
    {
    	// Get the mouse location
    	mx = e.getX();
    	my = e.getY();
    	
    /*  Rectangle Editor
    	count++;
    	
    	rect[count] = new Rect(mx, my, 0, 0, Color.BLACK);
    */
    }
    
	//-------------------------------------------------------------------------

    public void mouseDragged(MouseEvent e)
    {
    	// Get the mouse location
    	nx = e.getX();
    	ny = e.getY();
    	
    	
    /*  Rectangle Editor
    	int dx = nx - mx;
    	int dy = ny - my;
    	
    	rect[count].setSize(dx, dy);
    //*/	
    	
    }
    
	//-------------------------------------------------------------------------

    public void mouseMoved(MouseEvent e)
    {
    	// Get the mouse location
    	int mx = e.getX();
    	int my = e.getY();
    	
    	
    	//player.aimAt(mx, my, 3);
    }
    
	//-------------------------------------------------------------------------
    // Not used yet
	//-------------------------------------------------------------------------
    
    public void mouseReleased(MouseEvent e)
    {
    	mousePressed = false;
    }
    
	
}