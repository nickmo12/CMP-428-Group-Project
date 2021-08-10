//-----------------------------------------------------------------------------
// The Sprite class implements an object that can move around the screen
// while displaying animations appropriate to the circumstances  
//-----------------------------------------------------------------------------



import java.awt.*;
//-----------------------------------------------------------------------------

public class Sprite extends Rect
{
        //new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
	//-------------------------------------------------------------------------
		
	Animation[] animation;  // an array of Animation objects to select from
        
        final int UP    = -1; 
        final int CENTERED  = 0; 
        final int DOWN  = 1; 
        final int LEFT  = -1; 
        final int RIGHT = 1;  
		
        final int UP_JUMP    = 0;    // Convenience constants
        final int DOWN_SLIDE  = 1;    // indexing the array of
        final int LEFT_WALK  = 2;    // Animations according to
        final int RIGHT_WALK = 3;    // the type of motion
        final int RIGHT_RUN = 4;
        final int LEFT_RUN = 5;
        final int DOWN_SIT = 6;
        final int LEFT_IDLE = 7;
        final int RIGHT_IDLE = 8;
        final int DEATH = 9;
        final int SNEAKING = 10;
        

    
	boolean moving  = false; // the Sprite can be moving or still
	boolean jumping = false;
	
	int motion_horizontal = RIGHT;        // index indicating the type of motion
	int motion_vertical = CENTERED;        // index indicating the type of motion
	int animation_state = RIGHT_IDLE;        // index indicating the type of motion
	
	boolean alive = true;
	
	//-------------------------------------------------------------------------
	// Construct the Sprite
	//-------------------------------------------------------------------------
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i] + "_", count, filetype);
		}
	}
	
	//-------------------------------------------------------------------------
	// Construct the Sprite
	//-------------------------------------------------------------------------
	
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
			animation[i] = new Animation(name + "_" + pose[i] + "_", count[i], filetype);
		}
	}
        
        // Explicitly loads the player
	public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype, String delimiter, int leadingZeroes, String folder)
	{
		super(x, y, w, h, Color.RED);
		 
		// Set length of array to match the number of Animations
		animation = new Animation[pose.length];
		 
		// Load the Animations
		for(int i = 0; i < pose.length; i++)
		{
                        System.out.println(pose[i] + ", " + count[i]);
			animation[i] = new Animation(pose[i] + delimiter + name + "_", count[i], filetype, leadingZeroes);
			//animation[i] = new Animation(folder + "/" + pose[i] + delimiter + name + "_", count[i], filetype, leadingZeroes);
		}
	}
	
	
	//-------------------------------------------------------------------------
	
	public void jump(int dy)
	{
		vy -= dy;         // moving up on the screen corresponds to y going down
		
		moving = true;   // Cause paint method to use Animation's current image
		
		jumping = true;
	}
	
	//-------------------------------------------------------------------------
		
	public void moveUp(int dy)
	{
		vy -= dy;         // moving up on the screen corresponds to y going down
		
		moving = true;   // Cause paint method to use Animation's current image
		
		animation_state = UP_JUMP;     // Set Animation array index according to this action
	}
	
	//-------------------------------------------------------------------------

	public void moveDown(int dy)
	{
		vy += dy;
		
		moving = true;

		animation_state = DOWN_SIT;
	}
	
	//-------------------------------------------------------------------------

	public void moveLeft(int dx)
	{
		//if(!jumping)
		{
			vx -= dx;
			
			moving = true;
	
			motion_horizontal = LEFT;;
                        
                        animation_state = LEFT_WALK;
		}
	}
	
	//-------------------------------------------------------------------------

	public void moveRight(int dx)
	{
		//if(!jumping)
		{
			vx += dx;
			
			moving = true;
	
			motion_horizontal = RIGHT;
                        animation_state = RIGHT_WALK;
		}
	}
	
	//-----------------------------------------------------------------------------------

	public void idle()
	{
		//if(!jumping)
                if(!moving && motion_horizontal == LEFT)
		{
                    animation_state = LEFT_IDLE;
		}
                else if(!moving && motion_horizontal == RIGHT){
                    animation_state = RIGHT_IDLE;
                }
	}
	
	//-------------------------------------------------------------------------

	public void dies()
	{
		alive = false;
		
		py = -100000;
	}
	
	//-------------------------------------------------------------------------
	// Draws the Sprite on the screen according to its state:
	//   moving indicated whether to use the Animation's current or still image
	//   motion is an index into the array of Animations to pick the right pose    
	//-------------------------------------------------------------------------

        @Override
	public void draw(Graphics pen)
	{
            super.draw(pen);
	    if(alive)
		{
                    if(motion_horizontal == LEFT)
                        pen.drawImage(animation[animation_state].getCurrentImage(),
                                (int)px + w,
                                (int)py,
                                motion_horizontal * animation[animation_state].getCurrentImage().getWidth(null),
                                animation[animation_state].getCurrentImage().getHeight(null), null);
                    else if (motion_horizontal == RIGHT){
                        pen.drawImage(animation[animation_state].getCurrentImage(),
                                (int)px,
                                (int)py,
                                motion_horizontal * animation[animation_state].getCurrentImage().getWidth(null),
                                animation[animation_state].getCurrentImage().getHeight(null), null);
                    }
			
                        moving = false;
			
			if(!jumping)  vx = 0;
			
			//super.draw(pen);
		}
	}
	
	//-------------------------------------------------------------------------

}
