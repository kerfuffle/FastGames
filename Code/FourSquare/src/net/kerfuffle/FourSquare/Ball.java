package net.kerfuffle.FourSquare;

import net.kerfuffle.Utilities.GUI.Quad;

public class Ball {

	private Quad box;
	
	public Ball(Quad box)
	{
		this.box=box;
	}
	
	public void updateCoords(float x, float y)
	{
		box.x=x;
		box.y=y;
	}
	
	public void draw()
	{
		box.draw();
	}
	
}
