package basic_practice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class test8 {

	@Test
	public void testQueryBridgeWords() throws IOException {
		basic_practice example = new basic_practice();
		//example.saveMap();
		String result = example.queryBridgeWords("but","they");
		assertEquals("No bridge words from word1 to word2!",result);
	}

}
