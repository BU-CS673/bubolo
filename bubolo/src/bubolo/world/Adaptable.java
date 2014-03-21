package bubolo.world;

/**
 * Interface for Entities that are owned by a specific player. Used to determine object
 * color, friendly/enemy units, etc.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Adaptable
{
	/**
	 * Updates the current adaptive tiling state of this Entity.
	 */
	public void updateState();

	/**
	 * Returns the current adaptive tiling state of this Entity.
	 */
	public int getState();

	/**
	 * Manually sets the adaptive tiling state of this Entity.
	 */
	public void setState(int newState);
}
