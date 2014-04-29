package bubolo.world.entity.concrete;

import java.util.UUID;

import bubolo.audio.Audio;
import bubolo.audio.Sfx;
import bubolo.world.Damageable;
import bubolo.world.entity.StationaryElement;

/**
 * Trees are StationaryElements that can spread over time, and hide Tanks that drive over them.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Tree extends StationaryElement implements Damageable
{
	/**
	 * Used in serialization/de-serialization.
	 */
	private static final long serialVersionUID = 4072369464678115753L;
	
	/**
	 * The health of the tree
	 */
	private int hitPoints;

	/**
	 * The maximum amount of hit points of the tree
	 */
	public static final int MAX_HIT_POINTS = 1;

	/**
	 * Construct a new Tree with a random UUID.
	 */
	public Tree()
	{
		this(UUID.randomUUID());
	}

	/**
	 * Construct a new Tree with the specified UUID.
	 * 
	 * @param id
	 *            is the existing UUID to be applied to the new Tree.
	 */
	public Tree(UUID id)
	{
		super(id);
		setWidth(32);
		setHeight(32);
		updateBounds();
		hitPoints = MAX_HIT_POINTS;
	}

	/**
	 * Returns the current health of the tree
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
	 *            how much damage the tree has taken
	 */
	@Override
	public void takeHit(int damagePoints) 
	{
		hitPoints -= Math.abs(damagePoints);
		if(hitPoints <= 0)
		{
			this.getTile().clearElement();
			this.dispose();
		}
	}

	/**
	 * Increments the pillbox's health by a given amount
	 * 
	 * @param healPoints - how many points the tree is given
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
	
	@Override
	protected void onDispose()
	{
		Audio.play(Sfx.TREE_HIT);
	}
}
