package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ClearEverything implements ActionListener{
	
	private JTextField text;
	
	public ClearEverything(JTextField tf)
	{
		text = tf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		text.setText("");
	}

}
