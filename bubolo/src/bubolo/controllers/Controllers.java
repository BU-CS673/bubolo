package bubolo.controllers;

import java.util.HashMap;
import java.util.Map;

import bubolo.controllers.ai.AIBaseController;
import bubolo.controllers.ai.AIMineController;
import bubolo.controllers.ai.AIPillboxController;
import bubolo.controllers.input.KeyboardTankController;
import bubolo.util.Nullable;
import bubolo.world.entity.Entity;
import bubolo.world.entity.concrete.Base;
import bubolo.world.entity.concrete.Mine;
import bubolo.world.entity.concrete.Pillbox;
import bubolo.world.entity.concrete.Tank;

/**
 * Contains static methods for creating controllers.
 * 
 * @author BU CS673 - Clone Productions
 */
public class Controllers
{
	private Map<Class<? extends Entity>, ControllerFactory> defaultFactories;

	private static Controllers instance;

	/**
	 * Returns the instance of this singleton.
	 * 
	 * @return the instance of this singleton.
	 */
	public static Controllers getInstance()
	{
		if (instance == null)
		{
			instance = new Controllers();
		}
		return instance;
	}

	/**
	 * Private constructor to prevent instantiation outside of getInstance().
	 */
	private Controllers()
	{
		defaultFactories = setDefaultControllerFactories();
	}

	/**
	 * Instantiates controllers for the specified entity. The optional ControllerFactory can be used
	 * to specify the exact controllers that will be created for the entity. Alternatively, passing
	 * a null reference will result in the creation of the default controllers for the entity.
	 * 
	 * @param entity
	 *            reference to the entity.
	 * @param factory
	 *            reference to a controller factory, or null if the default behavior should be used.
	 */
	public void createController(Entity entity, @Nullable ControllerFactory factory)
	{
		ControllerFactory controllerFactory = factory;
		if (controllerFactory == null)
		{
			controllerFactory = defaultFactories.get(entity.getClass());
		}

		if (controllerFactory != null)
		{
			controllerFactory.create(entity);
		}
	}

	/**
	 * Creates a map that maps entity classes to default factories.
	 * 
	 * @return reference to the ControllerFactory map.
	 */
	private static Map<Class<? extends Entity>, ControllerFactory> setDefaultControllerFactories()
	{
		Map<Class<? extends Entity>, ControllerFactory> factories = new HashMap<>();

		// TODO: Add default factories here.

		factories.put(Tank.class, new ControllerFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			public void create(Entity entity)
			{
				entity.addController(new KeyboardTankController((Tank)entity));
			}
		});

		factories.put(Pillbox.class, new ControllerFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			public void create(Entity entity)
			{
				entity.addController(new AIPillboxController((Pillbox)entity));
			}
		});

		factories.put(Mine.class, new ControllerFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			public void create(Entity entity)
			{
				entity.addController(new AIMineController((Mine)entity));
			}
		});		
		
		factories.put(Base.class, new ControllerFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			public void create(Entity entity)
			{
				entity.addController(new AIBaseController((Base)entity));
			}
		});
		
		return factories;
	}
}
