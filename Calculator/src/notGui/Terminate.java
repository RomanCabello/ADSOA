package notGui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;

public class Terminate implements ActionListener{
	private Connect connect;
	private JButton button;
	private JButton button2;
	
	public Terminate(Connect ct, JButton bt, JButton bt2)
	{
		connect = ct;
		button = bt;
		button2 = bt2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config/Calculator.properties"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String id = prop.getProperty("ID");
		
		String msg = id+"|0|kill";
		connect.send(msg);
		button.setEnabled(false);
		button2.setEnabled(true);
	}

}
