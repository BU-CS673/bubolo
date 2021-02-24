package bubolo.map;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.junit.Test;

public class MapImporterTest {

	@Test
	public void importEverardIsland() throws IOException {
		MapImporter importer = new MapImporter();
		MapImporter.Results results = importer.importJsonMap(FileSystems.getDefault().getPath("res", "maps/Everard Island.json"));
		
		assertNotNull(results.world());
		assertEquals(3, results.layerCount());
		assertEquals(2, results.tilesetCount());
		assertEquals(11, results.typesImported().size());
		assertEquals(114, results.tileWidth());
		assertEquals(64, results.tileHeight());
	}

}
