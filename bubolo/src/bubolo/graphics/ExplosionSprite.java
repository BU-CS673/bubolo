package bubolo.graphics;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import bubolo.world.entity.concrete.Explosion;

/**
 * The graphical representation of an Explosion.
 * @author BU CS673 - Clone Productions
 */
class ExplosionSprite extends Sprite<Explosion>
{
		
	// The index of the texture region that will be drawn.
	private int imageIndex;
	
	// The list of texture regions, which is used for the explosion animation.
	private List<TextureRegion> images;
	
	// The number of milliseconds per frame.
	private static final long millisPerFrame = 50;

	// The amount of time remaining for the current frame.
	private long frameTimeRemaining;
	
	// The time of the last frame, in milliseconds.
	private long lastFrameTime;
	
	/**
	 * Constructor for the ExplosionSprite. This is Package-private because sprites
	 * should not be directly created outside of the graphics system
	 * (instead, call the Sprite.create(entity) static method).
	 * @param explosion Reference to the tank that this ExplosionSprite represents.
	 */
	ExplosionSprite(Explosion explosion)
	{
		super(DrawLayer.TANKS, explosion);
		
		Texture texture = Graphics.getTexture(Graphics.TEXTURE_PATH + "mine_explosion_sheet_smoke_alpha.png");
		images = new ArrayList<TextureRegion>(12);
		
			images.add(new TextureRegion(texture, 0, 0, 60, 60));
			images.add(new TextureRegion(texture, 60, 0, 60, 60));
			images.add(new TextureRegion(texture, 120, 0, 60, 60));
			images.add(new TextureRegion(texture, 180, 0, 60, 60));
			images.add(new TextureRegion(texture, 0, 60, 60, 60));
			images.add(new TextureRegion(texture, 60, 60, 60, 60));
			images.add(new TextureRegion(texture, 120, 60, 60, 60));
			images.add(new TextureRegion(texture, 180, 60, 60, 60));
			images.add(new TextureRegion(texture, 0, 120, 60, 60));
			images.add(new TextureRegion(texture, 60, 120, 60, 60));
			images.add(new TextureRegion(texture, 120, 120, 60, 60));
			images.add(new TextureRegion(texture, 180, 120, 60, 60));
			
			imageIndex = 0;
			frameTimeRemaining = millisPerFrame;
	}

	@Override
	public void draw(SpriteBatch batch, Camera camera, DrawLayer layer)
	{ 
		drawTexture(batch, camera, layer, images.get(imageIndex));
		
		// Play the explosion animation.
		
		frameTimeRemaining -= (System.currentTimeMillis() - lastFrameTime);
		lastFrameTime = System.currentTimeMillis();
		if (frameTimeRemaining < 0)
		{
			frameTimeRemaining = millisPerFrame;
			imageIndex = (imageIndex == images.size() - 1) ? 0 : imageIndex + 1;
		}
	}
}
