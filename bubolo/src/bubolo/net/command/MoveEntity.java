/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import bubolo.net.NetworkCommand;
import bubolo.util.GameLogicException;
import bubolo.world.World;
import bubolo.world.entity.Entity;

/**
 * Moves an entity in the world.
 * 
 * @author BU CS673 - Clone Productions
 */
public class MoveEntity implements NetworkCommand
{
	private static final long serialVersionUID = 1L;

	private final UUID id;

	private final int x;
	private final int y;

	private final float rotation;

	/**
	 * Constructs a MoveEntity object.
	 * 
	 * @param entity
	 *            the entity to move.
	 */
	public MoveEntity(Entity entity)
	{
		this.id = entity.getId();
		this.x = (int)entity.getX();
		this.y = (int)entity.getY();
		this.rotation = entity.getRotation();
	}

	@Override
	public void execute(World world)
	{
		try
		{
			Entity entity = world.getEntity(id);
			entity.setX(x).setY(y).setRotation(rotation);
		}
		catch (GameLogicException e)
		{
			Logger.getGlobal().log(Level.WARNING, "MoveEntity: Unable to find entity " + id);
		}
	}
}
