package notGui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Connect implements ActionListener {
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private Frame frame;
	private JButton button;
	private JButton button2;
	private JButton button3;
	private JTextField text;
	private String id;
	
	public Connect(Frame frame2, JButton b, JButton b2, JButton b3,JTextField tf) {
		frame = frame2;
		button = b;
		button2 = b2;
		button3 = b3;
		text = tf;

	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String time = ""+timestamp;

		boolean flag = false;

		button.setText("Connecting");
		
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config/Calculator.properties"));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		int port = Integer.parseInt(props.getProperty("Port"));
		
		
		id = props.getProperty("ID")+time;
		
			try {
				s = new Socket("127.0.0.1", port);
				flag = true;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(frame, "Connection Failed.", "Error", JOptionPane.ERROR_MESSAGE);
				flag = false;
				button.setText("Connect");
			}
		

		if (flag) {
			button.setEnabled(false);
			button.setText("Connected");
			button2.setEnabled(true);
			button3.setEnabled(true);
			try {
				out = new PrintWriter(s.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
			out.println(id);
			new Listen(this, in).start();
			
		}
		
		

	}
	
	public void send(String msg)
	{
		out.println(msg);
	}
	
	public void response(String msg)
	{
		String sub = msg;
		text.setText(sub);
	}
	public String getID()
	{
		return id;
		
	}

}
