package net.kerfuffle.LitterBox;

import java.io.IOException;
import java.util.ArrayList;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.Packet;

public class SendThread implements Runnable{
	
	private Thread t;
	private volatile boolean running = false;
	
	private Client client;
	private ArrayList<Packet> sendBuffer = new ArrayList<Packet>();
	
	public SendThread(Client client)
	{
		this.client = client;
	}
	
	
	public void start()
	{
		running = true;
		t = new Thread(this, "LitterBoxSend");
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
						client.sendPacket(sendBuffer.get(0));
						sendBuffer.remove(0);
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
		client.close();
	}
	
	public void sendPacket(Packet p)
	{
		sendBuffer.add(p);
	}

}
