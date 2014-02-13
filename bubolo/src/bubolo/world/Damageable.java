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
	 * Set the HP value f or this Damageable to the specified value.
	 * @param i is the value to set this Damageable's HP to.
	 * @return a reference to this Damageable.
	 * 
	 * */
	public Damageable setHP(int i);

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
	 * @param i is the amount to modify this Damageable's health by. Positive values increase HP, negative values decrease.
	 * @return a reference to this Damageable.
	 */
	public Damageable modifyHP(int i);

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
