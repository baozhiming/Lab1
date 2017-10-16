package basic_practice;
import java.util.*;
import java.io.File;
import java.util.LinkedList;
import java.lang.Math;
import java.util.Scanner;

import javax.sound.sampled.Line;
import java.io.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

//头文件//

public class basic_practice{
	//存储结点 使用哈希表存储图的结构//


public class basic_practice{
	//存储结点

	static HashMap<String,Edge> headNode = new HashMap<String,Edge>();

	public static void main(String[] args) throws IOException{
		/*****************读取文本内容*************************/
		File file = new File("F:\\baoming.txt");
		FileInputStream fis = new FileInputStream(file);
		BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
		String str;
		/**********文本内容***********************************/
		String line = " ";                           

		//读入到字符串中//

		

		while((str=bufr.readLine())!=null)
		{
			str = str.replaceAll("[^a-zA-Z]", " ");
			str = str.replaceAll("\t"," ");
			str = str.replaceAll("\r"," ");
			line = line + str+ " ";
		}		
		line = line.toLowerCase();
		line = line.trim();
		System.out.println("---------------行字符串------------------------");
		System.out.println(line);
		
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
		
		/**************************将hashMap输出检查是否正确*****************/
		/*Iterator iterator = headNode.keySet().iterator();
		while(iterator.hasNext())
		{
			
			Object key = iterator.next();
			if(headNode.get(key) != null)
			{
			  Iterator iterator1 = headNode.get(key).EdgeNode.keySet().iterator();
			
			  while(iterator1.hasNext())
			  {
			  	  Object key1 = iterator1.next();
				  System.out.print(key + " ");
			      System.out.print(key1 + " ");
				  System.out.println(headNode.get(key).EdgeNode.get(key1));
			  }
			}
		    else
		    {
				  System.out.println((arrayList[arrayList.length-1] + " " + "null!"));
		    }
			
			
	    }*/
        /****************************输出有向图***********************************/	
	    showDirectedGraph(); 
	    
	    System.out.println("3 ： 桥接词                  4 : 新文本                          5：最短路径                      6：随机游走");
	    boolean Orcontinue = true;
	    while(Orcontinue == true)
	    {
	    	System.out.print("please input the number where can come true : ");
		    Scanner oner = new Scanner(System.in);
		    int Oner = oner.nextInt();
	    	switch(Oner)
	    	{
	    	case 3:
	            /****************************桥接词***************************************/		
	    		System.out.println("请输入要查找桥接词的两个字符串：  ");
	    		Scanner s = new Scanner(System.in);
	    		String Word1 = s.next();
	    		String Word2 = s.next();
	    		String result = queryBridgeWords(Word1, Word2);
	    		System.out.println(result.trim());
	    		break;
	    	case 4:
	    		/*****************************生成新文本**********************************/		
	    		System.out.print("请输入新文本 ： ");
	    		Scanner st = new Scanner(System.in);
	    		String NewLine = st.nextLine();
	    		NewLine = NewLine.toLowerCase().trim();
	    		System.out.println(generateNewText(NewLine).trim());
	    		break;
	    	case 5:
	            /*****************************最短距离************************************/
	    		System.out.println("请输入要计算最短距离的单词： ");
	    		Scanner sn = new Scanner(System.in);
	    		String startNode = sn.next();
	    		String endNode = sn.next();
	    		System.out.println(calcShortestPath(startNode,endNode));
	    		break;
	    	case 6:
	            /*****************************随机游走************************************/		
	    		System.out.println(randomWalk());
	    		break;
	    	}
	    }

	    
	}
	
    //==================================================展示有向图===============================================//
	static void showDirectedGraph()
	{
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
	    gv.addln(gv.end_graph());
	    System.out.println(gv.getDotSource());
	    String type = "gif";
	    File out = new File("F:\\ming\\out1." + type);    // Windows
	    gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
	}
	
    //=================================桥接词函数================================//
	static String queryBridgeWords(String word1,String word2)
	{
		String bridgeWords="";
		String NoBridgeWords = "No bridge words from word1 to word2!";    //No bridge words from word1 to word2!
		String NoWords = "No word1 or word2 in the graph!";            //No word1 or word2 in the graph!
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
					return NoBridgeWords;
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
				return NoBridgeWords;
			}
		}		
		else
		{
			return NoWords;
		}
		
		return bridgeWords;
	}
		
	//===================================输出要写的新文本===========================================//
	static String generateNewText(String inputText)
	{
		String[] newLineArray = inputText.split("\\s+");
		String LineBack = newLineArray[0];
		for(int i =0;i<newLineArray.length - 1;i++)
		{
			String tempLine = queryBridgeWords(newLineArray[i],newLineArray[i+1]);
			if(tempLine.equals("No word1 or word2 in the graph!"))
			{
				LineBack = LineBack + " " + newLineArray[i+1];
			}
			else if(tempLine.equals("No bridge words from word1 to word2!"))
			{
				LineBack = LineBack + " " + newLineArray[i+1];
			}
			else
			{
				String[] aa = tempLine.split("\\s+");
				if(aa.length == 1)
				{
				    LineBack = LineBack + " " + aa[0] + " " + newLineArray[i+1];
				}
				else
				{
					LineBack = LineBack + " " + aa[(int)(Math.random()*(aa.length))] + " "+ newLineArray[i+1];
				}
			}
				
				
			
		}
		return LineBack;
	}
	
	
	//=========================================================最短路径========================================/
	static String calcShortestPath(String startNode, String endNode)
	{
		HashSet<String> outNode = new HashSet<String>();
		LinkedList<String> nextNode = new LinkedList<String>();
		LinkedList<String> resultStep = new LinkedList<String>();
		//起点到各节点的距离
		HashMap<String,preNode> nodeStep = new HashMap<String,preNode>();
		if((!headNode.containsKey(startNode)) || (!headNode.containsKey(endNode)))
		{
			return "No this Word!";
		}
		if(startNode.equals(endNode))
		{
			return "The distance is zero!";
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
		   return "Can not reach it!";
	   }
	   if(!nodeStep.containsKey(endNode))
	   {
		   return "Can not reach it!";
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
	
	
	 //========================================随机游走========================//
	static String randomWalk()
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
		 return overWords;
	}
}

//辅助类，用来表示图的下一个节点以及边上的权值//


class Edge
{
	HashMap<String,Integer> EdgeNode = new HashMap<String,Integer>();
	Edge(String theNode,Integer number){
		this.EdgeNode.put(theNode, number);
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

}
//fufeng1

