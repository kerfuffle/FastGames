package net.kerfuffle.LitterBox;

import org.lwjgl.glfw.GLFW;

import net.kerfuffle.LitterBox.Packets.PacketMessage;
import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Text.Font;

public class ChatBox {

	private Quad box;
	private StringBuilder words = new StringBuilder();
	private Font font;
	private boolean visible = false, caps = false;
	
	private SendThread st;
	
	public ChatBox(float x, float y, float w, float h, Font font, SendThread st)
	{
		box = new Quad(x,y,w,h, new RGB(1,1,1,0.5f));
		box.lockToScreen(true);
		this.st=st;
		this.font=font;
	}
	
	public void update()
	{
		if (DavisGUI.checkKey(GLFW.GLFW_KEY_T))
		{
			visible = true;
		}
		
		if (visible)
		{
			streamKeys();
			font.drawText(words.toString(), box.x, box.y);
			box.draw();
			
			if (DavisGUI.checkKey(GLFW.GLFW_KEY_ESCAPE))
			{
				words.delete(0, words.length()-1);
				visible = false;
			}
			if (DavisGUI.checkKey(GLFW.GLFW_KEY_ENTER))
			{
				PacketMessage pm = new PacketMessage(words.toString());
				st.sendPacket(pm);
				
				words.delete(0, words.length()-1);
				visible = false;
			}
		}
		
	}
	
	private void streamKeys()
	{
		for (int i = 32; i < 90; i++)
		{
			if (DavisGUI.checkKey(i))
			{
				char c = DavisGUI.glfwToChar(i);
				if (c != '\0')
				{
					words.append(caps ? Character.toUpperCase(c) : c);
				}
			}
		}
		if (DavisGUI.checkKey(GLFW.GLFW_KEY_BACKSPACE))
		{
			words.deleteCharAt(words.length()-1);
		}
		if (DavisGUI.checkKey(GLFW.GLFW_KEY_CAPS_LOCK))
		{
			caps = !caps;
		}
	}
	
	public String getMessage()
	{
		return words.toString();
	}
	
}
