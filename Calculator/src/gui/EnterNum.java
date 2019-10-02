package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class EnterNum implements ActionListener{
	
	private JTextField text;
	
	public EnterNum(JTextField tf)
	{
		text = tf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s = e.getActionCommand();
		String s1 = text.getText();
		String s2 = s1+s;
		
		text.setText(s2);
		
	}

}
