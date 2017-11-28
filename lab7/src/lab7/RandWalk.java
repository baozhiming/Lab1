package lab7;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public class RandWalk {
	public static String overWords;
	static void randomWalk(HashMap<String,Edge> headNode)
	{
        
		String[] keys = headNode.keySet().toArray(new String[0]);
		HashMap<Integer,String> overWord= new HashMap<Integer,String>();
		Random random = new Random();
		String firstRandomKey = keys[random.nextInt(keys.length)];
		String randomKey = firstRandomKey;
        String overWords = firstRandomKey;
        int i = 0;
		while(headNode.get(randomKey) != null)
		{
		    String[] nextKeys = headNode.get(randomKey).EdgeNode.keySet().toArray(new String[0]);
		    Random nextRandom = new Random();
		    String nextKey = nextKeys[nextRandom.nextInt(nextKeys.length)];
		    
		    if(!overWord.containsValue(randomKey + " " + nextKey))
		    {
		    	overWord.put(i,randomKey + " " + nextKey);
		    	i++;
		    }
		    else
		    {
		    	overWords = overWords + " " + nextKey;
		    	break;
		    }
		    overWords = overWords + " " + nextKey;
		    randomKey = nextKey;
		}
		/*-----------------将生成的随机路径在图中显示---------------------------*/
		String[] tempArray = overWords.split("\\s+");
		GraphViz gv = new GraphViz();
	    gv.addln(gv.start_graph());
	    Iterator<String> iterator = headNode.keySet().iterator();
		while(iterator.hasNext())
		{
	    String key = iterator.next();
	    if(headNode.get(key) != null)
	        {
			    Iterator<String> iterator1 = headNode.get(key).EdgeNode.keySet().iterator();
			    while(iterator1.hasNext())
		        {
		            String key1 = iterator1.next();
				    Integer a = headNode.get(key).EdgeNode.get(key1);
				    gv.addln(key + "->" + key1 + "[label = " + a + "]");
		        }
     	     }
		 }
		 for(int j = 0;j<tempArray.length -1;j++)
		 {
			 gv.addln(tempArray[j] + "->" + tempArray[j+1] + "[color=red]");
		 }
		 gv.addln(gv.end_graph());
		// System.out.println(gv.getDotSource());
		 String type = "gif";
		 File out = new File("F:\\ming\\out3." + type);    // Windows
		 gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	
	}

}
