package lab7;

import java.util.Scanner;

public class Boundary {
	private String word1;
	private String word2;
	private String NewLine;
	private String startNode;
	private String endNode;
	int function;
	Boundary() {
		System.out.println(" 2: ��������ͼ           3 �� �ŽӴ�                  4 : ���ı�                          5�����·��                      6���������");
		System.out.println("please input the number where can come true : ");
	    Scanner oner = new Scanner(System.in);
	    function = oner.nextInt();	
	}
	
	public int getfunction() {
		return function;
	}
	
	public void setbridgeWord() {
		System.out.println("������Ҫ�����ŽӴʵ������ַ�����  ");
		Scanner s = new Scanner(System.in);
		word1 = s.next();
		word2 = s.next();
	}
	
	public String getbridgeWord1() {
		return word1;
	}
	public String getbridgeWord2() {
		return word2;
	}
	public void showBridge1() {
		System.out.println("No bridge words from word1 to word2!");
	}
	public void showBridge2() {
		System.out.println("No word1 or word2 in the graph!");
	}
	public void showBridge3(String temp) {
		System.out.println(temp);
	}
	public void setNewtext() {
		System.out.print("���������ı� �� ");
		Scanner st = new Scanner(System.in);
		NewLine = st.nextLine();
		NewLine = NewLine.toLowerCase().trim();
	}
	public String getNewtext() {
		return NewLine;
	}
	public void showNewtext(String b) {
		System.out.println(b.trim());
	}
	public void setMinD() {
		System.out.println("������Ҫ������̾���ĵ��ʣ� ");
		Scanner sn = new Scanner(System.in);
		startNode = sn.next();
	    endNode = sn.next();
	}
	public String getstartNode() {
		return startNode;
	}
	public String getendNode() {
		return endNode;
	}
	public void showMinstep(String min) {
		System.out.println(min);
	}
	

}
