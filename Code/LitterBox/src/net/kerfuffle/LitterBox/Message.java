package net.kerfuffle.LitterBox;

import net.kerfuffle.Utilities.GUI.Coord;
import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Text.Font;

public class Message {

	private Coord c = new Coord();
	private String message;
	private int ticks, count = 0;
	private Font font;
	private boolean alive = true;
	
	public Message(String message, float x, float y, int ticks, Font font)
	{
		c.x=x;
		c.y=y;
		this.message = message;
		this.ticks = ticks;
		this.font = font;
		
		DavisGUI.addLockedCoord(c);
	}
	
	public void draw()
	{
		if (count < ticks)
		{
			font.drawText(message, c.x, c.y);
			count++;
		}
		else
		{
			alive = false;
		}
	}
	
	public boolean isAlive()
	{
		return alive;
	}
	
	public String getMessage()
	{
		return message;
	}

	public float getY()
	{
		return c.y;
	}
	
}
