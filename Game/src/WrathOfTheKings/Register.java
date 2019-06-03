package WrathOfTheKings;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Game.Battle_Field_Test;

public class Register extends JFrame implements ActionListener, Search
{
	//Components
	JLabel Name_Label = new JLabel("Enter Name:");
	JTextField Name_TextField = new JTextField(20);
	JLabel Password_Label = new JLabel("Enter Password: ");
	JTextField Password_TextField = new JPasswordField(20);
	JLabel Confirm_Password_Label = new JLabel("Confirm Password: ");
	JTextField Confirm_Password_TextField = new JPasswordField(20);
	JButton Register_Button = new JButton("Register");
	JButton Cancel_Button = new JButton("Cancel");
	Login login ;
	String [] Players_Names;
	Client C;
	String Team;


	public Register(Login L, String[] Players_Names, Client c)
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
		setContentPane(BG);
//
//		JLabel BG = new JLabel(new ImageIcon("Register2.png"));
//		BG.setBounds(0,0,1920, 900);
//		New_Player_Form.setContentPane(BG);
		setVisible(true);


		//Title
		JLabel Title = new JLabel("Create Your King !!");
		Title.setFont(new Font("Algerian",Font.BOLD, 52));
		Title.setBounds((int)(0.46*getWidth()), (int)(0.01*getHeight()), (int)(0.88*getWidth()), (int)(0.26*getHeight()));
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

		Password_TextField.setBounds(getWidth()/2+(int)(0.16*getWidth()), getHeight()/2-(int)(0.11*getHeight()), (int)(0.2*getWidth()), (int)(0.04*getHeight()));
		add(Password_TextField);


		Confirm_Password_Label.setBounds(getWidth()/2,getHeight()/2, (int)(0.2*getWidth()), (int)(0.08*getHeight()));
		Confirm_Password_Label.setForeground(Color.WHITE);
		Confirm_Password_Label.setFont(new Font("Algerian",Font.BOLD,20));
		add(Confirm_Password_Label);

		Confirm_Password_TextField.setBounds(getWidth()/2+(int)(0.16*getWidth()), getHeight()/2+((int)(0.02*getHeight())), (int)(0.2*getWidth()), (int)(0.04*getHeight()));
		add(Confirm_Password_TextField);

		Register_Button.setBounds(getWidth()/2+((int)(0.08*getWidth())), getHeight()/2+((int)(0.13*getHeight())), (int)(0.11*getWidth()), ((int)(0.07*getHeight())));
		Register_Button.addActionListener(this);
		add(Register_Button);

		Cancel_Button.setBounds(getWidth()/2+((int)(0.23*getWidth())), getHeight()/2+((int)(0.13*getHeight())), (int)(0.11*getWidth()), ((int)(0.07*getHeight())));
		Cancel_Button.addActionListener(this);
		add(Cancel_Button);

		//Register

	}

	public boolean Register_Player(String Name, String Password, String Confirmed, Login L) throws FileNotFoundException, IOException
	{
//		Player P = new Player();
		if(Password.equals(Confirmed))
		{
//			P.setName(Name);
//			P.setPassword(Password);
			//L.AddPlayer(P);
			//L.FillPlayerList();
			return true;
		}
		else
		{
			JOptionPane.showMessageDialog(this, "The Confirmation of The Password is Incorrect",
					"Error ! ", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}	
	public boolean SearchPlayer(Player P)
	{

		for(int i =0; i<Players_Names.length; i++)
		{
			if(Players_Names[i].equals(P.getName()))
				return true;
		}
		return false;
		//		if(login.Map.containsKey(P.getName()))
		//		{
		//			P = login.Map.get(P.getName());
		//			return true;
		//		}
		//		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == Register_Button)
		{
			try
			{
				Player P = new Player();
				P.setName(Name_TextField.getText());
				if(SearchPlayer(P)) 
				{
					JOptionPane.showMessageDialog(this, "This Name is Already Taken "
							+ ", Please Choose Another One",
							"Error ! ", JOptionPane.ERROR_MESSAGE);
					Name_TextField.setText("");
					Password_TextField.setText("");
					Confirm_Password_TextField.setText("");
				}
				else
				{
					if(Register_Player(Name_TextField.getText(), Password_TextField.getText()
							,Confirm_Password_TextField.getText(), login))
					{
						P.setPassword(Password_TextField.getText());
						C.Send("Player#" + P.getName() + "#" + P.getPassword() + "#" + P.getLevel() + "#" +
								P.getMatchesPlayed() + "#" + P.getMatchesWon() + "#" + P.getMatchesLost());
						JOptionPane.showMessageDialog(this, "Registeration Successful !",
								"Success ! ", JOptionPane.INFORMATION_MESSAGE);
						setVisible(false);
						JOptionPane.showMessageDialog(null, "You Are The " + Team + " Team",
								"Welcome ! ", JOptionPane.PLAIN_MESSAGE);
						Battle_Field_Test BF = new Battle_Field_Test(Team);
						Thread T = new Thread(BF);
						T.start();
					}
				}		
			}
			catch (IOException en) 
			{
				en.printStackTrace();
			}

		}
		else if(e.getSource() == Cancel_Button)
		{
			login.setVisible(true);
			this.setVisible(false);
		}
	}

}
