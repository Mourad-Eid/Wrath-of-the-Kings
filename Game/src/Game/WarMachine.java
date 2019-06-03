package Game;

import java.awt.Point;

public class WarMachine extends Monster
{
	private String Special_ImagePath;
	private int Counter = 0;

	public WarMachine(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(200);
		setCost(250);
		setDefense(60);
		setAttack(180);
		setPosition(P);
		setRange(20);
		//setAttack_Speed(500);
		setMovement_Speed(3);
		setDescription_Image("WarMachine_Description.jpg");
		
		if(T.equals("Blue"))
		{
			setStand_ImagePath("RightWarMachineStanding.gif");
			setWalk_ImagePath("RightWarMachineWalking.gif");
			setAttack_ImagePath("RightWarMachineAttacking.gif");
			setSpecial_ImagePath("RightWarMachineSpecial.gif");

		}
		else
		{
			setStand_ImagePath("LeftWarMachineStanding.gif");
			setWalk_ImagePath("LeftWarMachineWalking.gif");
			setAttack_ImagePath("LeftWarMachineAttacking.gif");
			setSpecial_ImagePath("LeftWarMachineSpecial.gif");

		}

	}
	
	public String getSpecial_ImagePath() 
	{
		return Special_ImagePath;
	}
	
	public void setSpecial_ImagePath(String special_ImagePath) 
	{
		Special_ImagePath = special_ImagePath;
	}
	public void Special()
	{
		if(Counter < 10)
		{
			setAttack(180);
			Counter ++;
		}
		else if (Counter == 10)
		{
			setAttack(1000);
			Counter = 0;
		}
	}
}