package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import client.Client;
import main.PoolHandler;

public class Server extends Thread{
	private ServerSocket Servant;
	private int port;
	private boolean taken;
	
	private List<Client> clients = new ArrayList<Client>();
	
	private List<Socket> Contracts = new ArrayList<Socket>();
	private List<PrintWriter> out = new ArrayList<PrintWriter>();
	private List<BufferedReader> in = new ArrayList<BufferedReader>();
	
	private PoolHandler brain;
	

	private boolean end = true;
	
	public Server(PoolHandler ph)
	{
		brain = ph;
	}
	
	public void run()
	{
		Properties prop = new Properties();
		
		try {
			prop.load(new FileInputStream("config/Node.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		port = Integer.parseInt(prop.getProperty("SPORT"));
		
		taken = true;
		
		while (taken) {
			try {
				Servant = new ServerSocket(port);
				taken = false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				taken = true;
				port++;
			} 
		}
		
		int count = 0;
		int limit = port;
		
		for(int x = 1; x < limit; x++)
		{
			port = x;
			clients.add(new Client(this));
			synchronized (clients.get(count)) {
				try {
					clients.get(count).start();
					clients.get(count).wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			count++;
			
		}
		int random = (int)(Math.random() * 2+1);
		
		switch (random)
		{
			case 1:
			{
				System.out.println("10000 years will give you such a crick in the neck");
				break;
			}
			case 2:
				System.out.println("I LIIIIIVE!!");
				break;
		}
		
		if(count == 0)
		{
			System.out.println("FIRST");
		}else {
			System.out.println("Conected to "+count+" other nodes");
		}
		
		
		int i = 0;
		int j = 0;
		
		while(true)
		{
			j = i;
			
			try {
				
				Contracts.add(Servant.accept());
				out.add(new PrintWriter(Contracts.get(i).getOutputStream(), true));
				in.add(new BufferedReader(new InputStreamReader(Contracts.get(i).getInputStream())));
				
				brain.addSocket(Contracts.get(i),out.get(i), in.get(i));

				i++;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				i = j;
			}
			
			
		}
		
		
	}
	
	public int getPort()
	{
		return port;
	}
	
	public void hasEnded(boolean bl)
	{
		end = bl;
	}
	
	public PoolHandler getBrain()
	{
		return brain;
	}
	
	
	
}
