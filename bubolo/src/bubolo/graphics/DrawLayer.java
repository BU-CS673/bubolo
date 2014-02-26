package bubolo.graphics;

/**
 * Used to determine the draw order. Sprites that are drawn first may be
 * partially or fully covered by a sprite that is drawn later.
 * @author BU CS673 - Clone Productions
 */
public enum DrawLayer
{
	/**
	 * The terrain layer. This is the first layer drawn.
	 */
	TERRAIN, 
	
	/**
	 * The terrain modifier layer. This is the second layer drawn.
	 */
	TERRAIN_MODIFIERS, 
	
	/**
	 * The game objects layer. This is the third layer drawn.
	 */
	OBJECTS, 
	
	/**
	 * The tanks layer. This is the last layer drawn.
	 */
	TANKS;
}
