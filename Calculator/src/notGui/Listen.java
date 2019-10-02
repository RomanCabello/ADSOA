package notGui;

import java.io.BufferedReader;
import java.io.IOException;

public class Listen extends Thread{
	
	private Connect connect;
	private BufferedReader in;
	
	public Listen(Connect cn, BufferedReader br)
	{
		connect = cn;
		in = br;
	}
	
	public void run()
	{
		String msg = "";
		String sub;
		try {
			msg = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true)
		{
			try {
				msg = in.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				
				
			}
			
			int i = msg.indexOf('|');
			i++;
			int j = msg.lastIndexOf('|');
			sub = msg.substring(i, j);
			connect.response(sub);
		}
	}

}
