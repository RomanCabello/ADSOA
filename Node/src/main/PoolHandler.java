package main;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PoolHandler {
	private List<Socket> Contracts = new ArrayList<Socket>();
	private List<PrintWriter> out = new ArrayList<PrintWriter>();
	private List<BufferedReader> in = new ArrayList<BufferedReader>();
	private List<Listener> channels = new ArrayList<Listener>();
	
	private List<String> map = new ArrayList<String>();
	
	
	private int count = 0;
	
	
	public void addSocket(Socket s, PrintWriter pw, BufferedReader bf)
	{
		Contracts.add(s);
		out.add(pw);
		in.add(bf);
		new Listener(pw, bf, this,count).start();
		map.add("");
		count++;
	}
	
	public List<PrintWriter> getAll()
	{
		return out;
	}
	
	public void mapTo(int index, String value)
	{
		map.set(index, value);
	}
	
	public void broadcast(String msg, String to)
	{	
		for (int x = 0; x < map.size(); x++) {
			
			if(map.get(x).equals(to))
			{
				System.out.println("Sending: "+msg+" to "+map.get(x));
				out.get(x).println(msg);
			}
		}
	}
	
	
	public void terminate(String msg)
	{	
		for (int x = 0; x < map.size(); x++) {
			
			if(map.get(x).equals("21")||map.get(x).equals("22")||map.get(x).equals("23")||map.get(x).equals("24"))
			{
				out.get(x).println(msg);
			}
		}
	}
}
