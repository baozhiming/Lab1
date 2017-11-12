package basic_practice;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class test7 {

	@Test
	public void test() throws IOException {
		basic_practice example = new basic_practice();
	//	example.saveMap();
		String result = example.queryBridgeWords("Zhongguo","riben");
		assertEquals("No word1 or word2 in the graph!",result);
	}

}
