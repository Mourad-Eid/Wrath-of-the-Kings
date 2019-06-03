package Game;

import java.awt.Point;

public class Angel extends Monster
{
	private String Special_ImagePath;
	private int Counter = 0;


	public Angel(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(350);
		setCost(200);
		setDefense(70);
		setAttack(200);
		setPosition(P);
		setRange(20);
		//setAttack_Speed(1750);
		setMovement_Speed(3);
		setDescription_Image("Angel_Description.jpg");

		if(T.equals("Blue"))
		{
			setStand_ImagePath("RightAngelStanding.gif");
			setWalk_ImagePath("RightAngelWalking.gif");
			setAttack_ImagePath("RightAngelAttacking.gif");
			setSpecial_ImagePath("RightAngelSpecial.gif");
		}

		else
		{
			setStand_ImagePath("LeftAngelStanding.gif");
			setWalk_ImagePath("LeftAngelWalking.gif");
			setAttack_ImagePath("LeftAngelAttacking.gif");
			setSpecial_ImagePath("LeftAngelSpecial.gif");
		}
	}

	public String getSpecial_ImagePath() 
	{
		return Special_ImagePath;
	}

	public void Special()
	{
		if(Counter < 5)
		{
			setAttack(200);
			Counter ++;
		}
		else if (Counter == 5)
		{
			setAttack(getAttack() + 100);
			Counter = 0;
		}

	}

	public void setSpecial_ImagePath(String special_ImagePath) 
	{
		Special_ImagePath = special_ImagePath;
	}
}