/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;

/**
 *
 * @author mendo
 */
public class Player extends Sprite {
    //String[] poses = new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
    //int[] poseCount = new int[]{9, 12, 9, 7, 3, 4, 19, 9};
    	
    public Player(int x, int y, int w, int h) {
        super(x, y, w, h, 
                "hero01", 
                new String[]{"Jump", "Slide", "Walk", "Walk", "Run", "Run", "Sit-down00", "Idle", "Idle", "Death", "Sneaking"}, 
                new int[]{9, 4, 9, 9, 7, 7, 3, 12, 12, 9, 19}, "png", "-", 3, "player");
    }
    	
}
