package net.kerfuffle.LitterBox;

import java.awt.FontFormatException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.GUI.Text.Font;
import net.kerfuffle.Utilities.Network.Client;

public class Game extends DavisGUI{

	private ArrayList<GameElement> gameElements = new ArrayList<GameElement>();
	
	
	
	private ArrayList<Message> messages = new ArrayList<Message>();
	private ArrayList<PlayerMP> players = new ArrayList<PlayerMP>();
	private SendThread st;

	private Font chatFont;
	private Player player;
	private ChatBox cb;

	private String username="!";
	private float x,y,w,h;
	private boolean playerNeedInit = false;

	private boolean initialized = false;

	public static final int WIDTH = 1000, HEIGHT = 700;

	public Game(SendThread st)
	{
		super("LitterBox", WIDTH, HEIGHT);
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

		cb = new ChatBox(-WIDTH/2, -HEIGHT/2, WIDTH, 50, chatFont, st);

		if (username.equals("!"))
		{
			playerNeedInit = true;
		}
		else
		{
			player = new Player(username, new Quad(x,y,w,h, new RGB(1,1,1)), chatFont, st);
		}
	}


	public void childLoop()
	{
		if (playerNeedInit)
		{
			player = new Player(username, new Quad(x,y,w,h, new RGB(1,1,1)), chatFont, st);
			playerNeedInit = false;
		}

		if (player != null)
		{
			player.update();
		}

		for (PlayerMP pmp : players)
		{
			pmp.draw();
		}
		for (GameElement ge : gameElements)
		{
			ge.update();
		}

		checkMessages();
		cb.update();

		initialized = true;
	}

	public void setLocalPlayer(String username, float x, float y, float w, float h)
	{
		this.username=username;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
	}

	public void addPlayer(String username, float x, float y, float w, float h)
	{
		players.add(new PlayerMP(username, x, y, w, h, chatFont));
	}
	public void removePlayer(String username)
	{
		PlayerMP removeMe = null;
		for (PlayerMP p : players)
		{
			if (p.getUsername().equals(username))
			{
				removeMe = p;	//Gotta avoid that concurrent modification
				break;
			}
		}
		
		players.remove(removeMe);
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

	public void addMessage(String str)
	{
		float y = (messages.size() > 0) ? messages.get(messages.size()-1).getY() - 30 : player.getY()+(HEIGHT/2)-50;
		messages.add(new Message(str, player.getX()-WIDTH/2 + 50, y, 600, chatFont));
	}


	private ArrayList<Message> messagesToRemove = new ArrayList<Message>();		/**For concurrent modification bull*/
	private void checkMessages()
	{
		for (Message m : messages)
		{
			if (!m.isAlive())
			{
				messagesToRemove.add(m);
			}
		}
		
		for (Message m : messagesToRemove)
		{
			messages.remove(m);
		}
		messagesToRemove.clear();
		
		for (Message m : messages)
		{
			m.draw();
		}
	}
	
	
	
	public void addGameElement(GameElement ge)
	{
		gameElements.add(ge);
	}
	public void removeGameElement(GameElement ge)
	{
		gameElements.remove(ge);
	}
	public void removeGameElement(int i)
	{
		gameElements.remove(i);
	}
}
