/**
 *
 */

package bubolo.net.command;

import java.util.UUID;

import bubolo.net.NetworkCommand;
import bubolo.world.World;
import bubolo.world.entity.concrete.Tank;

/**
 * Moves a tank and updates its speed.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MoveTank implements NetworkCommand
{
	private static final long serialVersionUID = 1L;

	private final UUID id;
	private final float speed;
	private final float x;
	private final float y;
	private final float rotation;

	/**
	 * Constructs a Move Tank command.
	 * @param tank the tank to move.
	 */
	public MoveTank(Tank tank)
	{
		this.id = tank.getId();
		this.speed = tank.getSpeed();
		this.x = tank.getX();
		this.y = tank.getY();
		this.rotation = tank.getRotation();
	}

	@Override
	public void execute(World world)
	{
		Tank tank = (Tank)world.getEntity(id);
		tank.setSpeed(new NetTankSpeed(speed));
		tank.setX(x);
		tank.setY(y);
		tank.setRotation(rotation);
	}
}
