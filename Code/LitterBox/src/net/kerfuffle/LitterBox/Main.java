package net.kerfuffle.LitterBox;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import net.kerfuffle.LitterBox.Packets.PacketCoord;
import net.kerfuffle.LitterBox.Packets.PacketDisconnect;
import net.kerfuffle.LitterBox.Packets.PacketError;
import net.kerfuffle.LitterBox.Packets.PacketLogin;
import net.kerfuffle.LitterBox.Packets.PacketMessage;
import net.kerfuffle.LitterBox.Packets.PacketNewBlock;
import net.kerfuffle.LitterBox.Packets.PacketRemoveBlock;
import net.kerfuffle.Utilities.MyCode;
import net.kerfuffle.Utilities.Network.Client;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;

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

		client = new Client("LitterBoxClient", ip, port);
		st = new SendThread(client);
		st.start();
		
		username = JOptionPane.showInputDialog("Username");
		PacketLogin login = new PacketLogin(username);
		
		st.sendPacket(login);

		game = new Game(st);

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
				if (packet.getId() == Global.MESSAGE)
				{
					PacketMessage p = new PacketMessage(packet.getData());
					game.addMessage(p.getUsername() + ": " + p.getMessage());
				}
				if (packet.getId() == Global.COORD)
				{
					PacketCoord p = new PacketCoord(packet.getData());
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
					PacketDisconnect p = new PacketDisconnect(packet.getData());
					game.addMessage("*" + p.getUsername() + " has disconnected.");
					game.removePlayer(p.getUsername());
				}
				if (packet.getId() == Global.NEW_BLOCK)
				{
					PacketNewBlock p = new PacketNewBlock(packet.getData());
					game.addGameElement(p.getBlock());
				}
				if (packet.getId() == Global.REMOVE_BLOCK)
				{
					PacketRemoveBlock p = new PacketRemoveBlock(packet.getData());
					game.removeGameElement(p.getElementId());
				}
			}
		});
		
		game.setCloseCode(new MyCode()
		{
			public void run()
			{
				PacketDisconnect pd = new PacketDisconnect("Free-will.");
				st.sendPacket(pd);
				
				st.close();
				client.close();
				
				System.exit(0);
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
