package WrathOfTheKings;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.HashMap;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javafx.*;

public class Login extends JFrame implements Serializable, ActionListener
{
	static String soundtrack = "Login-Itachi Theme.wav";
	JLabel Title;
	JLabel Password;
	JButton NewPlayer;
	JButton ExistingPlayer;
	public ArrayList<Player> Players = new ArrayList<Player>();
//	HashMap<String, Player> Map = new HashMap<String,Player>();
	Client C;
	
	public Login(Client C) throws HeadlessException, IOException, InterruptedException
	{
		//Client Part 
				this.C = C;
				//Window Size
				GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
				setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
				setLayout(null);
				setDefaultCloseOperation(EXIT_ON_CLOSE);
				ImageIcon imageIcon = new ImageIcon(new ImageIcon("Wok2.jpg")
						.getImage().getScaledInstance(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height
								, Image.SCALE_DEFAULT));
				JLabel BG = new JLabel();
				BG.setIcon(imageIcon);
				BG.setBounds(0,0,env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
				setContentPane(BG);

//				JLabel BG = new JLabel(new ImageIcon("Wok2.jpg"));
//				BG.setBounds(0,0,1552, 873);
//				setContentPane(BG);
				setVisible(true);

				
				//Title
				JLabel Title = new JLabel("Welcome To The Wrath of The Kings");
				Title.setFont(new Font("Algerian",Font.BOLD, 52));
				Title.setBounds((int)(0.11*getWidth()), (int)(0.03*getHeight()), (int)(0.88*getWidth()), (int)(0.26*getHeight()));
				Title.setForeground(Color.ORANGE);
				add(Title);

				//New Player Button
				NewPlayer = new JButton("New Player");
				NewPlayer.setFont(new Font("Algerian", Font.BOLD, 24));
				NewPlayer.setBounds((int)(0.18*getWidth()), (int)(0.39*getHeight()), (int)(0.26*getWidth()), (int)(0.2*getHeight()));
				NewPlayer.addActionListener(this);
				add(NewPlayer);
				
				
				//Existing Player Button
				ExistingPlayer = new JButton("Existing PLayer");
				ExistingPlayer.setFont(new Font("Algerian", Font.BOLD, 24));
				ExistingPlayer.setBounds((int)(0.55*getWidth()), (int)(0.39*getHeight()), (int)(0.26*getWidth()), (int)(0.2*getHeight()));
				ExistingPlayer.addActionListener(this);
				add(ExistingPlayer);
				
				sound(soundtrack);
				//FillPlayerList();
	}
	
	public void sound(String path)
	{

	    try
	    {
	        AudioInputStream audio = AudioSystem.getAudioInputStream(Menu.class.getResource(path));
	        Clip clip = AudioSystem.getClip();
	        clip.open(audio);
	        clip.start();
	    }
	    catch (Exception e)
	    {
	        System.out.println("check "+path+"\n");
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == NewPlayer)
		{
			setVisible(false);
			C.Send("New");
			System.out.println("Sending New");
			String[] Players_Names;
			try
			{
				Players_Names = C.Receive().split("#");
				Register R = new Register(this, Players_Names, C);

			}
			catch (IOException | InterruptedException e1)
			{
				e1.printStackTrace();
			}
//			
		}
		else if(e.getSource() == ExistingPlayer)
		{
			setVisible(false);
			C.Send("Existing");
			System.out.println("Sending Existing");
			String[] Players_Names;
			try
			{
				Players_Names = C.Receive().split("#");
				Existing R2 = new Existing(this, Players_Names, C);

			} 
			catch (IOException | InterruptedException e1)
			{
				e1.printStackTrace();
			}

		}
	}

}
