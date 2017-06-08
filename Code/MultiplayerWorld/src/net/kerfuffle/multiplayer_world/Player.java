package net.kerfuffle.multiplayer_world;

import org.lwjgl.glfw.GLFW;

import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Text.Font;
import net.kerfuffle.multiplayer_world.Packets.PacketMove;

import static net.kerfuffle.Utilities.GUI.DavisGUI.*;

public class Player extends Entity{
	
	private String username;
	private int UP = GLFW.GLFW_KEY_W, DOWN = GLFW.GLFW_KEY_S, LEFT = GLFW.GLFW_KEY_A, RIGHT = GLFW.GLFW_KEY_D;
	private Font font;
	private SendThread st;
	
	public Player(String username, float x, float y, float w, float h, Font font, SendThread st)
	{
		super(new Quad(x,y,w,h, new RGB(1,1,1)), Global.PLAYER_SPEED);
		this.username = username;
		this.font = font;
		this.st=st;
	}
	
	public void setFont(Font font)
	{
		this.font = font;
	}
	
	private void checkMovement()
	{
		PacketMove pm = null;
		if (keyDown(UP))
		{
			moveUp();
			pm = new PacketMove(Global.UP);
		}
		if (keyDown(DOWN))
		{
			moveDown();
			pm = new PacketMove(Global.DOWN);
		}
		if (keyDown(LEFT))
		{
			moveLeft();
			pm = new PacketMove(Global.LEFT);
		}
		if (keyDown(RIGHT))
		{
			moveRight();
			pm = new PacketMove(Global.RIGHT);
		}
		
		if (pm != null)
		{
			st.sendPacket(pm);
		}
	}
	
	public void update()
	{
		checkMovement();
		box.draw();
		font.drawText(username, box.x + (box.w/2), box.y - 30);
	}

}
