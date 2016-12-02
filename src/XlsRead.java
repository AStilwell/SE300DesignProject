import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import jxl.read.biff.BiffException;

public class XlsRead {
	Diagram diagram;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		diagram = new Diagram();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRead() {
		Boolean pass = false;
		try {
			diagram.read();
			if(diagram.getDiagram().get(0) != null){
				pass = true;
			}
		} catch (BiffException | IOException e) {
			pass = false;
		}
		assertTrue(pass);
	}
}
