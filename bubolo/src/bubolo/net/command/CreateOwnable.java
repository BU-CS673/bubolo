/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.net.command;

import java.util.UUID;
import java.util.logging.Logger;

import bubolo.util.GameLogicException;
import bubolo.world.Ownable;
import bubolo.world.World;
import bubolo.world.entity.Entity;

/**
 * Creates an ownable entity on remote computers.
 * 
 * @author BU CS673 - Clone Productions
 */
public class CreateOwnable extends CreateEntity
{
	private static final long serialVersionUID = 1L;
	
	private final UUID ownerUID;

	/**
	 * Constructs a CreateOwnable object.
	 * 
	 * @param type
	 *            the ownable's class.
	 * @param id
	 *            the ownable's unique id.
	 * @param x
	 *            the ownable's x position.
	 * @param y
	 *            the ownable's y position.
	 * @param rotation
	 *            the ownable's rotation.
	 * @param ownerUID
	 *            the id of the entity that owns the ownable.
	 */
	public CreateOwnable(Class<? extends Entity> type, UUID id, float x, float y, float rotation,
			UUID ownerUID)
	{
		super(type, id, x, y, rotation);
		this.ownerUID = ownerUID;
	}

	@Override
	public void execute(World world)
	{
		super.execute(world);

		try
		{
			Ownable ownable = (Ownable)world.getEntity(getId());
			if (ownerUID != null)
			{
				ownable.setOwnerUID(ownerUID);
				ownable.setOwned(true);
			}
			else
			{
				ownable.setOwned(false);
			}
		}
		catch (GameLogicException e)
		{
			Logger.getGlobal().severe("CreateBullet: The bullet was not created. ID: " + getId());
		}
	}
}
