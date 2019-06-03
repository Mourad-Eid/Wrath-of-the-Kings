package Game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Battle_Field_Test extends JFrame implements Runnable, MouseListener
{	
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

	//Blue Castle
	Castle Blue_Castle = new Castle(0,0,"Blue");
	JLabel Blue_Castle_Health;
	JLabel Blue_Gold;
	JLabel Blue_Army_Count;
	JLabel Blue_Dead_Count;
	JLabel Blue_Mana;

	//Red Castle
	Castle Red_Castle = new Castle(getWidth()-((int)(0.05*getWidth())),0,"Red");
	JLabel Red_Castle_Health;
	JLabel Red_Gold;
	JLabel Red_Army_Count;
	JLabel Red_Dead_Count;
	JLabel Red_Mana;

	//Monster Options
	JLabel Angel_Option;
	JLabel CatFighter_Option;
	JLabel Assassin_Option;
	JLabel Warrior_Option;
	JLabel WarMachine_Option;
	JLabel Swordsman_Option;
	Castle MyCastle = new Castle(0,0,"");
	String Team;

	//NEW 
	private static ArrayList<Monster> redArmy=new ArrayList<Monster>();
	private static ArrayList<Monster> blueArmy=new ArrayList<Monster>();

	public Battle_Field_Test(String T)
	{
		Team = T;
		
		//Window
		setTitle("Warth of the Kings!");
		setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);	
		//gamePanel.setSize(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
		setLayout(null);
		//gamePanel.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);		
		//		JLabel BG = new JLabel(new ImageIcon("BattleFieldv1.jpg"));
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("BattleFieldv1.jpg")
				.getImage().getScaledInstance(env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height
						, Image.SCALE_DEFAULT));
		JLabel BG = new JLabel();
		BG.setIcon(imageIcon);
		BG.setBounds(0,0,env.getMaximumWindowBounds().width, env.getMaximumWindowBounds().height);
		setContentPane(BG);

		Setting_Castle();
		Setting_Monster_Menu();		

		//Calling Functions
		if(Team.equals("Red"))
		{
			MyCastle=Red_Castle;
			Thread C1 = new Thread(MyCastle); //you might need to redo the castle part so don't forget
			//MyCastle.setBounds(getWidth()-((int)(0.1*getWidth())), 0, (int)(0.1*getWidth()), getHeight());
			add(MyCastle);
			add(Blue_Castle);
			revalidate();
			repaint();
		
			//MyCastle.setForeground(Color.RED);
			C1.start();
		}
		else if(Team.equals("Blue"))
		{
			MyCastle=Blue_Castle;
			Thread C2 = new Thread(MyCastle);
			//MyCastle.setBounds(getWidth()-((int)(0.1*getWidth())), 0, (int)(0.1*getWidth()), getHeight());
			add(MyCastle);
			add(Red_Castle);
			revalidate();
			repaint();
			
			//MyCastle.setForeground(Color.BLUE);			
			C2.start();
		}
		pack();
	}

	public void Setting_Blue()
	{
		Font f=new Font("Calibri", Font.BOLD, 14);
		Blue_Castle.setBounds(0, 0, (int)(0.05*getWidth()),(int)(getHeight()*0.7));
		//Blue Castle Components
		Blue_Castle_Health = new JLabel("Castle Health: " + MyCastle.getHealth());
		Blue_Gold = new JLabel("Gold: " + MyCastle.getGold());
		Blue_Army_Count = new JLabel("Alive Army: " + MyCastle.getArmy().size() + " Monsters");
		Blue_Dead_Count = new JLabel("Dead Army: " + MyCastle.getDead_Army().size() + " Monsters");
		Blue_Mana = new JLabel("Mana: " + MyCastle.getMana());

		//Blue Castle Components Position

		Blue_Castle_Health.setBounds((int)(0.01*getWidth()), (int)(0.74*getHeight()),
				(int)(0.26*getWidth()),(int)(0.05*getHeight()));
		//Blue_Castle_Health.setBounds(500, 500, 500, 50);
		Blue_Castle_Health.setFont(f);
		Blue_Castle_Health.setForeground(Color.BLUE);

		Blue_Gold.setBounds((int)(0.01*getWidth()), (int)(0.77*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Gold.setFont(f);
		Blue_Gold.setForeground(Color.BLUE);

		Blue_Army_Count.setBounds((int)(0.01*getWidth()), (int)(0.8*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Army_Count.setFont(f);
		Blue_Army_Count.setForeground(Color.BLUE);

		Blue_Dead_Count.setBounds((int)(0.01*getWidth()), (int)(0.83*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Dead_Count.setFont(f);
		Blue_Dead_Count.setForeground(Color.BLUE);

		Blue_Mana.setBounds((int)(0.01*getWidth()), (int)(0.86*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Mana.setFont(f);
		Blue_Mana.setForeground(Color.BLUE);

		//Adding Blue Castle Components
		add(Blue_Castle_Health);
		add(Blue_Gold);
		add(Blue_Army_Count);
		add(Blue_Dead_Count);
		add(Blue_Mana);

	}

	public void Setting_Other_Red()
	{
		//setting Castle Bounds
		Red_Castle.setBounds(getWidth()-((int)(0.05*getWidth())), 0, (int)(0.05*getWidth()),(int)(getHeight()*0.7));
		//add(Red_Castle);
		Font f=new Font("Calibri", Font.BOLD, 14);

		//Red Castle Components
		Red_Castle_Health = new JLabel("Castle Health: " + Red_Castle.getHealth());
		Red_Gold = new JLabel("Gold: " + Red_Castle.getGold());
		Red_Army_Count = new JLabel("Alive Army: " + Red_Castle.getArmy().size() + " Monsters");
		Red_Dead_Count = new JLabel("Dead Army: " + Red_Castle.getDead_Army().size() + " Monsters");
		Red_Mana = new JLabel("Mana: " + Red_Castle.getMana());


		//Red Castle Components Positions
		Red_Castle_Health.setBounds((int)(0.88*getWidth()), (int)(0.74*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Castle_Health.setFont(f);
		Red_Castle_Health.setForeground(Color.RED);

		Red_Gold.setBounds((int)(0.88*getWidth()), (int)(0.77*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Gold.setFont(f);
		Red_Gold.setForeground(Color.RED);

		Red_Army_Count.setBounds((int)(0.88*getWidth()), (int)(0.8*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Army_Count.setFont(f);
		Red_Army_Count.setForeground(Color.RED);

		Red_Dead_Count.setBounds((int)(0.88*getWidth()), (int)(0.83*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Dead_Count.setFont(f);
		Red_Dead_Count.setForeground(Color.RED);

		Red_Mana.setBounds((int)(0.88*getWidth()), (int)(0.86*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Mana.setFont(f);
		Red_Mana.setForeground(Color.RED);


		//Adding Red Castle Components
		add(Red_Castle_Health);
		add(Red_Gold);
		add(Red_Army_Count);
		add(Red_Dead_Count);
		add(Red_Mana);

		revalidate();
		repaint();
	}
	public void Setting_Red()
	{
		Font f=new Font("Calibri", Font.BOLD, 14);

		//setting castle position
		Red_Castle.setBounds(getWidth()-((int)(0.05*getWidth())), 0, (int)(0.05*getWidth()),(int)(getHeight()*0.7));
		//add(Red_Castle);
		//Red Castle Components
		Red_Castle_Health = new JLabel("Castle Health: " + MyCastle.getHealth());
		Red_Gold = new JLabel("Gold: " + MyCastle.getGold());
		Red_Army_Count = new JLabel("Alive Army: " + MyCastle.getArmy().size() + " Monsters");
		Red_Dead_Count = new JLabel("Dead Army: " + MyCastle.getDead_Army().size() + " Monsters");
		Red_Mana = new JLabel("Mana: " + MyCastle.getMana());


		//Red Castle Components Positions
		Red_Castle_Health.setBounds((int)(0.88*getWidth()), (int)(0.74*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Castle_Health.setFont(f);
		Red_Castle_Health.setForeground(Color.RED);

		Red_Gold.setBounds((int)(0.88*getWidth()), (int)(0.77*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Gold.setFont(f);
		Red_Gold.setForeground(Color.RED);

		Red_Army_Count.setBounds((int)(0.88*getWidth()), (int)(0.8*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Army_Count.setFont(f);
		Red_Army_Count.setForeground(Color.RED);

		Red_Dead_Count.setBounds((int)(0.88*getWidth()), (int)(0.83*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Dead_Count.setFont(f);
		Red_Dead_Count.setForeground(Color.RED);

		Red_Mana.setBounds((int)(0.88*getWidth()), (int)(0.86*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Red_Mana.setFont(f);
		Red_Mana.setForeground(Color.RED);


		//Adding Red Castle Components
		add(Red_Castle_Health);
		add(Red_Gold);
		add(Red_Army_Count);
		add(Red_Dead_Count);
		add(Red_Mana);

		revalidate();
		repaint();

	}
	
	public void Setting_Other_Blue()
	{
		Font f=new Font("Calibri", Font.BOLD, 14);

		//setting castle position
		Blue_Castle.setBounds(0, 0, (int)(0.05*getWidth()),(int)(getHeight()*0.7));
		//Blue Castle Components
		Blue_Castle_Health = new JLabel("Castle Health: " + Blue_Castle.getHealth());
		Blue_Gold = new JLabel("Gold: " + Blue_Castle.getGold());
		Blue_Army_Count = new JLabel("Alive Army: " + Blue_Castle.getArmy().size() + " Monsters");
		Blue_Dead_Count = new JLabel("Dead Army: " + Blue_Castle.getDead_Army().size() + " Monsters");
		Blue_Mana = new JLabel("Mana: " + Blue_Castle.getMana());

		//Blue Castle Components Position

		Blue_Castle_Health.setBounds((int)(0.01*getWidth()), (int)(0.74*getHeight()),
				(int)(0.26*getWidth()),(int)(0.05*getHeight()));
		//Blue_Castle_Health.setBounds(500, 500, 500, 50);
		Blue_Castle_Health.setFont(f);
		Blue_Castle_Health.setForeground(Color.BLUE);

		Blue_Gold.setBounds((int)(0.01*getWidth()), (int)(0.77*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Gold.setFont(f);
		Blue_Gold.setForeground(Color.BLUE);

		Blue_Army_Count.setBounds((int)(0.01*getWidth()), (int)(0.8*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Army_Count.setFont(f);
		Blue_Army_Count.setForeground(Color.BLUE);

		Blue_Dead_Count.setBounds((int)(0.01*getWidth()), (int)(0.83*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Dead_Count.setFont(f);
		Blue_Dead_Count.setForeground(Color.BLUE);

		Blue_Mana.setBounds((int)(0.01*getWidth()), (int)(0.86*getHeight()), (int)(0.26*getWidth()), (int)(0.05*getHeight()));
		Blue_Mana.setFont(f);
		Blue_Mana.setForeground(Color.BLUE);

		//Adding Blue Castle Components
		add(Blue_Castle_Health);
		add(Blue_Gold);
		add(Blue_Army_Count);
		add(Blue_Dead_Count);
		add(Blue_Mana);
	}
	//Drawing Functions
	public void Setting_Castle()
	{

		if(Team.equals("Blue"))
		{
			Setting_Blue();
			Setting_Other_Red();
		}
		else
		{
			Setting_Red();
			Setting_Other_Blue();
		}

	}

	public void Setting_Monster_Menu()
	{
		//Setting Icons
		Angel_Option = new JLabel(new ImageIcon("Angel_Description.jpg"));
		Angel_Option.setBounds((int)(0.13*getWidth()), (int)(0.725*getHeight()), 170, 200);
		Assassin_Option = new JLabel(new ImageIcon("Assassin_Description.jpg"));
		Assassin_Option.setBounds((int)(0.255*getWidth()), (int)(0.725*getHeight()), 170, 200);
		Swordsman_Option = new JLabel(new ImageIcon("Swordsman_Description.jpg"));
		Swordsman_Option.setBounds((int)(0.38*getWidth()), (int)(0.725*getHeight()), 170, 200);
		CatFighter_Option = new JLabel(new ImageIcon("CatFighter_Description.jpg"));
		CatFighter_Option.setBounds((int)(0.505*getWidth()), (int)(0.725*getHeight()), 170, 200);
		WarMachine_Option = new JLabel(new ImageIcon("WarMachine_Description.jpg"));
		WarMachine_Option.setBounds((int)(0.63*getWidth()), (int)(0.725*getHeight()), 170, 200);
		Warrior_Option = new JLabel(new ImageIcon("Warrior_Description.jpg"));
		Warrior_Option.setBounds((int)(0.755*getWidth()), (int)(0.725*getHeight()), 170, 200);

		//Adding Action Listeners
//		Angel_Option.addMouseMotionListener(this);
		Angel_Option.addMouseListener(this);
//		Warrior_Option.addMouseMotionListener(this);
		Warrior_Option.addMouseListener(this);
//		Assassin_Option.addMouseMotionListener(this);
		Assassin_Option.addMouseListener(this);
//		CatFighter_Option.addMouseMotionListener(this);
		CatFighter_Option.addMouseListener(this);
//		Swordsman_Option.addMouseMotionListener(this);
		Swordsman_Option.addMouseListener(this);
//		WarMachine_Option.addMouseMotionListener(this);
		WarMachine_Option.addMouseListener(this);

		//Angel_Option.setVisible(true);

		//Adding Labels To The Frame
		add(Angel_Option);
		add(Warrior_Option);
		add(Swordsman_Option);
		add(Assassin_Option);
		add(CatFighter_Option);
		add(WarMachine_Option);

		//		JLabel BGold = new JLabel(new ImageIcon("AngelAttackingSpecial.gif"));
		//		BGold.setBounds(0, 0, 800, 800);
		//		add(BGold);
	}

	public void Removing_Score()
	{
		//Removing Blue Castle Components
		remove(Blue_Castle_Health);
		remove(Blue_Gold);
		remove(Blue_Army_Count);
		remove(Blue_Dead_Count);
		remove(Blue_Mana);


		//Removing Red Castle Components
		remove(Red_Castle_Health);
		remove(Red_Gold);
		remove(Red_Army_Count);
		remove(Red_Dead_Count);
		remove(Red_Mana);

	}
	
	public boolean CanSpawn(Castle C, Monster M)
	{
		if(C.getMana() >= M.getCost())
			return true;
		return false;
	}

	public void Spawn(Castle C, Monster A, int x, int y)
	{
		A.setTeam(Team); //NEW 
		if(A.getTeam().equals("Blue"))
		{
			blueArmy.add(A);
		}
		else if (A.getTeam().equals("Red"))
		{
			redArmy.add(A);
		}
		C.getArmy().add(A);
		A.Change_Image_To("Stand");		
		A.setBounds(x,y, getWidth(), getHeight());
		add(A);	
		C.setMana(C.getMana()-A.getCost());
		//Adding Updated Lists
		Removing_Score();
		Setting_Castle();
		Thread move=new Thread(A);
		move.start();
		repaint();
		validate();


	}

	@Override
	public void mouseClicked(MouseEvent e)
	{

	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getSource() == Angel_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			Angel A = new Angel(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);
			
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
		}
		

		else if(e.getSource() == Assassin_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			Assassin A = new Assassin(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
		}

		else if(e.getSource() == CatFighter_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			CatFighter A = new CatFighter(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);			
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}

		}

		else if(e.getSource() == WarMachine_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			WarMachine A = new WarMachine(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}

		}

		else if(e.getSource() == Warrior_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			Warrior A = new Warrior(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}

		}

		else if(e.getSource() == Swordsman_Option)
		{
			if(checkSpawnPosition(e.getXOnScreen(), e.getYOnScreen()))
			{
			Swordsman A = new Swordsman(e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())),Team);
			if(CanSpawn(MyCastle,A))
			{
				Spawn(MyCastle,A, e.getXOnScreen()-((int)(0.03*getWidth())),e.getYOnScreen()-((int)(0.6*getHeight())));
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Not Enough Mana",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can't spawn a Monster Here!",
						"Error ! ", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	@Override
	public void run() 
	{
		try 
		{
			while(true)
			{
				Removing_Score();
				Setting_Castle();
				revalidate();
				repaint();

				Thread.sleep(6000);

			}
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	//NEW	
		public static ArrayList<Monster> getRedArmy()
		{
			return redArmy;
		}
		
		public static ArrayList<Monster> getBlueArmy()
		{
			return blueArmy;
		}
	
		public boolean checkSpawnPosition(int x,int y)
		{
			if(Team.equals("Blue")&& x <env.getCenterPoint().x && y< env.getMaximumWindowBounds().height*0.7)
			{
				return true;
			}
			else if(Team.contentEquals("Red") && x> env.getCenterPoint().x && y< env.getMaximumWindowBounds().height*0.7)
			{
				return true;
			}
			else
				return false;
		}
}
