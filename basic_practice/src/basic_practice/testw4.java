package basic_practice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class testw4 {

	@Test
	public void testGenerateNewText() throws IOException{
		basic_practice example = new basic_practice();
	//	example.saveMap();
		String result = example.generateNewText("time all");
		assertEquals("time happens all",result);
	}
}
