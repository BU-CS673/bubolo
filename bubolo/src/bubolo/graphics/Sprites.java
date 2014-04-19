package bubolo.graphics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Crater;
import bubolo.world.entity.concrete.DeepWater;
import bubolo.world.entity.concrete.Engineer;
import bubolo.world.entity.concrete.Grass;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.MineExplosion;
import bubolo.world.entity.concrete.Bullet;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Road;
import bubolo.world.entity.concrete.Rubble;
import bubolo.world.entity.concrete.Spawn;
import bubolo.world.entity.concrete.Swamp;
import bubolo.world.entity.concrete.Tank;
import bubolo.world.entity.concrete.Tree;
import bubolo.world.entity.concrete.Wall;
import bubolo.world.entity.concrete.Water;

/**
 * Contains static methods for adding new sprites.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Sprites
{
	private Map<Class<? extends Entity>, SpriteFactory> spriteFactories;

	private List<Sprite> sprites = new ArrayList<Sprite>();

	private static Sprites instance;

	/**
	 * Returns the instance of this singleton.
	 * 
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
		spriteFactories = setSpriteFactories();
	}

	/**
	 * Returns a reference to the list of sprites. Package-private because this method
	 * should not be accessed outside of the Graphics system.
	 * 
	 * @return the list of all sprites.
	 */
	List<Sprite> getSprites()
	{
		return sprites;
	}

	/**
	 * Creates a new sprite based on the type of entity provided.
	 * 
	 * @param entity
	 *            reference to an entity.
	 * @return reference to the new sprite.
	 */
	public Sprite createSprite(Entity entity)
	{
		if (!spriteFactories.containsKey(entity.getClass()))
		{
			throw new IllegalStateException(
					"createSprite is unable to create a sprite from entity type "
							+ entity.getClass().getName());
		}

		Sprite sprite = spriteFactories.get(entity.getClass()).create(entity);
		sprites.add(sprite);
		return sprite;
	}
	
	/**
	 * Adds a sprite that is not attached to an entity.
	 * @param sprite the sprite to add.
	 */
	void addSprite(Sprite sprite)
	{
		sprites.add(sprite);
	}

	/**
	 * Removes the specified sprite.
	 * 
	 * @param sprite
	 *            the sprite to remove.
	 */
	public void removeSprite(Sprite sprite)
	{
		for (int i = 0; i < sprites.size(); ++i)
		{
			if (sprites.get(i) == sprite)
			{
				sprites.remove(i);
				break;
			}
		}
	}

	/**
	 * Wrapper for sprite creation functions.
	 * 
	 * @author BU CS673 - Clone Productions
	 */
	private interface SpriteFactory
	{
		/**
		 * Executes the sprite creation function.
		 * 
		 * @param e
		 *            reference to the entity that the sprite represents.
		 * @return reference to the new sprite.
		 */
		Sprite create(Entity e);
	}

	/**
	 * Creates the sprite factory objects, which map concrete classes to sprite creation.
	 * 
	 * @return map of the concrete classes to sprite creator classes.
	 */
	private static Map<Class<? extends Entity>, SpriteFactory> setSpriteFactories()
	{
		Map<Class<? extends Entity>, SpriteFactory> factories = new HashMap<>();

		factories.put(Base.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new BaseSprite((Base) e);
			}
		});

		factories.put(Bullet.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new BulletSprite(e);
			}
		});

		factories.put(Crater.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new CraterSprite((Crater) e);
			}
		});

		factories.put(DeepWater.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new DeepWaterSprite((DeepWater) e);
			}
		});

		factories.put(Engineer.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new EngineerSprite((Engineer) e);
			}
		});

		factories.put(Grass.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new GrassSprite(e);
			}
		});

		factories.put(Mine.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new MineSprite((Mine) e);
			}
		});

		factories.put(MineExplosion.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new MineExplosionSprite((MineExplosion) e);
			}
		});

		factories.put(Pillbox.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new PillboxSprite((Pillbox) e);
			}
		});

		factories.put(Road.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new RoadSprite((Road) e);
			}
		});

		factories.put(Rubble.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new RubbleSprite(e);
			}
		});

		factories.put(Swamp.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new SwampSprite(e);
			}
		});

		factories.put(Tank.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new TankSprite((Tank) e);
			}
		});

		factories.put(Tree.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new TreeSprite(e);
			}
		});

		factories.put(Wall.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new WallSprite((Wall) e);
			}
		});

		factories.put(Water.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new WaterSprite((Water) e);
			}
			
		});

		factories.put(Spawn.class, new SpriteFactory() {
			@Override
			public Sprite create(Entity e)
			{
				return new SpawnSprite(e);
			}
		});
		return factories;
	}
}
