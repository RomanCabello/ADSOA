package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class Clear implements ActionListener{
	
	private JTextField text;
	
	public Clear(JTextField tf)
	{
		text = tf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s = text.getText();
		String s1 = s.substring(0, s.length()-1);
		text.setText(s1);
	}

}
