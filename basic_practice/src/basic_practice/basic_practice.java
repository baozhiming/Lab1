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


public class basic_practice {


public class Basic_practice{

    //瀛樺偍缁撶偣 浣跨敤鍝堝笇琛ㄥ瓨鍌ㄥ浘鐨勭粨鏋�//
    static HashMap<String,Edge> headNode = new HashMap<String,Edge>();

    public static void main(String[] args) throws IOException{
        /*****************璇诲彇鏂囨湰鍐呭*************************/
        File file = new File("F:\\baoming.txt");
        FileInputStream fis = new FileInputStream(file);
        BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
        String str;
        /**********鏂囨湰鍐呭***********************************/
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
        System.out.println("---------------琛屽瓧绗︿覆------------------------");
        System.out.println(line);

        /************************灏嗘枃浠朵腑鐨勫唴瀹硅鍏rrayList鏁扮粍涓�*******************/
        String[] arrayList = line.split("\\s+") ;
        bufr.close();

        /********************************灏嗘枃鏈緭鍏ュ埌鍥句腑*********************************/
        for(int i=0;i<arrayList.length-1;i++)
        {
            if(!headNode.containsKey(arrayList[i]))
            {
                 Edge tempNode = new Edge(arrayList[i+1],1) ;
                headNode.put(arrayList[i],tempNode);
            }
            else
            {
                if(!headNode.get(arrayList[i]).edgeNode.containsKey(arrayList[i+1]))
                {
                    headNode.get(arrayList[i]).edgeNode.put(arrayList[i+1], 1);
                }
                else
                {
                    Integer nowEdge =  headNode.get(arrayList[i]).edgeNode.get(arrayList[i+1]);
                    headNode.get(arrayList[i]).edgeNode.put(arrayList[i+1], nowEdge+1);
                }
            }

        }
        headNode.put(arrayList[arrayList.length-1],null);

        /**************************灏唄ashMap杈撳嚭妫�鏌ユ槸鍚︽纭�*****************/
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
        /****************************杈撳嚭鏈夊悜鍥�***********************************/
        showDirectedGraph();

        System.out.println("3 锛� 妗ユ帴璇�                  4 : 鏂版枃鏈�                          5锛氭渶鐭矾寰�                      6锛氶殢鏈烘父璧�");
        boolean orcontinue = true;
        while(orcontinue == true)
        {
            System.out.print("please input the number where can come true : ");
            Scanner oner = new Scanner(System.in);
            int oner = oner.nextInt();
            switch(oner)
            {
            case 3:
                /****************************妗ユ帴璇�***************************************/
                System.out.println("璇疯緭鍏ヨ鏌ユ壘妗ユ帴璇嶇殑涓や釜瀛楃涓诧細  ");
                Scanner s = new Scanner(System.in);
                String word1 = s.next();
                String word2 = s.next();
                String result = queryBridgeWords(word1, word2);
                System.out.println(result.trim());
                break;
            case 4:
                /*****************************鐢熸垚鏂版枃鏈�**********************************/
                System.out.print("璇疯緭鍏ユ柊鏂囨湰 锛� ");
                Scanner st = new Scanner(System.in);
                String newLine = st.nextLine();
                newLine = newLine.toLowerCase().trim();
                System.out.println(generateNewText(newLine).trim());
                break;
            case 5:
                /*****************************鏈�鐭窛绂�************************************/
                System.out.println("璇疯緭鍏ヨ璁＄畻鏈�鐭窛绂荤殑鍗曡瘝锛� ");
                Scanner sn = new Scanner(System.in);
                String startNode = sn.next();
                String endNode = sn.next();
                System.out.println(calcShortestPath(startNode,endNode));
                break;
            case 6:
                /*****************************闅忔満娓歌蛋************************************/
                System.out.println(randomWalk());
                break;
            }
        }


    }

    //=======灞曠ず鏈夊悜鍥�==========//
    static void showDirectedGraph()
    {
        GraphViz gv = new GraphViz();
        gv.addln(gv.startgraph());
        Iterator<String> iterator = headNode.keySet().iterator();

        while(iterator.hasNext())
        {
            String key = iterator.next();
            if(headNode.get(key) != null)
            {
                Iterator<String> iterator1 = headNode.get(key).edgeNode.keySet().iterator();
                while(iterator1.hasNext())
                {
                    String key1 = iterator1.next();
                    Integer a = headNode.get(key).edgeNode.get(key1);
                    gv.addln(key + "->" + key1 + "[label = " + a + "]");
                }
            }
        }
        gv.addln(gv.endgraph());
        System.out.println(gv.getDotSource());
        String type = "gif";
        File out = new File("F:\\ming\\out1." + type);    // Windows
        gv.writeGraphToFile( gv.getGraph( gv.getDotSource(), type ), out );
    }

