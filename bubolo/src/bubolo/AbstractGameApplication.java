/**
 *
 */

package bubolo;

/**
 * Abstract base class for Game Applications.
 * 
 * @author BU CS673 - Clone Productions
 */
public abstract class AbstractGameApplication implements GameApplication
{
	private boolean ready;
	private boolean gameStarted;
	
	@Override
	public final boolean isReady()
	{
		return ready;
	}
	
	/**
	 * Sets whether the game is ready.
	 * @param value true if the game is ready.
	 */
	protected void setReady(boolean value)
	{
		ready = value;
	}
	
	@Override
	public final void startGame()
	{
		gameStarted = true;
	}
	
	/**
	 * Specifies whether the game has started.
	 * @return whether the game has started.
	 */
	protected boolean isGameStarted()
	{
		return gameStarted;
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
}
