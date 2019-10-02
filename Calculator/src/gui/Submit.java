package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JTextField;

import notGui.Connect;

public class Submit implements ActionListener{
	
	private JTextField text;
	private String msg;
	private Connect connect;
	private String id;
	
	
	public Submit(JTextField tf, Connect cn)
	{
		text = tf;
		connect = cn;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String s = text.getText();
		
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("config/Calculator.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		String to;
		id = connect.getID();
		to = props.getProperty("Server");
		
		msg = id+"|"+s+"|"+to;
		
		connect.send(msg);
		
	}
	
	public String getMsg() {
		return msg;
	}
	

}
