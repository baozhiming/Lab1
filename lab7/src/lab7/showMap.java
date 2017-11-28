package lab7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;

public class showMap {
	private static HashMap<String,Edge> headNode = new HashMap<String,Edge>();
	public void saveHashmap() throws IOException {
		/*****************读取文本内容*************************/
		File file = new File("F:\\baoming.txt");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
		String str;
		/**********文本内容***********************************/
		String line = " ";                           		
		while((str=bufr.readLine())!=null)
		{
			str = str.replaceAll("[^a-zA-Z]", " ");
			str = str.replaceAll("\t"," ");
			str = str.replaceAll("\r"," ");
			line = line + str+ " ";
		}		
		line = line.toLowerCase();
		line = line.trim();
        /************************将文件中的内容读入arrayList数组中*******************/	
		String[] arrayList = line.split("\\s+") ;
		bufr.close();
	
        /********************************将文本输入到图中*********************************/	
		for(int i=0;i<arrayList.length-1;i++)
		{
			if(!headNode.containsKey(arrayList[i]))
			{
				Edge tempNode = new Edge(arrayList[i+1],1) ;
				headNode.put(arrayList[i],tempNode);
			}
			else
			{
				if(!headNode.get(arrayList[i]).EdgeNode.containsKey(arrayList[i+1]))
				{
					headNode.get(arrayList[i]).EdgeNode.put(arrayList[i+1], 1);
				}
				else
				{
					Integer nowEdge =  headNode.get(arrayList[i]).EdgeNode.get(arrayList[i+1]);
					headNode.get(arrayList[i]).EdgeNode.put(arrayList[i+1], nowEdge+1);
				} 
			}
			
		}
		headNode.put(arrayList[arrayList.length-1],null);
	}
	/*********************************展示有向图*****************************/
	public void showDirectedGraph()
	{
		Graphviz gv = new Graphviz();
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
	    gv.addln(gv.end_graph());
	    System.out.println(gv.getDotSource());
	    String type = "gif";
	    File out = new File("F:\\ming\\out1." + type);    // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
	
	public HashMap<String,Edge> getHashmap() {
		return headNode;
	}

}
class Edge
{
	HashMap<String,Integer> EdgeNode = new HashMap<String,Integer>();
	Edge(String theNode,Integer number){
		this.EdgeNode.put(theNode, number);
	}

}
