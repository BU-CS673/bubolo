package bubolo.graphics;

/**
 * Used to determine the draw order. Sprites that are drawn first may be partially or fully covered
 * by a sprite that is drawn later.
 * 
 * @author BU CS673 - Clone Productions
 */
enum DrawLayer
{

	/**
	 * The background layer. Using this in everything except for the background layer will cause the
	 * sprite to never be drawn.
	 */
	BACKGROUND,

	/**
	 * The lowest draw layer.
	 */
	FIRST,

	/**
	 * The second draw layer.
	 */
	SECOND,

	/**
	 * The third draw layer.
	 */
	THIRD,

	/**
	 * The top draw layer.
	 */
	TOP,

//	/**
//	 * The Effects layer. This is the last layer drawn. Above all other game objects.
//	 */
//	EFFECTS;
}
