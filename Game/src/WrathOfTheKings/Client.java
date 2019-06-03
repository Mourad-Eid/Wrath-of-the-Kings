package WrathOfTheKings;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client implements Runnable
{

	Socket S ;
	String MSG_Send ;
	String MSG_Receive;
	InputStream IS ;
	BufferedReader BR;
	OutputStream OS;
	PrintWriter PW;
	String Team;
	public Client() throws IOException, InterruptedException
	{
		S = new Socket("127.0.0.1",5000);
		IS = S.getInputStream();
		OS = S.getOutputStream();
		BR = new BufferedReader(new InputStreamReader(IS));
		PW = new PrintWriter(OS,true);
		Team = Receive();
//		JOptionPane.showMessageDialog(null, Receive(),
//				"Welcome ! ", JOptionPane.PLAIN_MESSAGE);

	}
	public void Send(String MSG) 
	{
		if(S.isConnected()) 
		{
			PW.println(MSG);
			PW.flush();	
		}
	}
	public String Receive() throws IOException, InterruptedException 
	{
		if(S.isConnected()) 
			return BR.readLine();	
		return null;
	}
	@Override
	public void run()
	{
		
		while(true)
		{
			try 
			{
				if(BR.ready())
				{
					//Receive();
					System.out.println("Server Says: " + Receive());
					Thread.sleep(1000);

				}
			}
			catch (IOException | InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


}
