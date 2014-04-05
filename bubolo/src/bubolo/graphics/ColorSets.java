package bubolo.graphics;

/**
 * Used to determine the color for standardized sprite textures that have more than one
 * color set available. These values are indices used when selecting which row of frames
 * to use in sprite display.
 * 
 */
public class ColorSets
{
	/**
	 * Index used for the main character color, blue, indicating that it appears in the
	 * first row of multi-color textures.
	 */
	public static final int BLUE = 0;
	/**
	 * Index used for the enemy character color, red, indicating that it appears in the
	 * second row of multi-color textures.
	 */
	public static final int RED = 1;
	/**
	 * Index used for the main character color, usually white/gray, indicating that it
	 * appears in the third row of multi-color textures.
	 */
	public static final int NEUTRAL = 2;
}
