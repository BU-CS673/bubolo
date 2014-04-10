package bubolo.controllers.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import bubolo.controllers.Controller;
import bubolo.net.Network;
import bubolo.net.NetworkSystem;
import bubolo.net.command.CreateEntity;
import bubolo.net.command.MoveTank;
import bubolo.world.World;
import bubolo.world.entity.concrete.Bullet;
import bubolo.world.entity.concrete.Tank;

/**
 * A controller for the local tank. This controller maps keyboard inputs to tank commands.
 * 
 * @author BU CS673 - Clone Productions
 */
public class KeyboardTankController implements Controller
{
	private Tank tank;

	/**
	 * Constructs a keyboard tank controller.
	 * 
	 * @param tank
	 *            reference to the local tank.
	 */
	public KeyboardTankController(Tank tank)
	{
		this.tank = tank;
	}

	@Override
	public void update(World world)
	{
		processMovement(tank);
		processCannon(tank, world);
		// processMineLaying(tank, world);
	}

	private static void processMovement(Tank tank)
	{
		// TODO (cdc - 3/14/2014): allow the key mappings to be changed.
		// TODO (cdc - 3/14/2014): test this.

		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP))
		{
			tank.accelerate();
			sendMove(tank);
		}
		else if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN))
		{
			tank.decelerate();
			sendMove(tank);
		}

		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT))
		{
			tank.rotateRight();
			sendMove(tank);
		}
		else if (Gdx.input.isKeyPressed(Keys.D) || Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			tank.rotateLeft();
			sendMove(tank);
		}
	}

	private static void processCannon(Tank tank, World world)
	{
		if (Gdx.input.isKeyPressed(Keys.SPACE) && tank.isCannonReady() && (tank.getAmmoCount() > 0) )
		{
			float tankCenterX = tank.getX();
			float tankCenterY = tank.getY();

			Bullet bullet = tank.fireCannon(world,
					tankCenterX + 18 * (float)Math.cos(tank.getRotation()),
					tankCenterY + 18 * (float)Math.sin(tank.getRotation()));
			
			Network net = NetworkSystem.getInstance();
			net.send(new CreateEntity(Bullet.class, bullet.getId(), bullet.getX(), bullet.getY(),
					bullet.getRotation()));

		}
	}

	private static void sendMove(Tank tank)
	{
		Network net = NetworkSystem.getInstance();
		net.send(new MoveTank(tank));
	}

	// TODO (cdc - 3/15/2014): Uncomment this once it's ready to be implemented.
	// private static void processMineLaying(Tank tank, World world)
	// {
	// // TODO (cdc - 3/14/2014): Change these to the correct lay mine keys:
	// if (Gdx.input.isKeyPressed(Keys.CONTROL_LEFT) ||
	// Gdx.input.isKeyPressed(Keys.CONTROL_RIGHT))
	// {
	// // TODO: lay a mine.
	// }
	// }
}
