package GameServer;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Accepted_Client extends JFrame implements Runnable 
{

	private Socket Client_Socket;
	private InputStream Input_Stream;
	private OutputStream Output_Stream;
	private BufferedReader Message_Received_Reader;
	private PrintWriter Message_Sent_Writer;
	ArrayList<Player> Players = new ArrayList<Player>();
	HashMap<String, Player> Map = new HashMap<String,Player>();
	JLabel L;


	public Accepted_Client(Socket s) throws IOException
	{
		Client_Socket = s;
		Input_Stream = Client_Socket.getInputStream();
		Output_Stream = Client_Socket.getOutputStream();
		Message_Received_Reader = new BufferedReader(new InputStreamReader(Input_Stream));
		Message_Sent_Writer =  new PrintWriter(Output_Stream,true);

		//Window
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		FillPlayerList();

		//		J.setVisible(true);
		//		JOptionPane.showMessageDialog(null, "Client Says: " + Receive(),
		//				"Message Received ! ", JOptionPane.INFORMATION_MESSAGE);
		//
		//

	}

	public String Receive() throws IOException, InterruptedException 
	{
		if(Client_Socket.isConnected())
		{
			return Message_Received_Reader.readLine();	
		}
		return null;
	}

	public void Send(String MSG) 
	{
		if(Client_Socket.isConnected()) 
		{
			Message_Sent_Writer.println(MSG);
			Message_Sent_Writer.flush();	
		}
	}


	public void Check_Message(String Message) throws ClassNotFoundException, IOException, InterruptedException
	{
//		JOptionPane.showMessageDialog(null, "Client Says: " + Message,
//				"Message Received ! ", JOptionPane.INFORMATION_MESSAGE);
		System.out.println(Message);
		String [] Message_Array = Message.split("#");
		for(int i=0; i<Message_Array.length; i++)
		{
			if(Message_Array[0].contains("New"))
			{
				Send_Player_List();
				break;
			}
			else if(Message_Array[0].equals("Player"))
			{
				Player P = new Player();
				P.setName(Message_Array[1]);
				P.setPassword(Message_Array[2]);
				P.setLevel(Integer.parseInt(Message_Array[3]));
				P.setMatchesPlayed(Integer.parseInt(Message_Array[4]));
				P.setMatchesWon(Integer.parseInt(Message_Array[5]));
				P.setMatchesLost(Integer.parseInt(Message_Array[6]));
				AddPlayer(P);
				break;
			}
			else if (Message_Array[0].equals("Existing"))
			{
				Send_Player_List();
				String Name = Receive();
				if(Map.containsKey(Name))
				{
					Player P1 = Map.get(Name);
				Thread.sleep(50);
				Send("Player#" + P1.getName() + "#" + P1.getPassword() + "#" + P1.getLevel() + "#" +
						P1.getMatchesPlayed() + "#" + P1.getMatchesWon() + "#" + P1.getMatchesLost());
				}
				break;
			}
			else
			{
				System.out.println("hohoho aa esht8lt xD");
				if(Map.containsKey(Message_Array[0]))
				{
					Player P1 = Map.get(Message_Array[0]);
					
				Send("Player#" + P1.getName() + "#" + P1.getPassword() + "#" + P1.getLevel() + "#" +
						P1.getMatchesPlayed() + "#" + P1.getMatchesWon() + "#" + P1.getMatchesLost());
				}
				break;
			}

		}
	}

	public void AddPlayer(Player P) throws FileNotFoundException, IOException
	{

		FileOutputStream f = new FileOutputStream(new File("Players.bin"));
		ObjectOutputStream o = new ObjectOutputStream(f);
		Players.add(P);
		Map.putIfAbsent(P.getName(), P);
		for(int i=0; i<Players.size(); i++)
		{
			o.writeObject(Players.get(i));
		}
		for(int i=0; i<Players.size(); i++)
		{
			System.out.println("Player's Name: " + Players.get(i).getName());
		}
		o.close();
		f.close();
	}

	public void FillPlayerList() 
	{
		ObjectInputStream Inp;
		try 
		{

			FileInputStream fi = new FileInputStream(new File("Players.bin"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			Player P = new Player();

			while(true)
			{
				try
				{
					P = (Player)oi.readObject();
					Players.add(P);
					Map.putIfAbsent(P.getName(), P);
					P = new Player();

				} 
				catch (EOFException e) 
				{
					break;
				}
			}



			oi.close();
			fi.close();


		}
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e)
		{	
			e.printStackTrace();
		}
	}


	public void Send_Player_List() throws IOException, ClassNotFoundException, InterruptedException
	{

		String All_Players = "";
		for(int i=0; i<Players.size(); i++)
		{
			All_Players+=Players.get(i).getName() + "#";
		}
		Send(All_Players);
	}

	@Override
	public void run() 
	{
		while(true)
		{
			try
			{
				if(Message_Received_Reader.ready())
				{
					Thread.sleep(100);
					String Message = Receive();
					L = new JLabel(Message);
					L.setBounds(10, 10, 50, 50);
					add(L);
					repaint();
					revalidate();
					Check_Message(Message);


				}
			} 
			catch (IOException | InterruptedException | ClassNotFoundException e) 
			{
				e.printStackTrace();
			}
		}

	}
}

