package net.kerfuffle.FourSquare;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyCode;
import net.kerfuffle.Utilities.Network.Packet;

public class Main {

	private Client client;
	private Game game;

	public void run() throws UnknownHostException, SocketException
	{
		String in = JOptionPane.showInputDialog("IP:Port");
		String sp[] = in.split(":");
		InetAddress ip = InetAddress.getByName(sp[0]);
		int port = Integer.parseInt(sp[1]);

		client = new Client("Client", ip, port);

		client.setMyCode(new MyCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.LOGIN)
				{
					
				}
			}
		});


		game = new Game(client);
	}

	public static void main(String args[]) throws UnknownHostException, SocketException
	{
		Main main = new Main();
		main.run();
	}

}
