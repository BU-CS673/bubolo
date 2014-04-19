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
	 * @param w reference to the world.
	 */
	public void updateTilingState(World w);

	/**
	 * Returns the current adaptive tiling state of this Entity.
	 * @return the current adaptive tiling state of this Entity.
	 */
	public int getTilingState();

	/**
	 * Manually sets the adaptive tiling state of this Entity.
	 * @param newState the new adaptive tiling state.
	 */
	public void setTilingState(int newState);
}
