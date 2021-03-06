package net.kerfuffle.multiplayer_world;

import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Text.Font;
import net.kerfuffle.Utilities.Network.Client;

public class Game extends DavisGUI{

	private ArrayList<PlayerMP> players = new ArrayList<PlayerMP>();
	private SendThread st;
	private boolean inited = false;
	
	private Font chatFont;
	
	
	private Player player;
	
	public Game(SendThread st)
	{
		super("MultiplayerGame", 1000, 700);
		this.st=st;
	}
	
	
	public void childInit()
	{
		try {
			chatFont = new Font((new FileInputStream("res/Helvetica.ttf")), 24);
		} catch (FontFormatException | IOException e) {chatFont = new Font();}
		
		if (player != null)
		{
			player.setFont(chatFont);
		}
		for (PlayerMP pmp : players)
		{
			pmp.setFont(chatFont);
		}
	}
	public void childLoop()
	{
		if (player != null)
		{
			player.update();
		}
		
		
		for (PlayerMP pmp : players)
		{
			pmp.draw();
		}
	}
	
	public void setLocalPlayer(String username, float x, float y, float w, float h)
	{
		player = new Player(username, x, y, w, h, chatFont, st);
	}
	
	public void addPlayer(String username, float x, float y, float w, float h)
	{
		players.add(new PlayerMP(username, x, y, w, h, chatFont));
	}
	public void movePlayer(String username, int direction)
	{
		for (PlayerMP p : players)
		{
			if (p.getUsername().equals(username))
			{
				p.move(direction);
				return;
			}
		}
	}
	public void setPlayerPos(String username, float x, float y)
	{
		for (PlayerMP p : players)
		{
			if (p.getUsername().equals(username))
			{
				p.box.x = x;
				p.box.y = y;
				return;
			}
		}
	}
	
	//TODO
	public void showMessage(String str)
	{
		
	}
	
}
