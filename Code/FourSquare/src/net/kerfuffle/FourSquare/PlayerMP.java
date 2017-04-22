package net.kerfuffle.FourSquare;

import net.kerfuffle.Utilities.GUI.Quad;

public class PlayerMP {

	private Quad box;
	private String username;
	
	public PlayerMP(String username, Quad box)
	{
		this.username=username;
		this.box=box;
	}
	
	public void updateCoords(float x, float y)
	{
		box.x=x;
		box.y=y;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void draw()
	{
		box.draw();
	}
	
}
