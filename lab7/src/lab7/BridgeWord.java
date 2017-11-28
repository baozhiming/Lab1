package lab7;

import java.util.HashMap;
import java.util.Iterator;

public class BridgeWord {
	private static String bridgeWords="";
	
	static void queryBridgeWords(String word1,String word2,HashMap<String,Edge> headNode)
	{
		
		//+1 String NoBridgeWords = "No bridge words from word1 to word2!";    //No bridge words from word1 to word2!
		//+2 String NoWords = "No word1 or word2 in the graph!";            //No word1 or word2 in the graph!
		int number = 0;
		boolean contains1 = headNode.containsKey(word1);
		boolean contains2 = headNode.containsKey(word2);
		if(contains1 && contains2)
		{
			Iterator<String> iterator = headNode.get(word1).EdgeNode.keySet().iterator();
			while(iterator.hasNext())
			{
				String key = iterator.next();
				if(key == word2)
				{
					bridgeWords = "+1";
					break;
				}				
				else
				{
					if(headNode.get(key) != null)
					{
					    boolean containKey = headNode.get(key).EdgeNode.containsKey(word2);
					    if(containKey)
					    {
					    	bridgeWords = bridgeWords + key + " ";
						    ++number;						
					    }
					}
				}
			}
			if(number == 0)
			{
				bridgeWords = "+1 ";
			}
		}		
		else
		{
			bridgeWords = "+2 ";
		}
		bridgeWords = bridgeWords.substring(0,bridgeWords.length() - 1);
	}
	static String getresultWords() {
		return bridgeWords;
	}

}
