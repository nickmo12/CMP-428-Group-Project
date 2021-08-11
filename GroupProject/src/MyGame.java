//-----------------------------------------------------------------------------
// The GamePanel class implements the game loop and asynchronous input system
//-----------------------------------------------------------------------------
// At the moment this is also where details for specific games are coded 
//-----------------------------------------------------------------------------




import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//-----------------------------------------------------------------------------

public class MyGame extends GamePanel
{
    // CENTER POSITIONS. Change the big numbers whenever you change the window size.
    int cx = 1900 / 2;
    int cy = 1070 / 2;
    // Map size
    int mapWidth = 10, mapHeight = 10;
	int tileWidth = 100, tileHeight = 100;
        
    // Declare Sprites
	Player player;

    // backgrounds usually have a position of 0,0
    ImageLayer bg_mountain;
    Image image =  Toolkit.getDefaultToolkit().getImage("./image/Death-hero01_000.png");
    
    // Declare Tilesets
    Tileset autumn_tiles;
    
    // Declare Level layouts, as Tile[] data structures
    ArrayList<Tile[]> levels;
    
    int[] level1Layout = new int[] {
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    		 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,
    		 0,  0,  0,  0,  0,  0,  0,  0,  0,  0
    		
    };

	//Bullet bullet  = new Bullet(-1000, 0, 0);
		
    //-------------------------------------------------------------------------

    @Override
    public void initialize()
    {
    	player = new Player(0, 550, 100, 250);
    	bg_mountain = new ImageLayer(0, 0, 3, "Mountains.png");
    	autumn_tiles = new Tileset("tiles/", new String[] {
        		"Grass",
        		"GrassLeft",
        		"GrassMid",
        		"GrassRight",
        }, "png");
    	levels = new ArrayList<>();
    	levels.add(new Tile[mapHeight * mapWidth]); // Added a placeholder for title screen
    	levels.add(new Tile[mapHeight * mapWidth]);
    	
        Camera.intialize((int)player.px, (int)player.py);
        
        LoadLevel(1);
    }
    
    public void LoadLevel(int currentLevel) {
    	// initialize level layout
    	if(currentLevel == 1) {
    		Tile[] level = levels.get(currentLevel);
    		
    		for(int y = 0; y < mapHeight; y++) {
    			for (int x = 0; x < mapWidth; x++) {
    				int tileIndex = (y * mapWidth) + x;
    				int tx = x * tileWidth;
    				int ty = y * tileWidth;
    				int layout_cell = level1Layout[tileIndex = (y * mapWidth) + x];
    				
    	    		level[tileIndex] = new Tile(tx, ty, tileWidth, tileHeight, autumn_tiles, layout_cell);
    			}
    		}
    	}
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
    	
    	if(pressing[LT]) {
            player.moveLeft(3);
        }
    	if(pressing[RT])  {
            player.moveRight(3);
        }
    	if(pressing[UP])  {
            player.jump(5);
        }
    	if(pressing[DN])  {
            player.moveDown(3);
        }
    	player.handlePhysics();
    	
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
    	int px = (int)player.px;
    	int py = (int)player.py;
    	
    	Camera.x = px;
    	Camera.y = py;
    	
    	// Lazily check for tile collisions
    	for(int t = 0; t < levels.get(1).length; t++) {
    		Tile tile = levels.get(1)[t];
    		if(player.bottom_overlaps(tile) && tile.index != -1) {
    			 player.setGrounded();
    			 px = ((int)(Math.abs(tile.px - player.px)));
    		}
    	}

    	//Player bounds
    	if(player.px <= 0) {
    		player.px = 0;
    	}
    	if (player.px >= mapWidth * tileWidth) {
    		player.px = (mapWidth * tileWidth);
    	}
    	
    	
    }
    
    //-------------------------------------------------------------------------
    // paint the screen when the O.S. calls according to the code below
    //-------------------------------------------------------------------------
    
	public void paint(Graphics pen)
	{
		pen.clearRect(0, 0,2560, 1536);

		// Draw Background stuff
        bg_mountain.draw(pen);
                
        // Draw Tilesets
        for(int i = 0; i < levels.get(1).length; i++) {
        	// Draw Every tile from level layout
        	levels.get(1)[i].draw(pen, true);
        }
        
        // Draw Sprite Stuff

		// Draw Player
		player.draw(pen);
		
		pen.drawImage(image, 0, 0, null);
		
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