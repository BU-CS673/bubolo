/**
 *
 */

package bubolo.net.command;

import static com.google.common.base.Preconditions.*;
import bubolo.net.NetworkCommand;
import bubolo.world.World;

/**
 * Command that is used to notify the clients to start the game.
 * 
 * @author BU CS673 - Clone Productions
 */
public class StartGame implements NetworkCommand
{
	private static final long serialVersionUID = 1L;
	
	private final SendMap sendMapCommand;
	
	/**
	 * 
	 * @param sendMapCommand an instantiated send map command.
	 */
	public StartGame(SendMap sendMapCommand)
	{
		this.sendMapCommand = checkNotNull(sendMapCommand);
	}

	@Override
	public void execute(World world)
	{
		sendMapCommand.execute(world);
	}
}
