package net.kerfuffle.multiplayer_world;

import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;
import net.kerfuffle.multiplayer_world.Packets.PacketCurrentConfig;
import net.kerfuffle.multiplayer_world.Packets.PacketError;
import net.kerfuffle.multiplayer_world.Packets.PacketLogin;
import net.kerfuffle.multiplayer_world.Packets.PacketMessage;
import net.kerfuffle.multiplayer_world.Packets.PacketMove;

public class Main {

	private SendThread st;
	private Server server;
	
	private ArrayList<SPlayer> players = new ArrayList<SPlayer>();

	public void run() throws SocketException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port."));

		server = new Server("MultiplayerServer", port);
		st = new SendThread(server);

		server.setMyNetworkCode(new MyNetworkCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.LOGIN)
				{
					System.out.println(packet.getIp());
					PacketLogin p = new PacketLogin(packet.getData());
					if (validUsername(p.getUsername()))
					{
						
						for (SPlayer sp : players)
						{
							PacketLogin oldy = new PacketLogin(sp.getUsername(), sp.getX(), sp.getY(), sp.getW(), sp.getH());
							st.sendPacket(oldy, packet.getIp(), packet.getPort());
						}
						
						float x = (float) (Math.random()*1000);
						float y = (float) (Math.random()*700);
						float w = 50;
						float h = 50;
						
						server.addUser(new User(p.getUsername(), packet.getIp(), packet.getPort()));
						players.add(new SPlayer(p.getUsername(), packet.getIp(), packet.getPort(), x, y, w, h));
						
						PacketCurrentConfig pcc = new PacketCurrentConfig(x,y,w,h);
						st.sendPacket(pcc, packet.getIp(), packet.getPort());
						
						PacketLogin pl = new PacketLogin(p.getUsername(), x, y,w,h);
						st.sendToAllExcept(pl, packet.getIp(), packet.getPort());
					}
					else
					{
						PacketError err = new PacketError("Username is already taken.");
						st.sendPacket(err, p.getIp(), p.getPort());
					}
				}
				if (packet.getId() == Global.MESSAGE)
				{
					PacketMessage p = new PacketMessage(packet.getData());
					PacketMessage pm = new PacketMessage(server.getUsername(packet.getIp(), packet.getPort()), p.getMessage());
					st.sendToAllExcept(pm, packet.getIp(), packet.getPort());
				}
				if (packet.getId() == Global.MOVE)
				{
					PacketMove p = new PacketMove(packet.getData());
					st.sendToAllExcept(p, packet.getIp(), packet.getPort());
				}
				if (packet.getId() == Global.DISCONNECT)
				{
					
				}
			}
		});

		st.start();
		server.start();
	}
	
	private boolean validUsername(String username)
	{
		for (SPlayer sp : players)
		{
			if (sp.getUsername().equals(username))
			{
				return false;
			}
		}
		return true;
	}


	public static void main(String args[]) throws SocketException
	{
		new Main().run();
	}

}