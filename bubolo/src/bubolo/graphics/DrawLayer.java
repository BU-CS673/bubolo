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
	 * The background layer. Using this in anything except for the background layer will cause the
	 * sprite to never be drawn.
	 */
	BACKGROUND,

	/**
	 * The lowest sprite draw layer. Higher numbers are drawn above lower numbers.
	 */
	FIRST,

	/**
	 * The second-level sprite draw layer. Higher numbers are drawn above lower numbers.
	 */
	SECOND,

	/**
	 * The third-level sprite draw layer. Higher numbers are drawn above lower numbers.
	 */
	THIRD,

	/**
	 * The fourth sprite draw layer. Higher numbers are drawn above lower numbers.
	 */
	FOURTH,

	/**
	 * The top sprite draw layer.
	 */
	TOP
}
