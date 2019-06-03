package Game;

import java.awt.Point;

public class CatFighter extends Monster
{
	int Counter = 0;
	public CatFighter(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(150);
		setCost(50);
		setDefense(30);
		setAttack(120);
		setPosition(P);
		setRange(10);
		//setAttack_Speed(1000);
		setMovement_Speed(2);
		setDescription_Image("CatFighter_Description.jpg");

		
		if(T.equals("Blue")) //el faree2 eli 3ala el shemal fa yekon weshohom a7iet el ymeen
		{
			setStand_ImagePath("RightCatFighterStanding.gif");
			setWalk_ImagePath("RightCatFighterWalking.gif");
			setAttack_ImagePath("RightCatFighterAttacking.gif");
		}

		else
		{
			setStand_ImagePath("LeftCatFighterStanding.gif");
			setWalk_ImagePath("LeftCatFighterWalking.gif");
			setAttack_ImagePath("LeftCatFighterAttacking.gif");
		}

	}

	public void Special() // kol 5 darabat el attack beta3ha bizeed b 50
	{
		if(Counter%5 == 0 && Counter != 0)
		{
			setAttack(getAttack()+50);
		}
		Counter++;

	}
}