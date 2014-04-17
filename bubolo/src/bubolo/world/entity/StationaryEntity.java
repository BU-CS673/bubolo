package bubolo.world.entity;

import java.util.UUID;

import bubolo.world.Tile;
import bubolo.world.World;

/**
 * Basic class for DrawableEntities that do not move (i.e., those that are fixed a single location
 * on the map grid).
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class StationaryEntity extends Entity
{
	/**
	 * The Tile object to which this StationaryEntity belongs. Used for positioning.
	 */
	private Tile myTile;

	/**
	 * Used when serializing and de-serializing.
	 */
	private static final long serialVersionUID = 7748173162492885868L;

	/**
	 * Construct a new StationaryEntity with a random UUID.
	 */
	public StationaryEntity()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new StationaryEntity with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new StationaryEntity.
	 */
	public StationaryEntity(UUID id)
	{
		super(id);
	}

	/**
	 * Sets the Tile object that this StationaryEntity belongs to.
	 * 
	 * @param t
	 *            is the Tile object into which this StationaryEntity should be stored.
	 * @return a reference to this StationaryEntity.
	 */
	public StationaryEntity setTile(Tile t)
	{
		myTile = t;
		return this;
	}

	/**
	 * Returns the Tile object to which this StationaryEntity belongs.
	 * 
	 * @return a reference to this StationaryEntity's Tile object.
	 */
	public Tile getTile()
	{
		return myTile;
	}

	/**
	 * Get the x position of this StationaryEntity in World coordinates. Uses the grid position of
	 * this entity's Tile object to calculate its world position. If this StationaryEntity does not
	 * belong to a Tile, it returns the Entity default getX() method.
	 */
	@Override
	public float getX()
	{
		if (myTile != null)
		{
			return myTile.getX();
		}
		else
			return super.getX();
	}

	/**
	 * Get the y position of this StationaryEntity in World coordinates. Uses the grid position of
	 * this entity's Tile object to calculate its world position. If this StationaryEntity does not
	 * belong to a Tile, it returns the Entity default getY() method.
	 */
	@Override
	public float getY()
	{
		if (myTile != null)
		{
			return myTile.getY();
		}
		else
			return super.getY();
	}

	@Override
	public void update(World world)
	{
		updateControllers(world);
	}
}
