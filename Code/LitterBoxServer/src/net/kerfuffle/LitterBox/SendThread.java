package net.kerfuffle.LitterBox;

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
	private ArrayList<Packet> allExceptBuffer = new ArrayList<Packet>();

	private Server server;

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

			if (allExceptBuffer.size() > 0)
			{
				try
				{	
					if (allExceptBuffer.get(0) != null)
					{
						server.sendToAllUsersExcept(allExceptBuffer.get(0), allExceptBuffer.get(0).getIp(), allExceptBuffer.get(0).getPort());
						allExceptBuffer.remove(0);
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
						server.sendToAllUsers(allBuffer.get(0));
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
		allExceptBuffer.add(new Packet(p.getData(), ip, port));
	}
	
	public void sendToAll(Packet p)
	{
		allBuffer.add(p);
	}

	public void sendPacket(Packet p, InetAddress ip, int port)
	{
		sendBuffer.add(new Packet(p.getData(), ip, port));
	}

}
