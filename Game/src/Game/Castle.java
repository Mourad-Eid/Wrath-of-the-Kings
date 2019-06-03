package Game;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Castle extends Monster implements Runnable
{
	private int castleHP;
	//private String castleImg;
	private int Gold;
	private int Castle_Mana;
	private int Defense;
	private String Team;
	private ArrayList<Monster> Army = new ArrayList<Monster>();
	private ArrayList<Monster> Dead_Army = new ArrayList<Monster>();
	
	public Castle(int x,int y,String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(2000);
		setCost(0);
		setDefense(100);
		setAttack(200);
		setPosition(P);
		setRange(15);
		//setAttack_Speed(1500);
		setMovement_Speed(0);
		setMana(500);
		this.setIcon(new ImageIcon("D:\\study\\OOD\\stone-watchtower"));
		validate();
		//setDescription_Image("Warrior_Description.jpg");
	}

	//Functions
	
	/*public String getTeam() {
		return Team;
	}

	public void setTeam(String team) {
		Team = team;
	}*/
	
	/*public int getCastleHP() {
		return castleHP;
	}

	public void setCastleHP(int castleHP) {
		this.castleHP = castleHP;
	}

	public int getCastle_Mana() {
		return Castle_Mana;
	}

	public void setCastle_Mana(int castle_Mana) {
		Castle_Mana = castle_Mana;
	}*/

	/*public int getDefense() {
		return Defense;
	}

	public void setDefense(int defense) {
		Defense = defense;
	}
	public void Add_Soldier(Monster M)
	{
		Army.add(M);
	}
	//Few Setter and Getters
	/*public void setHealth(int castleHP)
	{
		this.castleHP = castleHP;
	}	

	public int getHealth()
	{
		return castleHP;
	}*/
	

	public void Dead_Soldier(Monster M)
	{
		Army.remove(M);
		Dead_Army.add(M);
	}
	
	public int getGold()
	{
		return Gold;
	}

	public void setGold(int gold) 
	{
		if(gold>=0)
			Gold = gold;
		else
			Gold = 0;
	}
	
	public int getMana() 
	{
		return Castle_Mana;
	}

	public void setMana(int mana) 
	{
		if(mana>=0)
			Castle_Mana = mana;
		else
			Castle_Mana = 0;	
	}

	public ArrayList<Monster> getArmy() 
	{
		return Army;
	}

	public void setArmy(ArrayList<Monster> army)
	{
		Army = army;
	}

	public ArrayList<Monster> getDead_Army()
	{
		return Dead_Army;
	}

	public void setDead_Army(ArrayList<Monster> dead_Army)
	{
		Dead_Army = dead_Army;
	}
	
	/*public void die()
	{
		Container C=this.getParent();
		if(this.getTeam().contentEquals("Red"))
		{
			//Battle_Field_Test.getRedArmy().remove(this);
			C.remove(this);
			C.repaint();
			C.validate();
		}
		else
		{
			//Battle_Field_Test.getBlueArmy().remove(this);
			C.remove(this);
			C.repaint();
			C.validate();
		}

	}*/

	@Override
	public void run() 
	{
		try 
		{
			while(true)
			{
				Thread.sleep(6000);
				setMana(getMana()+25);
			}

		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}