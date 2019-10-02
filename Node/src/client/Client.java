package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


import main.PoolHandler;
import server.Server;

public class Client extends Thread{
	
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	
	private int port;
	
	private boolean flag;
	
	private Server server;
	
	private PoolHandler brain;
	
	public Client (Server srv)
	{
		server = srv;
		brain = server.getBrain();
		port = server.getPort();
	}
	
	public void run()
	{
		
		
		synchronized (this) {
			try {
				s = new Socket("127.0.0.1", port);
				flag = false;
				server.hasEnded(flag);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				flag = true;
				server.hasEnded(flag);
			}
			notify();
		}
		if (!flag)
		{
			try {
				out = new PrintWriter(s.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			brain.addSocket(s, out, in);
		}
	}
	
	
	
	

}
