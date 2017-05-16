package JavaTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;

import junit.framework.TestCase;

public class TestCaseWithStdOutCatch extends TestCase {
	protected ByteArrayOutputStream outContent = null;
	
	public void setUpOutContent() throws IOException {
		if( outContent != null ) outContent.close();
		outContent = new ByteArrayOutputStream();
	    System.setOut(new PrintStream(outContent));
	}

	public void restoreSysOut()
	{
		System.setOut(null);
	}
	
	public TestCaseWithStdOutCatch(String testName) {
		super( testName );
	}
	
}
