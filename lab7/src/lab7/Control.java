package lab7;

import java.io.IOException;
import java.util.HashMap;

public class Control {
	private showMap showmap;
	private BridgeWord bridgeWord;
	private NewText newtext;
	private calshortDistance calshort;
	private RandWalk randWalk;
	Control() throws IOException {
		showmap = new showMap();
		showmap.saveHashmap();
	}
	public void functionClass(Boundary a) {
		switch(a.getfunction()) {
		case 2:
			showmap.showDirectedGraph();
			break;
		case 3:
			a.setbridgeWord();
			String word1 = a.getbridgeWord1();
			String word2 = a.getbridgeWord2();
			bridgeWord.queryBridgeWords(word1, word2,showmap.getHashmap());
			String temp = bridgeWord.getresultWords();
			if(temp == "+1") {
				a.showBridge1();
			}
			else if(temp == "+2") {
				a.showBridge2();
			}
			else {
				a.showBridge3(temp);
			}
			break;
		case 4:
			a.setNewtext();
			newtext.generateNewText(a.getNewtext(),showmap.getHashmap());
			a.showNewtext(newtext.getLineBack()); 
			break;
		case 5:
			a.setMinD();
			String startNode = a.getstartNode();
			String endNode = a.getendNode();
			calshort.calcShortestPath(startNode,endNode,showmap.getHashmap());
			a.showMinstep(calshort.getMinstep());
			break;
		case 6:
			randWalk.randomWalk();
			break;
			
		}
	}


}
