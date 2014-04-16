/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import bubolo.net.NetworkCommand;
import bubolo.world.World;

/**
 * @author BU CS673 - Clone Productions
 */
@Deprecated
public class HelloNetworkCommand implements NetworkCommand
{
	private final String message;
	
	public HelloNetworkCommand(String message)
	{
		this.message = message;
	}

	@Override
	public void execute(World world)
	{
		System.out.println(message);
	}
}
