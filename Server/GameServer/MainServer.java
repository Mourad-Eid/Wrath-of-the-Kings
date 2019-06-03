package GameServer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainServer
{

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException
	{
//		FileOutputStream f = new FileOutputStream(new File("Players.bin"));
//		ObjectOutputStream o = new ObjectOutputStream(f);
//		ArrayList<Player> Players = new ArrayList<Player>();
//
//		Player P = new Player();
//		P.setName("Caroline");
//		P.setPassword("asd");
//		Players.add(P);
//		Player P2 = new Player();
//		P2.setName("Mary");
//		Players.add(P2);
//		Player P3 = new Player();
//		P3.setName("Alfred");
//		Players.add(P3);
//		for(int i=0; i<Players.size(); i++)
//		{
//			o.writeObject(Players.get(i));
//		}
//		o.close();
//		f.close();
		
		Server AC = new Server();
		Thread T = new Thread(AC);
		T.start();
	}

}
