package lab7;

import java.io.IOException;

public class Lab7 {
	public static  void main(String[] args) throws IOException {
		Control control = new Control();
		while(true) { 
			Boundary boundary = new Boundary();
			control.functionClass(boundary);
		}
		
	}
	

}
