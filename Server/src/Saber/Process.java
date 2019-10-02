package Saber;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class Process {
	private String msg;
	private String res;
	private Properties props;
	private String result;
	private BufferedReader in;
	private java.lang.Process pro;
	private String op;
	
	public Process(Properties p, String s)
	{
		props = p;
		op = s;
	}
	
	public String run(String st) throws IOException, InterruptedException
	{
		msg = st;
		
		String route = props.getProperty("Route");
		
		Properties serv = new Properties();
		serv.load(new FileInputStream("config/Server.properties"));
		String id = serv.getProperty("ID");
		
		int i = msg.indexOf('|');
		i++;
		int j = msg.indexOf("|", i);
		
		int z = msg.indexOf(op, i);
		
		int num1 = Integer.parseInt(msg.substring(i, z));
		int num2 = Integer.parseInt(msg.substring(z+1, j));
		
		
		
		pro = Runtime.getRuntime().exec(route+num1+" "+num2);
		pro.waitFor();
		in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
        res = in.readLine();
		
		String sender;
		
		i = msg.indexOf("1");
		j = msg.indexOf("|");
		
		sender = msg.substring(i, j);
		
		result = id+"|"+res+"|"+sender;
		
		return result;
		
	}

}
