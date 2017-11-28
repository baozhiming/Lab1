package lab7;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class calshortDistance {
	private static String Minstep;
	
	public String getMinstep() {
		return Minstep;
	}

	static void calcShortestPath(String startNode, String endNode,HashMap<String,Edge> headNode)
	{
		HashSet<String> outNode = new HashSet<String>();
		LinkedList<String> nextNode = new LinkedList<String>();
		LinkedList<String> resultStep = new LinkedList<String>();
		//起点到各节点的距离
		HashMap<String,preNode> nodeStep = new HashMap<String,preNode>();
		if((!headNode.containsKey(startNode)) || (!headNode.containsKey(endNode)))
		{
			Minstep =  "No this Word!";
		}
		if(startNode.equals(endNode))
		{
			Minstep = "The distance is zero!";
		}
		nextNode.add(startNode);
		while(!nextNode.isEmpty())
		{
			int step = 0;
			String start = nextNode.removeFirst();
		    if(nodeStep.containsKey(start))
			{
				step = nodeStep.get(start).getNodeStep();
			}
		    if(headNode.get(start) != null)
		    {
			    Iterator<String> iterator = headNode.get(start).EdgeNode.keySet().iterator();
			    while(iterator.hasNext())
			    {
				    String key = iterator.next();
				    if(key == startNode)
				    {
					    continue;
				    }
				    Integer allStep = step + headNode.get(start).EdgeNode.get(key);
				    if(nodeStep.containsKey(key))
				    {
					    if(allStep < nodeStep.get(key).getNodeStep())
					    {
						     nodeStep.put(key,new preNode(start,allStep));
					    }
				    }
				    else
				    {
					    nodeStep.put(key, new preNode(start,allStep));
				    }
				    if((!nextNode.contains(key)) && (!outNode.contains(key)))
				    {
					    nextNode.addLast(key);
				    }
			    }
		    }
		  //  else {
		  //  	 nodeStep.put(start,null);
		  //  }
			outNode.add(start);
		}
       /*****************************************将得到的nodeStep输出最短路径***********************************/		
	   if(headNode.get(startNode) == null)
	   {
		   Minstep =  "Can not reach it!";
	   }
	   if(!nodeStep.containsKey(endNode))
	   {
		   Minstep = "Can not reach it!";
	   }

	   String tempNode = endNode;
	   resultStep.addFirst(endNode);
	   while(tempNode != startNode)
	   {
		   
		   resultStep.addFirst(nodeStep.get(tempNode).getpreNodeStr());
		   tempNode = nodeStep.get(tempNode).getpreNodeStr();
	   }                                                                 /******将存储好的hashMap以反向顺序将其输出并存入result字符串*****/
	   
       /************************************将最短路径存放在minStep字符串中并输出********************************/
	   String minStep = resultStep.removeFirst();
	   String minStepMap = minStep;
	   for(Iterator iter = resultStep.iterator();iter.hasNext();)
	   {
		   minStep = minStep + "——>" + iter.next();
	   }
	   /*************************************画图将最短路径输出***********************************************/
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
	   for(Iterator<String> iter = resultStep.iterator();iter.hasNext();)
	   {
		   String b = iter.next();
		   gv.addln(minStepMap + "->" + b + "[color=red]");
		   minStepMap = b;
	   }   
	   gv.addln(gv.end_graph());
	 // System.out.println(gv.getDotSource());
	   String type = "gif";
	   File out = new File("F:\\ming\\out2." + type);    // Windows
	   gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );

	   return minStep;
	}
	

}
class preNode
{
	private String preNodeStr;
	private int toNodeStep;
	public preNode(String preNodeStr,int nodeStep)
	{
		this.preNodeStr = preNodeStr;
		this.toNodeStep = nodeStep;
	}
	public String getpreNodeStr()
	{
		return preNodeStr;
	}
	public int getNodeStep()
	{
		return toNodeStep;
	}
}