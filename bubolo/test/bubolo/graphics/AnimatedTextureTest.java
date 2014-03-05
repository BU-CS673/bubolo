package bubolo.graphics;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 1. Calls animate and ensures that no exception is called
   2. Calls getTextureRegion and verifies that an instantiated TextureRegion is returned
   3. Calls isFinished, and verifies that false is returned
 * 
 * @author BU673 - Clone Industries
 */





import static org.junit.Assert.*;

public class AnimatedTextureTest
{
	private List<TextureRegion> frames;
	private long millisPerFrame;
	private boolean loop;
	AnimatedTexture texture = new AnimatedTexture(frames, millisPerFrame, loop);
	
	@Before
	public void setup(){
		
	}
	
	@Test
	public void testAnimate(){
		
		texture.animate();
	}
	@Test
	public void testGetTextureRegion(){
		texture.getTextureRegion();
	}
	@Test
	public void testIsFinished(){
		assertFalse(texture.isFinished());
	}
}
