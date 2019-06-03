package GameServer;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Server implements Runnable
{
	ServerSocket Server_Socket;
	OutputStream OS_Server;
	InputStream IS_Server;
	PrintWriter PW_Server;
	BufferedReader BR_Server;
	Socket ClientSocket;

	public Server() throws IOException, ClassNotFoundException, InterruptedException
	{
		System.out.println("Server Initiated !");
		Server_Socket = new ServerSocket(5000);
		ClientSocket = new Socket();
		
	}


	@Override
	public void run()
	{
		try
		{
			ClientSocket = Server_Socket.accept();
			System.out.println("Client Connected " + ClientSocket.toString());
			OS_Server = ClientSocket.getOutputStream();
			IS_Server = ClientSocket.getInputStream();
			PW_Server = new PrintWriter(OS_Server,true);
			PW_Server.println("Blue");
			BR_Server = new BufferedReader(new InputStreamReader(IS_Server));
			
			Accepted_Client	A = new Accepted_Client(ClientSocket);
			Thread T = new Thread(A);
			T.start();
			

		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
}
