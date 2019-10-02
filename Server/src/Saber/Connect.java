package Saber;

import java.awt.Frame;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Connect {
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private Frame frame;
	
	private int port;
	private String id;
	private Properties props;
	private String op;
	
	public Connect(Properties p, String s)
	{
		op = s;
		props = p;
	}
	
	public void run() throws IOException
	{
		boolean flag;
		
		Properties ServerProps = new Properties();
		ServerProps.load(new FileInputStream("config/Server.properties"));
		
		id = ServerProps.getProperty("ID")+props.getProperty("ID");
		port = Integer.parseInt(ServerProps.getProperty("Port"));
		
		frame = new JFrame();
		
		
		
		
		try {
			s = new Socket("127.0.0.1",port);
			flag = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag = false;
			JOptionPane.showMessageDialog(frame, "Connection Failed.", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
		if (flag) {
			out = new PrintWriter(s.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out.println(id);
			Listen lt = new Listen(in, out, props, op);
			lt.run();
		}
		
		else {
			System.exit(0);
		}
	}

}
