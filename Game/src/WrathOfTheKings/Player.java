package WrathOfTheKings;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Player implements Serializable
{
	private String Name;
	private String Password;
	private int Level;
	private int MatchesPlayed;
	private int MatchesWon;
	private int MatchesLost;
	
	public Player()
	{
		Name = null;
		Password = null;
		Level = 1;
		MatchesPlayed = 0;
		MatchesWon = 0;
		MatchesLost = 0;
	}

	public void UpdatePlayer() //Updates the info of the player such as the matches won/lost/played
	{

	}


	public void DeletePlayer() //Delete Player from HashMap
	{

	}

	public Player SearchPlayer(String N) //Search for the player by name or key
	{
		Player P = new Player();
		return P;
	}

	public String getName() 
	{
		return Name;
	}

	public boolean setName(String name)
	{
		if(name != null)
		{
			this.Name = name;
			return true;
		}
		else
			return false;
	}

	public String getPassword()
	{
		return Password;
	}

	public boolean setPassword(String password)
	{
		if(password != null)
		{
			this.Password = password;
			return true;
		}
		else
			return false;

	}

	public int getLevel()
	{
		return Level;
	}

	public boolean setLevel(int level)
	{
		if(level > 0)
		{
			this.Level = level;
			return true;
		}
		else
			return false;
	}

	public int getMatchesPlayed()
	{
		return MatchesPlayed;
	}

	public boolean setMatchesPlayed(int matchesPlayed)
	{
		if(matchesPlayed >= 0)
		{
			this.MatchesPlayed = matchesPlayed;
			return true;
		}
		else
			return false;
	}

	public int getMatchesWon() 
	{
		return MatchesWon;
	}

	public boolean setMatchesWon(int matchesWon) 
	{
		if(matchesWon >= 0)
		{
			this.MatchesWon = matchesWon;
			return true;
		}
		else
			return false;	
	}

	public int getMatchesLost() 
	{
		return MatchesLost;
	}

	public boolean setMatchesLost(int matchesLost) 
	{
		if(matchesLost >= 0)
		{
			this.MatchesLost = matchesLost;
			return true;
		}
		else
			return false;	
	}

}
