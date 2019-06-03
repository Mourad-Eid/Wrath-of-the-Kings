package WrathOfTheKings;

import java.io.FileNotFoundException;
import java.io.IOException;

import Game.Battle_Field_Test;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException
	{
		Client C = new Client();
		Thread T = new Thread(C);
		T.start();
		Login L = new Login(C);
		
//		Player P = new Player();
//		P.setName("Caroline");
//		L.AddPlayer(P);
//		Player P2 = new Player();
//		P2.setName("Mary");
//		L.AddPlayer(P2);
//		Player P3 = new Player();
//		P3.setName("Alfred");
//		L.AddPlayer(P3);
//		P = new Player();
//		P.setName("Ay 7ad");
//		L.AddPlayer(P);
//		L.FillPlayerList();
//		Thread T = new Thread(L);
//		System.out.println("I'm Finished !");
		//T.start();
		
		//Battle_Field_Test BF = new Battle_Field_Test();
	}

}
