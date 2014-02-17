package bubolo.graphics;

import java.util.List;

import bubolo.world.Entity;
import bubolo.world.Tank;

/**
 * Contains static methods for adding new sprites.
 * @author BU CS673 - Clone Productions
 */
public class Sprites
{
	private static List<Sprite<? extends Entity>> sprites;
	
	/**
	 * Private constructor to prevent this class from being instantiated.
	 */
	private Sprites()
	{
	}
	
	/**
	 * Returns a reference to the list of sprites. Package-private because 
	 * this method should not be accessed outside of the Graphics system.
	 * @return
	 */
	static List<Sprite<? extends Entity>> getSprites()
	{
		return sprites;
	}
	
	/**
	 * Do not call this overload.
	 * @param entity reference to an entity.
	 */
	public static void create(Entity entity)
	{
		throw new UnsupportedOperationException(
				"Sprites.create(Entity) should not be called. Call one of the strongly-typed overloads." +
				"Entity id: " + entity.getId());
	}
	
	/**
	 * Creates a new sprite, and adds it to the list of drawables.
	 * @param tank reference to a tank entity.
	 */
	public static void create(Tank tank)
	{
		sprites.add(new TankSprite(tank));
	}
	
	/**
	 * Creates a new sprite, and adds it to the list of drawables.
	 * @param grass reference to a grass entity.
	 */
//	public static void create(Grass grass)
//	{
//		sprites.add(new GrassSprite(grass));
//	}
//	
//	/**
//	 * Creates a new sprite, and adds it to the list of drawables.
//	 * @param road reference to a road entity.
//	 */
//	public static void create(Road road)
//	{
//		sprites.add(new RoadSprite(road));
//	}
//	
//	/**
//	 * Creates a new sprite, and adds it to the list of drawables.
//	 * @param tree reference to a tree entity.
//	 */
//	public static void create(Tree tree)
//	{
//		sprites.add((new TreeSprite(tree));
//	}
}
