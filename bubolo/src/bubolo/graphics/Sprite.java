/**
 * Copyright (c) 2014 BU MET CS673 Game Engineering Team
 *
 * See the file license.txt for copying permission.
 */

package bubolo.graphics;

import bubolo.util.Coordinates;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * @author BU CS673 - Clone Productions
 */
abstract class Sprite implements Drawable
{
	// The layer that this sprite is drawn to.
	private DrawLayer drawLayer;
		

	// Ideally, these probably should not be placed into Sprite<T>.
	private static final float SCALE_X = 1.f;
	private static final float SCALE_Y = 1.f;
	
	/**
	 * Constructs a sprite.
	 * @param layer the sprite's draw layer.
	 */
	protected Sprite(DrawLayer layer)
	{
		this.drawLayer = layer;
	}

	/**
	 * Returns the sprite's draw layer.
	 * 
	 * @return the sprite's draw layer.
	 */
	protected DrawLayer getDrawLayer()
	{
		return drawLayer;
	}
	
	/**
	 * Returns true if the underlying entity is destroyed, or false otherwise.
	 * 
	 * @return true if the underlying entity is destroyed, or false otherwise.
	 */
	protected abstract boolean isEntityDisposed();
	
	/**
	 * Draws the sprite to the screen.
	 * 
	 * @param batch
	 *            The game's SpriteBatch object. batch.begin() must have been called before the
	 *            SpriteBatch is passed to this Sprite.
	 * @param camera
	 *            The game's libgdx camera.
	 * @param layer
	 *            The layer that is currently being drawn. Note that this is not necessarily the
	 *            DrawLayer that this entity belongs to.
	 */
	abstract void draw(SpriteBatch batch, Camera camera, DrawLayer layer);

	/**
	 * Draws the texture to the screen.
	 * 
	 * @param batch
	 *            The game's SpriteBatch object. batch.begin() must have been called before the
	 *            SpriteBatch is passed to this Sprite.
	 * @param camera
	 *            The game's libgdx camera.
	 * @param layer
	 *            The layer that is currently being drawn. Note that this is not necessarily the
	 *            DrawLayer that this entity belongs to.
	 * @param texture
	 *            The texture to draw.
	 */
	protected void drawTexture(SpriteBatch batch, Camera camera, DrawLayer layer, Texture texture)
	{
		if (layer == getDrawLayer())
		{
			Vector2 cameraCoordinates = Coordinates.worldToCamera(camera,
					new Vector2(getX() - (texture.getWidth() / 2),
							getY() - (texture.getHeight() / 2)));

			Vector2 origin = getOrigin(texture.getWidth(), texture.getHeight());

			batch.draw(
					texture,
					cameraCoordinates.x,
					cameraCoordinates.y,
					origin.x,
					origin.y,
					texture.getWidth(),
					texture.getHeight(),
					SCALE_X,
					SCALE_Y,
					(float)(MathUtils.radiansToDegrees * (getRotation() - Math.PI / 2.f)),
					0, 0, texture.getWidth(), texture.getHeight(), false, false);
		}
	}

	/**
	 * Draws the texture to the screen.
	 * 
	 * @param batch
	 *            The game's SpriteBatch object. batch.begin() must have been called before the
	 *            SpriteBatch is passed to this Sprite.
	 * @param camera
	 *            The game's libgdx camera.
	 * @param layer
	 *            The layer that is currently being drawn. Note that this is not necessarily the
	 *            DrawLayer that this entity belongs to.
	 * @param texture
	 *            The texture region to draw.
	 */
	protected void drawTexture(SpriteBatch batch, Camera camera, DrawLayer layer,
			TextureRegion texture)
	{
		if (layer == getDrawLayer())
		{
			Vector2 cameraCoordinates = Coordinates.worldToCamera(camera,
					new Vector2(getX() - (texture.getRegionWidth() / 2),
							getY() - (texture.getRegionHeight() / 2)));

			Vector2 origin = getOrigin(texture.getRegionWidth(), texture.getRegionHeight());

			batch.draw(
					texture,
					cameraCoordinates.x,
					cameraCoordinates.y,
					origin.x,
					origin.y,
					texture.getRegionWidth(),
					texture.getRegionHeight(),
					SCALE_X,
					SCALE_Y,
					(float)(MathUtils.radiansToDegrees * (getRotation() - Math.PI / 2.f)));
		}

	}

	/**
	 * Returns the center of a given width and height.
	 * 
	 * @param width
	 *            the width of a texture or texture region.
	 * @param height
	 *            the height of a texture or texture region.
	 * @return the center of the given width and height.
	 */
	private static Vector2 getOrigin(float width, float height)
	{
		return new Vector2(width / 2.f, height / 2.f);
	}
}
