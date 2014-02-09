package bubolo.graphics;

/**
 * Used to determine the draw order. Sprites that are drawn first may be
 * partially or fully covered by a sprite that is drawn later.
 * @author BU673 - Clone Industries
 */
public enum DrawLayer
{
	TERRAIN, TERRAIN_MODIFIERS, OBJECTS, TANKS;
}
