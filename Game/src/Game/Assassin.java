package Game;

import java.awt.Point;

public class Assassin extends Monster
{

	public Assassin(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(100);
		setCost(150);
		setDefense(20);
		setAttack(300);
		setPosition(P);
		setRange(10);
		//setAttack_Speed(1000);
		setMovement_Speed(4);
		setDescription_Image("Assassin_Description.jpg");

		
		if(T.equals("Blue"))
		{
			setStand_ImagePath("RightAssassinStanding.gif");
			setWalk_ImagePath("RightAssassinWalking.gif");
			setAttack_ImagePath("RightAssassinAttacking.gif");
		}
		else
		{
			setStand_ImagePath("LeftAssassinStanding.gif");
			setWalk_ImagePath("LeftAssassinWalking.gif");
			setAttack_ImagePath("LeftAssassinAttacking.gif");
		}

	}
}