package bubolo.graphics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Iterator;
import java.util.List;

/**
 * A sprite can hold AnimatedTextures in addition to Textures. 
 * AnimatedTextures switch between frames in a texture (called "TextureRegions" by our library) 
 * after certain amounts of time, and can be set to loop or not loop. 
 * Looping animated textures benefit things such as the Tank, 
 * for which we have a set of textures that make it look like the tank tracks are moving, 
 * while non looping textures could be used for things such as explosions.
 * @author BU CS673 - Clone Productions
 */

public class AnimatedTexture
{
	private List<TextureRegion> frames;
	private TextureRegion currentFrame;
	private long millisPerFrame;
	private long nextFrameChangeTime;
	private boolean loop;
	private boolean finished;
	Iterator<TextureRegion> frameItr = frames.iterator();
	
	/**
	 * Protected constructor
	 */
	protected AnimatedTexture(List<TextureRegion> frames, long millisPerFrame, boolean loop){
		this.frames = frames;
		this.millisPerFrame = millisPerFrame;
		this.loop = loop;	
	}
	
	/**
	 * Changes the currentTextureRegion to point at the next frame if the current time 
	 * exceeds the nextFrameChangeTime. We can get the current time from System.currentTimeMillis(). 
	 * Sets finished to true if there are no more frames, and the loop variable is set to false. 
	 * If the current frame is changed, this should also update nextFrameChangeTime 
	 * to the current time plus millisPerFrame.
	 * @return 
	 */
	public void animate(){
		//loop once 
		animateWithoutLoop();
		//while loop is true, keep looping
		while (loop){
			animateWithoutLoop();
		}
	}
	public void animateWithoutLoop(){
		if (System.currentTimeMillis()> nextFrameChangeTime){
			//Changes the currentTextureRegion to point at the next frame
			currentFrame = frameItr.next();
			
			nextFrameChangeTime = System.currentTimeMillis() + millisPerFrame;
			
			if (!frameItr.hasNext()){
				finished = true;
				loop = false;
			}
		}	
	}
	
	/**
	 * @return a reference to the currentFrame
	 */
	public TextureRegion getTextureRegion(){
		return currentFrame;
	}
	
	/**
	 * @return the value of the loop variable
	 */
	public boolean isFinished(){
		return finished;
	}
	
}
