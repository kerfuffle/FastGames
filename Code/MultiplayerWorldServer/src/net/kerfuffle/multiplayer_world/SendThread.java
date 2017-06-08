package net.kerfuffle.multiplayer_world;

import java.io.IOException;
import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.Packet;

public class SendThread implements Runnable{
	
	private Thread t;
	private volatile boolean running = false;
	
	private Server server;
	private Packet packet;
	private InetAddress ip;
	private int port;
	private boolean send = false, toAllExcept = false;
	
	public SendThread(Server server)
	{
		this.server = server;
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
					server.sendToUser(packet, ip, port);
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				send = false;
			}
			
			if (toAllExcept)
			{
				try
				{
					server.sendToAllUsersExcept(packet, ip, port);
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				toAllExcept = false;
			}
		}
	}
	
	public void close()
	{
		running = false;
	}
	
	public void sendToAllExcept(Packet p, InetAddress ip, int port)
	{
		packet = p;
		this.ip=ip;
		this.port=port;
		toAllExcept = true;
	}
	
	public void sendPacket(Packet p, InetAddress ip, int port)
	{
		packet = p;
		this.ip=ip;
		this.port=port;
		send = true;
	}

}