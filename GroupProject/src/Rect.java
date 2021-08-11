

import java.awt.*;

public class Rect
{
	public double px;
	public double py;
	
	double vx = 0;
	double vy = 0;
	
	double ax = 0;
	double ay = 0;

	int w;
	int h;
	
	Color c;
	
	public Rect(int x, int y, int w, int h, Color c)
	{
		this.px = x;
		this.py = y;
		
		this.w = w;
		this.h = h;
		
		this.c = c;
	}
	
	public void draw(Graphics pen)
	{
		pen.setColor(c);
		
		pen.drawRect((int)px, (int)py, w, h);
	}
	
	public void draw(Graphics pen, boolean isUsingCamera) {
        if(!isUsingCamera)
        {
        	draw(pen);
        	return;
        }

		pen.setColor(c);
		
		pen.drawRect((int)px, (int)py, w, h);
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	
	public void setVelocity(double vx, double vy)
	{
		this.vx = vx;
		this.vy = vy;
	}
	
	public void setAcceleration(double ax, double ay)
	{
		this.ax = ax;
		this.ay = ay;
	}
	
	public void move()
	{
		System.out.println("MOVING");
		px += vx;
		py += vy;
		
		vx += ax;
		vy += ay;
	}
	
	public void moveBy(int dx, int dy)
	{
		px += dx;
		py += dy;
	}
	
	public void moveUp(int dy)
	{
		py -= dy;
	}
	
	public void moveDown(int dy)
	{
		py += dy;
	}
	
	public void moveLeft(int dx)
	{
		px -= dx;
	}
	
	public void moveRight(int dx)
	{
		px += dx;
	}
	
	//-------------------------------------------------------------------------
	// Is point (mx, my) contained within this Axis Aligned Rect (x, y, w, h)? 
	//-------------------------------------------------------------------------
	
	public boolean contains(int mx, int my)
	{
		return (mx >= px) && (mx <= px+w) && (my >= py) && (my <= py+h);
	}
	

	
	public boolean overlaps(Rect r)
	{
		return (r.px + r.w >=   px)  &&
			   (  px +   w >= r.px)  &&
			   (  py +   h >= r.py)  &&
			   (r.py + r.h >=   py);
	}
	
	public boolean bottom_overlaps(Rect r)
	{
		return (r.px + r.w >=   px)  &&
			   (  px +   w >= r.px)  &&
			   (  py +   h >= r.py)  &&
			   (r.py + r.h >=   py);
	}
	

}
