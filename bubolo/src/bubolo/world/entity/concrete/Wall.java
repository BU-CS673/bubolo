package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.util.TileUtil;
import bubolo.world.Adaptable;
import bubolo.world.Damageable;
import bubolo.world.World;
import bubolo.world.entity.StationaryElement;

/**
 * Walls are intended to impede Tank movement, and create Rubble Terrain when destroyed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Wall extends StationaryElement implements Adaptable, Damageable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = -4591161497141031916L;

	private int tilingState = 0;
	
	/**
	 * The health of the tree
	 */
	private int hitPoints;

	/**
	 * The maximum amount of hit points of the tree
	 */
	public static final int MAX_HIT_POINTS = 100;

	/**
	 * Intended to be generic -- this is a list of all of the StationaryEntities classes that should
	 * result in a valid match when checking surrounding tiles to determine adaptive tiling state.
	 */
	private Class<?>[] matchingTypes = new Class[] { Wall.class };

	/**
	 * Construct a new Wall with a random UUID.
	 */
	public Wall()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Wall with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Wall(UUID id)
	{
		super(id);
		setWidth(30);
		setHeight(30);
		updateBounds();
		setSolid(true);
		hitPoints = MAX_HIT_POINTS;
	}

	@Override
	public void updateTilingState(World w)
	{
		if (this.getTile() != null)
		{
			setTilingState(TileUtil.getTilingState(this.getTile(), w, matchingTypes));
		}
		else
		{
			setTilingState(0);
		}
	}

	@Override
	public void update(World w)
	{
		super.update(w);
		updateTilingState(w);
	}

	@Override
	public int getTilingState()
	{
		return tilingState;
	}

	@Override
	public void setTilingState(int newState)
	{
		tilingState = newState;
	}

	/**
	 * Returns the current health of the wall
	 * 
	 * @return current hit point count
	 */
	@Override
	public int getHitPoints() 
	{
		return hitPoints;
	}

	/**
	 * Method that returns the maximum number of hit points the entity can have. 
	 * @return - Max Hit points for the entity
	 */
	@Override
	public int getMaxHitPoints() 
	{
		return MAX_HIT_POINTS;
	}

	/**
	 * Changes the hit point count after taking damage
	 * 
	 * @param damagePoints
	 *            how much damage the wall has taken
	 */
	@Override
	public void takeHit(int damagePoints) 
	{
		Audio.play(Sfx.WALL_HIT);
		hitPoints -= Math.abs(damagePoints);
		
		if(hitPoints <= 0)
		{
			this.getTile().clearElement();
			dispose();
		}	
	}

	/**
	 * Increments the pillbox's health by a given amount
	 * 
	 * @param healPoints - how many points the wall is given
	 */
	@Override
	public void heal(int healPoints) 
	{
		if (hitPoints + Math.abs(healPoints) < MAX_HIT_POINTS)
		{
			hitPoints += Math.abs(healPoints);
		}

		else
		{
			hitPoints = MAX_HIT_POINTS;
		}		
	}
}
