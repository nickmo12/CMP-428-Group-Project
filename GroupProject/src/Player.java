/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Graphics;

public class Player extends Sprite {
    //String[] poses = new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
    //int[] poseCount = new int[]{9, 12, 9, 7, 3, 4, 19, 9};
    	
	// Default Hero Constructor
    public Player(int x, int y, int w, int h) {
        super(x, y, w, h, 
                "hero01", 
                new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
                new int[]{9, 4, 9, 9, 7, 7, 3, 12, 12, 9, 19}, "png", "-", 3, "player");
    }

    // Foxy Constructor
    public Player(int x, int y, int w, int h, String name, String folder) {
        super(x, y, w, h, 
        		name, 
                new String[]{"jump", "fall", "run", "run", "cartoon super fast run", "cartoon super fast run", "crouch", "idle", "idle", "Death"}, 
                new int[]{21, 7, 14, 14, 7, 7, 14, 14, 14, 22, 19}, "png", "-", 2, folder);
    }
    
    @Override
	public void handlePhysics() {		
		if(pushGroundTimer < jumpForce && animation_state == UP_JUMP) {
			pushGroundTimer++;
		}
		else if (pushGroundTimer < jumpForce && pushGroundTimer != 0 && animation_state != UP_JUMP &&  jumpTimer < jumpMax){
			// Player moved or canceled jump
			pushGroundTimer = 0;
			moving = false;
            animation[UP_JUMP].reset();
		}
		else if(pushGroundTimer >= jumpForce && jumpTimer < jumpMax) {
			isGrounded = false;
			
			if(vy == 0) {
				vy -= jumpHeight;         // moving up on the screen corresponds to y going down
			}
			
			move();
			
			jumping = true;
			
			jumpTimer++;
		}
		else if (pushGroundTimer >= jumpForce && jumpTimer >= jumpMax && !isGrounded) {
			fall(10);
			Camera.moveDown(10);
		}
		else {
			// Player should be falling
			if(jumping && jumpTimer >= jumpMax) {
				jumping = false;
				jumpTimer = 0;
				pushGroundTimer = 0;
	            animation[UP_JUMP].reset();
				moving = false;
			}
	        idle();
		}
	}
}
