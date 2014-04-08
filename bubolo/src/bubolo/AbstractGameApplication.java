/**
 *
 */

package bubolo;

import bubolo.world.World;

/**
 * Abstract base class for Game Applications.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class AbstractGameApplication implements GameApplication
{
	private boolean ready;

	/** The game world. **/
	protected World world;

	@Override
	public final boolean isReady()
	{
		return ready;
	}

	/**
	 * Sets whether the game is ready.
	 * 
	 * @param value
	 *            true if the game is ready.
	 */
	protected void setReady(boolean value)
	{
		ready = value;
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resize(int width, int height)
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public boolean isGameStarted()
	{
		return (isReady() && world != null && world.getMapTiles() != null);
	}
}
