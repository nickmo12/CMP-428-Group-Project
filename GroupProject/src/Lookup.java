

public class Lookup
{
	public final static double[] cos = generateCos();
	public final static double[] sin = generateSin();
	
	public final static double PI = Math.PI;
	
	
	public static double[] generateCos()
	{
		double[] cos = new double[360];
		
		for(int i = 0; i < cos.length; i++)
			
			cos[i] = Math.cos(i * PI / 180);

		return cos;
		
	}

	public static double[] generateSin()
	{
		double[] sin = new double[360];
		
		for(int i = 0; i < sin.length; i++)
			
			sin[i] = Math.sin(i * PI / 180);
		
		return sin;
	}

}




