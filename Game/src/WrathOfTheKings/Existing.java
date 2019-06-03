package WrathOfTheKings;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import Game.Battle_Field_Test;

public class Existing extends JFrame implements ActionListener, Search
{
	//Components
	JLabel Name_Label = new JLabel("Enter Name:");
	JTextField Name_TextField = new JTextField(20);
	JLabel Password_Label = new JLabel("Enter Password: ");
	JTextField Password_TextField = new JPasswordField(20);	
	JButton Login_Button = new JButton("Login");
	JButton Cancel_Button = new JButton("Cancel");
	Login login;
	String [] Players_Names;
	Client C;
	String Team;

	public Existing(Login L, String[] Players_Names, Client c)
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		login=L;
		C = c;
		this.Team = C.Team;
		this.Players_Names = Players_Names;

		//Form Dimensions: 
		setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Register2.png")
				.getImage().getScaledInstance(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height
						, Image.SCALE_DEFAULT));
		JLabel BG = new JLabel();
		BG.setIcon(imageIcon);
		BG.setBounds(0,0,env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
		//setContentPane(BG);
		
//		JLabel BG = new JLabel(new ImageIcon("Register2.png"));
//		BG.setBounds(0,0,1920, 900);
		setContentPane(BG);
		setVisible(true);


		//Title
		JLabel Title = new JLabel("Login with Your King !!");
		Title.setFont(new Font("Algerian",Font.BOLD, 52));
		Title.setBounds((int)(0.43*getWidth()), (int)(0.01*getHeight()), (int)(0.88*getWidth()), (int)(0.26*getHeight()));
		Title.setForeground(Color.WHITE);
		add(Title);

		//Adding The Components:
		Name_Label.setBounds(getWidth()/2,getHeight()/2-((int)(0.26*getHeight())), (int)(0.2*getWidth()), (int)(0.04*getHeight()));
		Name_Label.setForeground(Color.WHITE);
		Name_Label.setFont(new Font("Algerian",Font.BOLD,20));
		add(Name_Label);

		Name_TextField.setBounds(getWidth()/2+((int)(0.16*getWidth())), getHeight()/2-((int)(0.24*getHeight())), (int)(0.22*getWidth()), (int)(0.04*getHeight()));
		add(Name_TextField);

		Password_Label.setBounds(getWidth()/2,getHeight()/2-((int)(0.13*getHeight())), (int)(0.2*getWidth()), (int)(0.08*getHeight()));
		Password_Label.setForeground(Color.WHITE);
		Password_Label.setFont(new Font("Algerian",Font.BOLD,20));
		add(Password_Label);

		Password_TextField.setBounds(getWidth()/2+((int)(0.16*getWidth())), getHeight()/2-((int)(0.11*getHeight())), (int)(0.22*getWidth()), (int)(0.04*getHeight()));
		add(Password_TextField);


		Login_Button.setBounds(getWidth()/2+((int)(0.08*getWidth())), getHeight()/2+((int)(0.13*getHeight())), (int)(0.11*getWidth()), ((int)(0.07*getHeight())));
		Login_Button.addActionListener(this);
		add(Login_Button);

		Cancel_Button.setBounds(getWidth()/2+((int)(0.23*getWidth())), getHeight()/2+((int)(0.13*getHeight())), (int)(0.11*getWidth()), ((int)(0.07*getHeight())));
		Cancel_Button.addActionListener(this);
		add(Cancel_Button);

	}

	public boolean SearchPlayer(Player P)
	{
		for(int i =0; i<Players_Names.length; i++)
		{
			if(Players_Names[i].equals(P.getName()))
			{
				C.Send(P.getName());
				return true;
			}
		}
		return false;

		//			if(login.Map.containsKey(P.getName()))
		//			{
		//				P = login.Map.get(P.getName());
		//				return true;
		//			}
		//			return false;
	}

	public Player UpdatePlayer(Player P, String Message) throws IOException, InterruptedException
	{
		Player P1 = new Player();
		String [] Message_Array = Message.split("#");
		P1.setName(Message_Array[1]);
		P1.setPassword(Message_Array[2]);
		P1.setLevel(Integer.parseInt(Message_Array[3]));
		P1.setMatchesPlayed(Integer.parseInt(Message_Array[4]));
		P1.setMatchesWon(Integer.parseInt(Message_Array[5]));
		P1.setMatchesLost(Integer.parseInt(Message_Array[6]));
		//			if(login.Map.containsKey(P.getName()))
		//			{
		//				Pl = login.Map.get(P.getName());
		//			}
		return P1;

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == Login_Button)
		{
			Player P = new Player();
			P.setName(Name_TextField.getText());
			boolean f = SearchPlayer(P);
			try 
			{
				if(f == true)
				{
					System.out.println("hoho ana mesty receive xD");
					String Message = C.Receive();
					System.out.println("hoho ana 5alas receive xD");
					P = UpdatePlayer(P, Message);

					System.out.println("Player Updated !");
					if(P.getPassword().equals(Password_TextField.getText()))
					{
						JOptionPane.showMessageDialog(this, "Name : " + P.getName() + "\nLevel : " + P.getLevel()
						+ "\nMatches Played : " + P.getMatchesPlayed() + "\nMatches Won : " + P.getMatchesWon()
						+ "\nMatches Lost : " + P.getMatchesLost(),
						"Player Details ! ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "You Are The " + Team + " Team",
								"Welcome ! ", JOptionPane.PLAIN_MESSAGE);
						Battle_Field_Test BF = new Battle_Field_Test(Team);
						Thread T = new Thread(BF);
						T.start();
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Wrong Name or Password",
								"Error ! ", JOptionPane.ERROR_MESSAGE);
						Name_TextField.setText("");
						Password_TextField.setText("");
					}
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Wrong Name or Password",
							"Error ! ", JOptionPane.ERROR_MESSAGE);
					Name_TextField.setText("");
					Password_TextField.setText("");
				}
			} 
			catch (IOException | InterruptedException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		}
		else
		{
			this.setVisible(false);
			login.setVisible(true);
		}

	}

}
