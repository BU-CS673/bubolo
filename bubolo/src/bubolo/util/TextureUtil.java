package bubolo.util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Used for processing standard format textures of different kinds for use in adaptive
 * tiling, animations, and coloration.
 * 
 * @author BU CS673 - Clone Productions
 */
public class TextureUtil
{
	/**
	 * Split a sprite texture up into discrete frames, using the standardized height and
	 * width of each frame to determine start and end points of each frame. Used for
	 * separating ColorSets and animation frames. Note that all frames must be the same
	 * size, and the texture must have no empty space.
	 * 
	 * @param tex
	 *            is the texture to be split into frames.
	 * @param frameWidth
	 *            is the width of each frame.
	 * @param frameHeight
	 *            is the height of each frame.
	 * @return a two-sided array of TextureRegions, representing each frame. They are
	 *         returned in [COLUMN][ROW] order to allow easy separation by ColorSet.
	 */
	public static TextureRegion[][] splitFrames(Texture tex, int frameWidth, int frameHeight)
	{
		if (tex.getWidth() % frameWidth != 0 || tex.getHeight() % frameHeight != 0)
		{
			throw new TextureFormatException("Cannot split texture into frames, wrong size!");
		}
		int rows = tex.getHeight() / frameHeight;
		int columns = tex.getWidth() / frameWidth;

		TextureRegion[][] frameSets = new TextureRegion[columns][rows];
		for (int col = 0; col < columns; col++)
		{
			for (int row = 0; row < rows; row++)
			{
				frameSets[col][row] = new TextureRegion(tex, col * frameWidth, row * frameHeight,
						frameWidth, frameHeight);
			}
		}
		return frameSets;

	}

	/**
	 * Convert a single .png representing multiple tiling states into an Array of 16
	 * different texture regions, according to the established 4x4 standard layout.
	 * 
	 * @param tex
	 *            is a 4x4 input texture to be split. Must be at 128 x 128 resolution.
	 * @return an array of TextureRegions representing textures for each of the 16
	 *         adaptive tiling states.
	 */
	public static TextureRegion[] adaptiveSplit_16(Texture tex)
	{

		if (tex.getHeight() != Coordinates.WORLD_SCALE*4 && tex.getWidth() != Coordinates.WORLD_SCALE*4)
		{
			throw new TextureFormatException("Cannot split texture into 16 tiles, wrong size!");
		}

		TextureRegion[] adapt = new TextureRegion[16];

		// Grab the 16 texture frames for a standard 4x4 layout

		TextureRegion[][] allFrames = splitFrames(tex, Coordinates.WORLD_SCALE, Coordinates.WORLD_SCALE);

		// Assign each texture frame to the correct index
		adapt[0] = allFrames[0][0];
		adapt[1] = allFrames[0][3];
		adapt[2] = allFrames[0][1];
		adapt[3] = allFrames[0][2];
		adapt[4] = allFrames[3][0];
		adapt[5] = allFrames[3][3];
		adapt[6] = allFrames[3][1];
		adapt[7] = allFrames[3][2];
		adapt[8] = allFrames[1][0];
		adapt[9] = allFrames[1][3];
		adapt[10] = allFrames[1][1];
		adapt[11] = allFrames[1][2];
		adapt[12] = allFrames[2][0];
		adapt[13] = allFrames[2][3];
		adapt[14] = allFrames[2][1];
		adapt[15] = allFrames[2][2];

		return adapt;
	}

	/**
	 * Convert a single .png representing multiple tiling states into an Array of 9
	 * different texture regions, according to the established 3x3 standard layout.
	 * Primarily used for the DeepWater Terrain.
	 * 
	 * @param tex
	 *            is a 3x3 input texture to be split. Must be at 96 x 96 resolution.
	 * @return an array of TextureRegions representing textures for each of the 9 adaptive
	 *         tiling states.
	 */
	public static TextureRegion[] adaptiveSplit_9(Texture tex)
	{
		if (tex.getHeight() != Coordinates.WORLD_SCALE*3 && tex.getWidth() != Coordinates.WORLD_SCALE*3)
		{
			throw new TextureFormatException("Cannot split texture into 9 tiles, wrong size!");
		}

		// Grab the 9 texture states for a standard 3x3 layout
		TextureRegion[][] allFrames = splitFrames(tex, Coordinates.WORLD_SCALE, Coordinates.WORLD_SCALE);

		TextureRegion[] adapt = new TextureRegion[9];
		adapt[0] = allFrames[0][0];
		adapt[1] = allFrames[1][0];
		adapt[2] = allFrames[2][0];
		adapt[3] = allFrames[0][1];
		adapt[4] = allFrames[1][1];
		adapt[5] = allFrames[2][1];
		adapt[6] = allFrames[0][2];
		adapt[7] = allFrames[1][2];
		adapt[8] = allFrames[2][2];

		return adapt;
	}

	/**
	 * Convert a single .png representing multiple tiling states into an Array of 34
	 * different texture regions, according to the established 4x4 + 3x3 + 3x3 standard
	 * layout. Primarily used for the Water Terrain.
	 * 
	 * @param tex
	 *            is a 4x4 + 3x3 + 3x3 input texture to be split. Must be at 224 x 192
	 *            resolution.
	 * @return an array of TextureRegions representing textures for each of the 9 adaptive
	 *         tiling states.
	 */
	public static TextureRegion[] adaptiveSplit_16_9_9(Texture tex)
	{
		if (tex.getHeight() != Coordinates.WORLD_SCALE*7 && tex.getWidth() != Coordinates.WORLD_SCALE*6)
		{
			throw new TextureFormatException("Cannot split texture into 16x9x9 tiles, wrong size!");
		}

		TextureRegion[] adapt = new TextureRegion[34];

		// Grab the 34 texture frames for a standard 4x4 + 3x3 + 3x3 layout

		TextureRegion[][] allFrames = splitFrames(tex, Coordinates.WORLD_SCALE, Coordinates.WORLD_SCALE);

		// Assign each texture frame to the correct index...

		// ex. rivers
		adapt[0] = allFrames[0][0];
		adapt[1] = allFrames[0][3];
		adapt[2] = allFrames[0][1];
		adapt[3] = allFrames[0][2];
		adapt[4] = allFrames[3][0];
		adapt[5] = allFrames[3][3];
		adapt[6] = allFrames[3][1];
		adapt[7] = allFrames[3][2];
		adapt[8] = allFrames[1][0];
		adapt[9] = allFrames[1][3];
		adapt[10] = allFrames[1][1];
		adapt[11] = allFrames[1][2];
		adapt[12] = allFrames[2][0];
		adapt[13] = allFrames[2][3];
		adapt[14] = allFrames[2][1];
		adapt[15] = allFrames[2][2];

		// ex. open water
		adapt[16] = allFrames[0][4];
		adapt[17] = allFrames[1][4];
		adapt[18] = allFrames[2][4];
		adapt[19] = allFrames[0][5];
		adapt[20] = allFrames[1][5];
		adapt[21] = allFrames[2][5];
		adapt[22] = allFrames[0][6];
		adapt[23] = allFrames[1][6];
		adapt[24] = allFrames[2][6];

		// ex. open water
		adapt[25] = allFrames[3][4];
		adapt[26] = allFrames[4][4];
		adapt[27] = allFrames[5][4];
		adapt[28] = allFrames[3][5];
		adapt[29] = allFrames[4][5];
		adapt[30] = allFrames[5][5];
		adapt[31] = allFrames[3][6];
		adapt[32] = allFrames[4][6];
		adapt[33] = allFrames[5][6];

		return adapt;
	}
}
