package basic_practice;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
public class test2 {
	@Test
	public void testqueryBridgeWords() throws IOException {
		basic_practice example = new basic_practice();
		//example.saveMap();
		String result = example.queryBridgeWords("time","watching");
		assertEquals("when happens",result);
	}

}
