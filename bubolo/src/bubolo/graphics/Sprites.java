package bubolo.graphics;

import java.util.ArrayList;
import java.util.List;

import bubolo.util.GameLogicException;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Tank;

/**
 * Contains static methods for adding new sprites.
 * @author BU CS673 - Clone Productions
 */
public class Sprites
{
	private List<Sprite<? extends Entity>> sprites = new ArrayList<Sprite<? extends Entity>>();
	
	private static Sprites instance;
	
	/**
	 * Returns the instance of this singleton.
	 * @return the instance of this singleton.
	 */
	public static Sprites getInstance()
	{
		if (instance == null)
		{
			instance = new Sprites();
		}
		return instance;
	}
	
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
	List<Sprite<? extends Entity>> getSprites()
	{
		return sprites;
	}
	
	/**
	 * Do not call this overload.
	 * @param entity reference to an entity.
	 */
	public void create(Entity entity)
	{
		throw new GameLogicException(
				"Sprites.create(Entity) should not be called. Call one of the strongly-typed overloads." +
				"Entity id: " + entity.getId());
	}
	
	/**
	 * Creates a new sprite, and adds it to the list of drawables.
	 * @param tank reference to a tank entity.
	 */
	public void create(Tank tank)
	{
		sprites.add(new TankSprite(tank));
	}
	
	/**
	 * Creates a new sprite, and adds it to the list of drawables.
	 * @param grass reference to a grass entity.
	 */
//	public void create(Grass grass)
//	{
//		sprites.add(new GrassSprite(grass));
//	}
//	
//	/**
//	 * Creates a new sprite, and adds it to the list of drawables.
//	 * @param road reference to a road entity.
//	 */
//	public void create(Road road)
//	{
//		sprites.add(new RoadSprite(road));
//	}
//	
//	/**
//	 * Creates a new sprite, and adds it to the list of drawables.
//	 * @param tree reference to a tree entity.
//	 */
//	public void create(Tree tree)
//	{
//		sprites.add((new TreeSprite(tree));
//	}
}
