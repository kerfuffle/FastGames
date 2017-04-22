package net.kerfuffle.FourSquare;

import org.lwjgl.glfw.GLFW;

import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Player;
import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.Network.Client;

public class Game extends DavisGUI implements Runnable{

	private static final int WIDTH = 1000, HEIGHT = 700;
	
	private Client client;
	private Thread game;
	private boolean running = false;

	private Player localPlayer;
	
	public Game (Client client)
	{
		super ("FourSquare", WIDTH, HEIGHT);
		this.client=client;
	}
	
	public void start()
	{
		running = true;
		game = new Thread(this, "Game Thread");
		game.start();
	}
	public boolean isRunning()
	{
		return running;
	}

	
	///////////////////////////////////////****************************/
	

	public void childInit() 
	{
		localPlayer = new Player(new Quad(0, 0, 50, 50, new RGB(1,1,1)), GLFW.GLFW_KEY_W, GLFW.GLFW_KEY_S, GLFW.GLFW_KEY_A, GLFW.GLFW_KEY_D, false);
		
	}
	

	public void childLoop() 
	{
		
	}

}
