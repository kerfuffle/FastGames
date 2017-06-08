package net.kerfuffle.multiplayer_world;

import java.io.IOException;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.Packet;

public class SendThread implements Runnable{
	
	private Thread t;
	private volatile boolean running = false;
	
	private Client client;
	private Packet packet;
	private boolean send = false;
	
	public SendThread(Client client)
	{
		this.client = client;
	}
	
	
	public void start()
	{
		running = true;
		t = new Thread(this, "MultiplayerSend");
		t.start();
	}
	
	public void run()
	{
		while (running)
		{
			if (send)
			{
				try
				{
					client.sendPacket(packet);
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				send = false;
			}
		}
	}
	
	public void close()
	{
		running = false;
	}
	
	public void sendPacket(Packet p)
	{
		packet = p;
		send = true;
	}

}
