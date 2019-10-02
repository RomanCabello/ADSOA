package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class Listener extends Thread {

	private PrintWriter out;
	private BufferedReader in;
	private List<PrintWriter> all = new ArrayList<PrintWriter>();
	private PoolHandler brain;
	private String msg;
	public boolean flag;
	private String status;
	
	private String client;
	private String server;
	private String node;
	private String min;
	private String sum;
	private String div;
	private String mul;
	
	private String to;
	
	private int index;
	
	public Listener(PrintWriter pw, BufferedReader bf, PoolHandler ph, int i) {
		out = pw;
		in = bf;
		brain = ph;
		index = i;
		
	}

	public void run() {
		
		String sub;
		
		loader();
		
		flag = false;
		
		try {
			handshake(node);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {
			try {

				msg = in.readLine();
				System.out.println("Got: "+msg);
				int i = msg.indexOf("|");
				i++;
				int j = msg.lastIndexOf("|");
				j++;
				to = msg.substring(j);
				j--;
				sub = msg.substring(i, j);
				
				if(msg.endsWith("kill"))
				{
					System.out.println("I don't wanna go Mr.Stark");
					brain.terminate(msg);
					if(msg.startsWith(client))
					{
						brain.broadcast(msg, node);
					}
					System.exit(0);
				}
				
				
				if(msg.startsWith(node))
				{
					msg = "0"+msg;
					if(msg.endsWith(server))
					{
						if(sub.indexOf('-')!=-1)
						{
							msg = msg+min;
						}
						if(sub.indexOf('+')!=-1)
						{
							msg = msg+sum;
						}
						if(sub.indexOf('/')!=-1)
						{
							msg = msg+div;
						}
						if(sub.indexOf('*')!=-1)
						{
							msg = msg+mul;
						}
						to = msg.substring(msg.length()-2);
						brain.broadcast(msg, to);
					} else {
						brain.broadcast(msg, to);
					}
					
				}
				else {
					if(msg.startsWith(client))
						{
							flag = true;
						}
					msg = "0"+msg;
					brain.broadcast(msg, node);
					
					if (flag)
					{
						if(sub.indexOf('-')!=-1)
						{
							msg = msg+min;
						}
						if(sub.indexOf('+')!=-1)
						{
							msg = msg+sum;
						}
						if(sub.indexOf('/')!=-1)
						{
							msg = msg+div;
						}
						if(sub.indexOf('*')!=-1)
						{
							msg = msg+mul;
						}
						to = msg.substring(msg.length()-2);
						brain.broadcast(msg, to);
					}
					else {
						brain.broadcast(msg, to);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
			}
		}

	}

	public void broadcast() {
		all = brain.getAll();
		Iterator<PrintWriter> it = all.iterator();

		while (it.hasNext()) {
			out = it.next();
			out.println("msg");

		}
	}

	public void handshake(String node) throws IOException {
		out.println(node);
		status= in.readLine();
		brain.mapTo(index, status);
		
	}
	
	public void loader()
	{
		
		Properties props = new Properties();
		
		try {
			props.load(new FileInputStream("config/Node.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		node = props.getProperty("NODE");
		client = props.getProperty("CLIENT");
		server = props.getProperty("SERVER");
		
		min = props.getProperty("Smin");
		sum = props.getProperty("Ssum");
		div = props.getProperty("Sdiv");
		mul = props.getProperty("Smul");
		
		
	}

}
