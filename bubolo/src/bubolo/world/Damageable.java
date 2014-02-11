package bubolo.world;

/**
 * Interface for Entities that are able to be affected by other objects through taking
 * damage. Outlines methods relating to getting, checking, and modifying the HP of
 * Entities.
 * 
 * @author BU673 - Clone Industries
 */
public interface Damageable
{
	/**
	 * Get the current HP value for this Damageable.
	 * 
	 * @return an int representing the current HP value.
	 */
	public int getHP();

	/**
	 * Get the max HP value for this Damageable.
	 * 
	 * @return an int representing the maximum HP value.
	 */
	public int getMaxHP();

	/**
	 * Modify this Damageable's HP by the specified amount. Positive values should
	 * increase the current HP, negative values should decrease it.
	 * 
	 * @return this Damageable's current HP, after the change.
	 */
	public int modifyHP();

	/**
	 * Test to see whether this Damageable should be considered 'alive' for the purposes
	 * of entity interactions.
	 * 
	 * @return true if the Damageable should be considered 'alive'.
	 */
	public boolean isAlive();
	
	
	/** 
	 * Destroy this Damageable and clean up any remaining assets.
	 */
	public void destroy();
}
