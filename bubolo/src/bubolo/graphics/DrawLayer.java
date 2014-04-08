package bubolo.graphics;

/**
 * Used to determine the draw order. Sprites that are drawn first may be
 * partially or fully covered by a sprite that is drawn later.
 * @author BU CS673 - Clone Productions
 */
enum DrawLayer
{
	
	/**
	 * The background layer. Used to fill small holes in transparent textures where no terrain is visible underneath (edges of rivers).
	 */
	BACKGROUND,
	
	/**
	 * The lowest Terrain layer. This will usually be the lowest visible layer. Grass and Swamp.
	 */
	BASE_TERRAIN, 
	
	/**
	 * The highest Terrain layer, above base Terrain, below StationaryElements. Used for Terrains that use transparency, such as Roads and Water.
	 * 
	 */
	TERRAIN,
	
	/**
	 * The StationaryElements layer. This is the fourth layer drawn. Above Terrain, below Actors.
	 */
	STATIONARY_ELEMENTS, 
	
	/**
	 * The Actors layer. This is the second-to-last layer drawn. Above StationaryElements, below Effects.
	 */
	ACTORS,
	
	/**
	 * The Effects layer. This is the last layer drawn. Above all other game objects.
	 */
	EFFECTS;
}
