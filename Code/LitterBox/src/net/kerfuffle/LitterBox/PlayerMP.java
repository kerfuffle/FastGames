package net.kerfuffle.LitterBox;

import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Text.Font;

public class PlayerMP extends Entity{

	private String username;
	private Font font;
	
	public PlayerMP(String username, float x, float y, float w, float h, Font font)
	{
		super(new Quad(x,y,w,h, new RGB(1,1,1)), Global.PLAYER_SPEED);
		this.username = username;
		this.font = font;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void draw()
	{
		box.draw();
		font.drawText(username, box.x + (box.w/2), box.y - 30);
	}
}
