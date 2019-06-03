package Game;

import java.awt.Point;

public class Swordsman extends Monster
{
	public Swordsman(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(300);
		setCost(150);
		setDefense(50);
		setAttack(170);
		setPosition(P);
		setRange(20);
		//setAttack_Speed(1000);
		setMovement_Speed(2);
		setDescription_Image("Swordsman_Description.jpg");

		if(T.equals("Blue"))
		{
			setStand_ImagePath("RightSwordsmanStanding.gif");
			setWalk_ImagePath("RightSwordsmanWalking.gif");
			setAttack_ImagePath("RightSwordsmanAttacking.gif");
		}
		else
		{
			setStand_ImagePath("LeftSwordsmanStanding.gif");
			setWalk_ImagePath("LeftSwordsmanWalking.gif");
			setAttack_ImagePath("LeftSwordsmanAttacking.gif");
		}

	}
}