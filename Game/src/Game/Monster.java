package Game;
import javax.swing.*;

import java.awt.Container;
import java.awt.Point;
import WrathOfTheKings.*;

abstract class Monster extends JLabel implements Runnable
{
	private int Health;
	private Point Position ;
	private int Cost;
	private int Attack;
	private int Defense;
	private int Range;
	//private long Attack_Speed;
	private int Movement_Speed;
	private String Stand_ImagePath ;
	private String Attack_ImagePath ;
	private String Walk_ImagePath ;
	private String Main_Image;
	private String Description_Image;
	private String Team;
	//private Boolean isSelected;


	//Constructor
	public Monster(int x, int y, String Team)
	{
		Position = new Point();
		Position.x = x ;
		Position.y = y ;
		this.Team = Team;
	}


	//Setter and Getters
	public int getHealth()
	{
		return Health;
	}
	public void setHealth(int health) 
	{	
		if(health >= 0)
			Health = health;
		else
			Health = 0;
	}
	public String getDescription_Image() 
	{
		return Description_Image;
	}


	public void setDescription_Image(String description_Image) 
	{
		Description_Image = description_Image;
	}

	public int getCost()
	{
		return Cost;
	}
	public void setCost(int cost)
	{
		if(cost > 0)
			Cost = cost;
	}
	public int getAttack() 
	{
		return Attack;
	}
	public void setAttack(int attack)
	{
		if(attack > 0)
			Attack = attack;
	}
	public int getDefense() 
	{
		return Defense;
	}
	public void setDefense(int defense)
	{	
		if(defense > 0)
			Defense = defense;
	}
	public String getStand_ImagePath()
	{
		return Stand_ImagePath;
	}
	public void setStand_ImagePath(String stand_ImagePath) 
	{
		if(stand_ImagePath != null)
			Stand_ImagePath = stand_ImagePath;
	}
	public String getAttack_ImagePath()
	{
		return Attack_ImagePath;
	}
	public void setAttack_ImagePath(String attack_ImagePath)
	{
		if(attack_ImagePath != null)
			Attack_ImagePath = attack_ImagePath;
	}
	public String getWalk_ImagePath()
	{
		return Walk_ImagePath;
	}
	public void setWalk_ImagePath(String walk_ImagePath)
	{
		if(walk_ImagePath != null)
			Walk_ImagePath = walk_ImagePath;
	}

	/*public long getAttack_Speed() 
	{
		return Attack_Speed;
	}
	public void setAttack_Speed(long attack_Speed)
	{
		if(attack_Speed > 0)
			Attack_Speed = attack_Speed;
	}*/
	public int getMovement_Speed() 
	{
		return Movement_Speed;
	}
	public void setMovement_Speed(int movement_Speed)
	{
		if(movement_Speed > 0)
			Movement_Speed = movement_Speed;
	}

	public Point getPosition() 
	{
		return Position;
	}


	public void setPosition(Point position)
	{
		if(position != null)
			Position = position;
	}


	public int getRange() 
	{
		return Range;
	}


	public void setRange(int range)
	{
		if(range>0)
			Range = range;
	}


	public String getMain_Image()
	{
		return Main_Image;
	}


	public void setMain_Image(String main_Image)
	{
		Main_Image = main_Image;
	}

	public String getTeam() 
	{
		return Team;
	}

	public void setTeam(String team)
	{
		if(team != null)
			Team = team;
	}


	//Attack and Move
	public void Attack(Monster M) throws InterruptedException
	{
		if(this.getTeam()!=M.getTeam()) {	
			if(M.getHealth()>0)
			{
				Change_Image_To("Attack");
				M.setHealth(M.getHealth() - Attack + M.getDefense());
				repaint();
				validate();
			}
			else
			{
				M.die();
				validate();
			}
		}
	}
	public void AttackCastle (Castle c)
	{
		if(this.getTeam()!=c.getTeam()) {	
			if(c.getHealth()>0)
			{
				Change_Image_To("Attack");
				c.setHealth(c.getHealth() - Attack + c.getDefense());
				repaint();
				validate();
			}
			else
			{
				c.die();
				validate();
			}
		}
	}

	public boolean is_Near(Monster M) throws InterruptedException 
	{
		if(Position.distance(M.Position)<=Range) 
		{
			return true;	
		}
		else
			return false ;
	}

	public void move()
	{
		if (this.Team.equals("Red")) 
		{
			this.Position.x-=Movement_Speed;
			Change_Image_To("Walking");
			this.setBounds(this.Position.x, this.Position.y, getWidth(), getHeight());
			repaint();
			validate();
		}
		else if (this.Team.equals("Blue"))
		{
			this.Position.x+=Movement_Speed;
			Change_Image_To("Walking");
			this.setBounds(this.Position.x, this.Position.y, getWidth(), getHeight());
			repaint();
			validate();
		}
	}

	public void die()
	{
		Container C=this.getParent();
		if(this.getTeam().contentEquals("Red"))
		{
			Battle_Field_Test.getRedArmy().remove(this);
			C.remove(this);
			C.repaint();
			C.validate();
		}
		else
		{
			Battle_Field_Test.getBlueArmy().remove(this);
			C.remove(this);
			C.repaint();
			C.validate();
		}


	}

	@Override
	public void run()
	{
		try {
			while(true)
			{
				if (this.getTeam().equals("Red"))
				{
					for(Monster m:Battle_Field_Test.getBlueArmy())
					{
						if (this.is_Near(m))
						{
							this.Attack(m);
							Thread.sleep(100);
							repaint();
							validate();
						}						
					}
				}
				
				else
				{
					Thread.sleep(100);
					move();
					repaint();
					validate();
				}

				if(this.getTeam().equals("Blue"))
				{
					for(Monster m:Battle_Field_Test.getRedArmy())
					{
						if (this.is_Near(m))
						{					
							this.Attack(m);
							Thread.sleep(100);	
							repaint();
							validate();
						}

					}
				}

				else
				{
					Thread.sleep(100);
					move();
					repaint();
					validate();			
				}
			}

		}catch (InterruptedException e) {					
			e.printStackTrace();
		}
	}



	public void Change_Image_To(String Mode) 
	{
		switch(Mode)
		{
		case "Attack":
		{
			this.setIcon(new ImageIcon(getAttack_ImagePath()));
			break;
		}
		case "Walking":
		{
			this.setIcon(new ImageIcon(getWalk_ImagePath()));
			break;
		}
		case "Stand":
		{
			this.setIcon(new ImageIcon(getStand_ImagePath()));
			break;
		}
		}
	}

}