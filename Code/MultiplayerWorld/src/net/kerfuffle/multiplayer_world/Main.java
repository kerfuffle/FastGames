package net.kerfuffle.multiplayer_world;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.MyCode;
import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Packets.PacketCoord;
import net.kerfuffle.multiplayer_world.Packets.PacketCurrentConfig;
import net.kerfuffle.multiplayer_world.Packets.PacketError;
import net.kerfuffle.multiplayer_world.Packets.PacketLogin;
import net.kerfuffle.multiplayer_world.Packets.PacketMessage;
import net.kerfuffle.multiplayer_world.Packets.PacketMove;

public class Main {

	private Game game;
	private Client client;
	private SendThread st;
	private String username;

	public void run() throws SocketException, UnknownHostException
	{
		String in = JOptionPane.showInputDialog("IP:Port");
		String sp[] = in.split(":");
		InetAddress ip = InetAddress.getByName(sp[0]);
		int port = Integer.parseInt(sp[1]);

		client = new Client("MultiplayerWorldClient", ip, port);
		st = new SendThread(client);
		st.start();
		
		username = JOptionPane.showInputDialog("Username");
		PacketLogin login = new PacketLogin(username);
		
		st.sendPacket(login);
		

		game = new Game(st);

		game.setCloseCode(new MyCode()
		{
			public void run()
			{
				client.close();
				st.close();
			}
		});

		client.setMyNetworkCode(new MyNetworkCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.LOGIN)
				{
					PacketLogin p = new PacketLogin(packet.getData());
					if (p.getUsername().equals(username))
					{
						game.setLocalPlayer(username, p.getX(), p.getY(), p.getW(), p.getH());	
					}
					else
					{
						game.addPlayer(p.getUsername(), p.getX(), p.getY(), p.getW(), p.getH());
					}
					
				}
				if (packet.getId() == Global.MOVE)
				{
					PacketMove p = new PacketMove(packet.getData());
					game.movePlayer(p.getUsername(), p.getDirection());
				}
				if (packet.getId() == Global.MESSAGE)
				{
					PacketMessage p = new PacketMessage(packet.getData());
					game.showMessage(p.getUsername() + ": " + p.getMessage());
				}
//				if (packet.getId() == Global.CURRENT_CONFIG)
//				{
//					System.out.println("received current config");
//					PacketCurrentConfig p = new PacketCurrentConfig(packet.getData());
//								
//				}
				if (packet.getId() == Global.COORD)
				{
					PacketCoord p = new PacketCoord(packet.getData());
					System.out.format("%s: %f, %f\n", p.getUsername(), p.getX(), p.getY());
					game.setPlayerPos(p.getUsername(), p.getX(), p.getY());
				}
				if (packet.getId() == Global.ERROR)
				{
					PacketError p = new PacketError(packet.getData());
					JOptionPane.showMessageDialog(null, p.getMessage());
					
					if (p.getType() == Global.LOGIN_ERROR)
					{
						username = JOptionPane.showInputDialog("Username");
						PacketLogin login = new PacketLogin(username);
						
						st.sendPacket(login);
					}
				}
				if (packet.getId() == Global.DISCONNECT)
				{
					
				}
			}
		});
		
		client.start();
		game.run();
		
	}

	public static void main (String args[]) throws SocketException, UnknownHostException
	{
		new Main().run();
	}
}
