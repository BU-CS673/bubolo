/**
 *
 */

package bubolo.net.command;

/**
 * Used by the Network Commands to set the tank's speed.
 * @author BU CS673 - Clone Productions
 */
public final class NetTankSpeed
{
	private final float speed;
	
	/**
	 * Constructs a Net Tank Speed object.
	 * @param speed the tank's new speed.
	 */
	NetTankSpeed(float speed)
	{
		this.speed = speed;
	}
	
	/**
	 * Gets the tank's new speed.
	 * @return the tank's new speed.
	 */
	public float getSpeed()
	{
		return speed;
	}
}
