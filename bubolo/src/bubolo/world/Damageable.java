package bubolo.world;

/**
 * Interface for Entities that are able to be affected by other objects through taking
 * damage. Outlines methods relating to getting, checking, and modifying the HP of
 * Entities.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Damageable
{
	/**
	 * Returns the current health of the tank
	 * 
	 * @return current hit point count
	 */
	public int getHitPoints();
	
	/**
	 * Method that returns the maximum number of hit points the entity can have. 
	 * @return - Max Hit points for the entity
	 */
	public int getMaxHitPoints();
	
	/**
	 * Changes the hit point count after taking damage
	 * 
	 * @param damagePoints
	 *            how much damage the tank has taken
	 */
	public void takeHit(int damagePoints);
	
	/**
	 * Increments the tanks health by a given amount
	 * 
	 * @param healPoints
	 *            - how many points the tank is given
	 */
	public void heal(int healPoints);
	
}
