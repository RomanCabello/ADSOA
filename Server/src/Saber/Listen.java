package Saber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Listen extends Thread{
	private String msg;
	private BufferedReader in;
	private PrintWriter out;
	private Process process;
	private String response;
	private String op;
	private Properties props;
	
	public Listen(BufferedReader bf, PrintWriter pw, Properties p, String s)
	{
		in = bf;
		out = pw;
		op = s;
		props = p;
		process = new Process(p, op);
	}
	
	public void run() {
		try {
			msg = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true)
		{
			try {
				msg = in.readLine();
				int i = msg.lastIndexOf('|');
				i++;
				String sub = msg.substring(i);
				if (sub.startsWith("kill"))
				{
					System.exit(0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
			try {
				response = process.run(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println(response);
		}
		
		
	}

}
