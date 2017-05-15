package JavaTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import junit.framework.TestCase;

public class TestCaseWithStdOutCatch extends TestCase {
	protected ByteArrayOutputStream outContent = null;
	//protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	public void setUpOutContent() throws IOException {
		if( outContent != null ) outContent.close();
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	    //System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	    //System.setErr(null);
	}
	
	public TestCaseWithStdOutCatch(String testName) {
		super( testName );
	}
	
}
