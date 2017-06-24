package net.kerfuffle.multiplayer_world;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.Packet;


public class SendThread implements Runnable{

	private Thread t;
	private volatile boolean running = false;

	private ArrayList<Packet> sendBuffer = new ArrayList<Packet>();
	private ArrayList<Packet> allBuffer = new ArrayList<Packet>();

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

			if (sendBuffer.size() > 0)
			{
				try
				{
					if (sendBuffer.get(0) != null)
					{
						server.sendToUser(sendBuffer.get(0), sendBuffer.get(0).getIp(), sendBuffer.get(0).getPort());
						sendBuffer.remove(0);
					}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}

	


			if (allBuffer.size() > 0)
			{
				try
				{	
					if (allBuffer.get(0) != null)
					{
						server.sendToAllUsersExcept(allBuffer.get(0), allBuffer.get(0).getIp(), allBuffer.get(0).getPort());
						allBuffer.remove(0);
					}
				}
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		

		}
	}

	public void close()
	{
		running = false;
	}

	public void sendToAllExcept(Packet p, InetAddress ip, int port)
	{
		allBuffer.add(new Packet(p.getData(), ip, port));
		toAllExcept = true;
	}

	public void sendPacket(Packet p, InetAddress ip, int port)
	{
		sendBuffer.add(new Packet(p.getData(), ip, port));
		send = true;
	}

}
