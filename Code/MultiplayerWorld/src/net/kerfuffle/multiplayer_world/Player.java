package net.kerfuffle.multiplayer_world;

import org.lwjgl.glfw.GLFW;

import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Text.Font;

import static net.kerfuffle.Utilities.GUI.DavisGUI.*;

public class Player extends Entity{
	
	private String username;
	private int UP = GLFW.GLFW_KEY_W, DOWN = GLFW.GLFW_KEY_S, LEFT = GLFW.GLFW_KEY_A, RIGHT = GLFW.GLFW_KEY_D;
	private Font font;
	
	public Player(String username, float x, float y, float w, float h, Font font)
	{
		super(new Quad(x,y,w,h, new RGB(1,1,1)), Global.PLAYER_SPEED);
		this.username = username;
		this.font = font;
	}
	
	private void checkMovement()
	{
		if (keyDown(UP))
		{
			moveUp();	
		}
		if (keyDown(DOWN))
		{
			moveDown();
		}
		if (keyDown(LEFT))
		{
			moveLeft();
		}
		if (keyDown(RIGHT))
		{
			moveRight();
		}
	}
	
	public void update()
	{
		checkMovement();
		box.draw();
		font.drawText(username, box.x + (box.w/2), box.y - 20);
	}

}
