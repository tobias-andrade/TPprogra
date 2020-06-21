package util;

import java.util.Random;

public class Util
{
	private static Random r=new Random();
	
	public static void espera(int maximo)
	{
		try
		{
			Thread.sleep(r.nextInt(maximo));
		} catch (InterruptedException e)
		{
			
			e.printStackTrace();
		}
	}
	
	public static void esperaFija(int cant)
	{
		try {
			Thread.sleep(cant);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
}
