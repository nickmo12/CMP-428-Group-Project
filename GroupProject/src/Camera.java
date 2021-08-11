


public  class Camera
{
	static int initialX;
	static int initialY;
	
	static int x;
	static int y;
	
	public static void intialize(int x, int y)
	{
		Camera.x = x;
		Camera.y = y;
		
		initialX = x;
		initialY = y;
	}
	
	public static int dx() {
		return x - initialX;
	}
	public static int dy() {
		return y - initialY;
	}
	
	
	public static void moveRight(int dx)
	{
		x += dx;
	}

	public static void moveLeft(int dx)
	{
		x -= dx;
	}

	public static void moveUp(int dy)
	{
		y -= dy;
	}

	public static void moveDown(int dy)
	{
		y += dy;
	}
}
