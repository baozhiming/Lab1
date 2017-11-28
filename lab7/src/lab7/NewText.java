package lab7;

import java.util.HashMap;

public class NewText {
	private static BridgeWord s = new BridgeWord();
	private static String LineBack = "";
	static void generateNewText(String inputText,HashMap<String,Edge> headNode)
	{
		String[] newLineArray = inputText.split("\\s+");
		for(int i =0;i<newLineArray.length - 1;i++)
		{
			
			
			s.queryBridgeWords(newLineArray[i],newLineArray[i+1],headNode);
			String tempLine = s.getresultWords();
			if(tempLine.equals("+2"))
			{
				LineBack = LineBack + " " + newLineArray[i+1];
			}
			else if(tempLine.equals("+1"))
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
	}
	public static String getLineBack() {
		return LineBack;
	}
}