    //=================================妗ユ帴璇嶅嚱鏁�================================//
    static String queryBridgeWords(String word1,String word2)
    {
        String bridgeWords="";
        String noBridgeWords = "No bridge words from word1 to word2!";    //No bridge words from word1 to word2!
        String noWords = "No word1 or word2 in the graph!";            //No word1 or word2 in the graph!
        int number = 0;
        final boolean contains1 = headNode.containsKey(word1);
        final boolean contains2 = headNode.containsKey(word2);
        if(contains1 && contains2)
        {
            Iterator<String> iterator = headNode.get(word1).edgeNode.keySet().iterator();
            while(iterator.hasNext())
            {
                String key = iterator.next();
                if(key == word2)
                {
                    return noBridgeWords;
                }
                else
                {
                    if(headNode.get(key) != null)
                    {
                        boolean containKey = headNode.get(key).edgeNode.containsKey(word2);
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
                return noBridgeWords;
            }
        }
        else
        {
            return noWords;
        }

        return bridgeWords;
    }

    //===================================杈撳嚭瑕佸啓鐨勬柊鏂囨湰===========================================//
    static String generateNewText(String inputText)
    {
        String[] newLineArray = inputText.split("\\s+");
        String lineBack = newLineArray[0];
        for(int i =0;i<newLineArray.length - 1;i++)
        {
            String tempLine = queryBridgeWords(newLineArray[i],newLineArray[i+1]);
            if(tempLine.equals("No word1 or word2 in the graph!"))
            {
                lineBack = lineBack + " " + newLineArray[i+1];
            }
            else if(tempLine.equals("No bridge words from word1 to word2!"))
            {
                lineBack = lineBack + " " + newLineArray[i+1];
            }
            else
            {
                String[] aa = tempLine.split("\\s+");
                if(aa.length == 1)
                {
                    lineBack = lineBack + " " + aa[0] + " " + newLineArray[i+1];
                }
                else
                {
                    lineBack = lineBack + " " + aa[(int)(Math.random()*(aa.length))] + " "+ newLineArray[i+1];
                }
            }



        }
        return lineBack;
    }


    //=========================================================鏈�鐭矾寰�========================================/
    static String calcShortestPath(String startNode, String endNode)
    {
        HashSet<String> outNode = new HashSet<String>();
        LinkedList<String> nextNode = new LinkedList<String>();
        LinkedList<String> resultStep = new LinkedList<String>();
        //璧风偣鍒板悇鑺傜偣鐨勮窛绂�
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
                Iterator<String> iterator = headNode.get(start).edgeNode.keySet().iterator();
                while(iterator.hasNext())
                {
                    String key = iterator.next();
                    if(key == startNode)
                    {
                        continue;
                    }
                    Integer allStep = step + headNode.get(start).edgeNode.get(key);
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
          //     nodeStep.put(start,null);
          //  }
            outNode.add(start);
        }
       /*****************************************灏嗗緱鍒扮殑nodeStep杈撳嚭鏈�鐭矾寰�***********************************/
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
       }                                                                 /******灏嗗瓨鍌ㄥソ鐨刪ashMap浠ュ弽鍚戦『搴忓皢鍏惰緭鍑哄苟瀛樺叆result瀛楃涓�*****/

       /**********灏嗘渶鐭矾寰勫瓨鏀惧湪minStep瀛楃涓蹭腑骞惰緭鍑�******/
       String minStep = resultStep.removeFirst();
       String minStepMap = minStep;
       for(Iterator iter = resultStep.iterator();iter.hasNext();)
       {
           minStep = minStep + "鈥斺��>" + iter.next();
       }
       /******鐢诲浘灏嗘渶鐭矾寰勮緭鍑�*********/
       GraphViz gv = new GraphViz();
       gv.addln(gv.start_graph());

       Iterator<String> iterator = headNode.keySet().iterator();
       while(iterator.hasNext())
       {
           String key = iterator.next();
           if(headNode.get(key) != null)
           {
               Iterator<String> iterator1 = headNode.get(key).edgeNode.keySet().iterator();
                while(iterator1.hasNext())
                {
                   String key1 = iterator1.next();
                   Integer a = headNode.get(key).edgeNode.get(key1);
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


     //========================================闅忔満娓歌蛋========================//
    static String randomWalk()
    {

        String[] keys = headNode.keySet().toArray(new String[0]);
        HashMap<Integer,String> overWord= new HashMap<Integer,String>();
        Random random = new Random();
        final String firstRandomKey = keys[random.nextInt(keys.length)];
        String randomKey = firstRandomKey;
        String overWords = firstRandomKey;
        int i = 0;
        while(headNode.get(randomKey) != null)
        {
            String[] nextKeys = headNode.get(randomKey).edgeNode.keySet().toArray(new String[0]);
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
        /*----灏嗙敓鎴愮殑闅忔満璺緞鍦ㄥ浘涓樉绀�----*/
        String[] tempArray = overWords.split("\\s+");
        GraphViz gv = new GraphViz();
        gv.addln(gv.start_graph());
        Iterator<String> iterator = headNode.keySet().iterator();
        while(iterator.hasNext())
        {
        String key = iterator.next();
        if(headNode.get(key) != null)
            {
                Iterator<String> iterator1 = headNode.get(key).edgeNode.keySet().iterator();
                while(iterator1.hasNext())
                {
                    String key1 = iterator1.next();
                    Integer a = headNode.get(key).edgeNode.get(key1);
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
//杈呭姪绫伙紝鐢ㄦ潵琛ㄧず鍥剧殑涓嬩竴涓妭鐐逛互鍙婅竟涓婄殑鏉冨��//
class Edge
{
    HashMap<String,Integer> edgeNode = new HashMap<String,Integer>();
    Edge(String theNode,Integer number){
        this.edgeNode.put(theNode, number);
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
    /*public String getpreNodeStr()
    {
        return preNodeStr;
    }*/
    public int getNodeStep()
    {
        return toNodeStep;
    }
}
