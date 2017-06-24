package net.kerfuffle.LitterBox;

import net.kerfuffle.Utilities.GUI.Text.Font;

public class Message {

	private float x,y;
	private String message;
	private int ticks, count = 0;
	private Font font;
	private boolean alive = true;
	
	public Message(String message, float x, float y, int ticks, Font font)
	{
		this.x=x;
		this.y=y;
		this.message = message;
		this.ticks = ticks;
		this.font = font;
	}
	
	public void draw()
	{
		if (count < ticks)
		{
			font.drawText(message, x, y);
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
	
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	
}