package Game;

import java.awt.Point;

public class Warrior extends Monster
{
	int Counter = 0;
	public Warrior(int x, int y, String T)
	{
		super(x,y,T);
		Point P = new Point();
		P.x = x;
		P.y = y;
		setHealth(400);
		setCost(200);
		setDefense(50);
		setAttack(175);
		setPosition(P);
		setRange(15);
		//setAttack_Speed(1500);
		setMovement_Speed(3);
		setDescription_Image("Warrior_Description.jpg");


		if(T.equals("Blue")) //el faree2 eli 3ala el shemal fa yekon weshohom a7iet el ymeen
		{
			setStand_ImagePath("RightWarriorStanding.gif");
			setWalk_ImagePath("RightWarriorWalking.gif");
			setAttack_ImagePath("RightWarriorAttacking.gif");
		}
		else
		{
			setStand_ImagePath("LeftWarriorStanding.gif");
			setWalk_ImagePath("LeftWarriorWalking.gif");
			setAttack_ImagePath("LeftWarriorAttacking.gif");
		}
	}

/*	public void Special() // kol 5 darabat el attack speed beta3too betzeed
	{
		if(Counter%5 == 0 && Counter != 0)
		{
			setAttack_Speed(getAttack_Speed() + 250);
		}
		Counter++;
	}*/
}