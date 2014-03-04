package test;

public class Main
{
	public static void main(String[] args)
	{
		Sprite s1 = new GrassSprite();
		Sprite s2 = new TankSprite();
		
		System.out.println(s1.getClass());
		System.out.println(s2.getClass());
		
		print(s1);
		print(s2);
	}
	
	public static void print(Sprite sprite)
	{
		System.out.println(sprite.getClass());
	}
}
