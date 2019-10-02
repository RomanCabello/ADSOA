package Saber;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
	
	private static String op;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		op = args[0];
		
		Properties loadProps = new Properties();
	    
		switch (op)
		{
		case "+":
			loadProps.load(new FileInputStream("config/Sum.properties"));
			break;
			
		case "-":
			loadProps.load(new FileInputStream("config/Minus.properties"));
			break;
			
		case "*":
			loadProps.load(new FileInputStream("config/Multiply.properties"));
			break;
			
		case "/":
			loadProps.load(new FileInputStream("config/Divide.properties"));
			break;
			
			default:
				System.out.println("New phone who dis?");
				System.exit(0);
		}
		
		Connect connect = new Connect(loadProps, op);
		
		connect.run();
	}

}
