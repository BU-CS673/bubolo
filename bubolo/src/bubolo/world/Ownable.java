package bubolo.world;

/**
 * Interface for Entities that are owned by a specific player. Used to determine object
 * color, friendly/enemy units, etc.
 * 
 * @author BU CS673 - Clone Productions
 */
public interface Ownable
{
	/**
	 * Returns whether this Entity belongs to the local player.
	 * 
	 * @return true if this tank belongs to the local player, false otherwise.
	 */
	public boolean isLocalPlayer();

	/**
	 * Sets whether this Entity belongs to the local player or not.
	 * 
	 * @param local
	 *            represents whether this tank should belong to the local player. Pass
	 *            false if not.
	 */
	public void setLocalPlayer(boolean local);

	/**
	 * Returns whether this Entity belongs to the local player.
	 * 
	 * @return true if this tank belongs to the local player, false otherwise.
	 */
	public boolean isOwned();

	/**
	 * Sets whether this Entity belongs to the local player or not.
	 * 
	 * @param local
	 *            represents whether this tank should belong to the local player. Pass
	 *            false if not.
	 */
	public void setOwned(boolean owned);
}
