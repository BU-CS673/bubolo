package bubolo.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;

import bubolo.world.World;

public class MapImporterTest {

	@Test
	public void importEverardIsland() throws IOException {
		MapImporter importer = new MapImporter();
		Path mapPath = FileSystems.getDefault().getPath("res", "maps/Everard Island.json");
		var results = importer.importJsonMapWithDiagnostics(mapPath, true);

		World world = results.getLeft();
		MapImporter.Diagnostics diagnostics = results.getRight();

		assertNotNull(world);
		assertEquals(3, diagnostics.layerCount());
		assertEquals(2, diagnostics.tilesetCount());
		assertEquals(11, diagnostics.typesImported().size());
		assertEquals(114, diagnostics.tileWidth());
		assertEquals(64, diagnostics.tileHeight());
	}

}
