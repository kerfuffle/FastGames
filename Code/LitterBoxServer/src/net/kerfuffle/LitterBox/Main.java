package net.kerfuffle.LitterBox;

import java.net.SocketException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import net.kerfuffle.LitterBox.Packets.PacketCoord;
import net.kerfuffle.LitterBox.Packets.PacketError;
import net.kerfuffle.LitterBox.Packets.PacketLogin;
import net.kerfuffle.LitterBox.Packets.PacketMessage;
import net.kerfuffle.Utilities.Network.MyNetworkCode;
import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.Utilities.Network.Server;
import net.kerfuffle.Utilities.Network.User;

public class Main {

	private SendThread st;
	private Server server;
	
	private ArrayList<SPlayer> players = new ArrayList<SPlayer>();

	public void run() throws SocketException
	{
		int port = Integer.parseInt(JOptionPane.showInputDialog("Port."));

		server = new Server("MultiplayerServer", port);
		st = new SendThread(server);
		st.start();

		server.setMyNetworkCode(new MyNetworkCode()
		{
			public void run(Packet packet)
			{
				if (packet.getId() == Global.LOGIN)
				{
					PacketLogin p = new PacketLogin(packet.getData());
					if (validUsername(p.getUsername()))
					{
						float x = 0;//(float) (Math.random()*1000);
						float y = 0;//(float) (Math.random()*700);
						float w = 50;//(float) (Math.random()*200);
						float h = 50;//(float) (Math.random()*200);
						
						server.addUser(new User(p.getUsername(), packet.getIp(), packet.getPort()));
						players.add(new SPlayer(p.getUsername(), packet.getIp(), packet.getPort(), x, y, w, h));
						
						PacketLogin pl = new PacketLogin(p.getUsername(), x, y,w,h);
						st.sendToAll(pl);
						
						for (SPlayer sp : players)
						{
							PacketLogin oldy = new PacketLogin(sp.getUsername(), sp.getX(), sp.getY(), sp.getW(), sp.getH());
							st.sendPacket(oldy, packet.getIp(), packet.getPort());
						}
						
					}
					else
					{
						PacketError err = new PacketError(Global.LOGIN_ERROR, "Username is already taken.");
						st.sendPacket(err, packet.getIp(), packet.getPort());
					}
				}
				if (packet.getId() == Global.MESSAGE)
				{
					PacketMessage p = new PacketMessage(packet.getData());
					PacketMessage pm = new PacketMessage(server.getUsername(packet.getIp(), packet.getPort()), p.getMessage());
					st.sendToAllExcept(pm, packet.getIp(), packet.getPort());
				}
				if (packet.getId() == Global.COORD)
				{
					PacketCoord p = new PacketCoord(packet.getData());
					PacketCoord pc = new PacketCoord(server.getUsername(packet.getIp(), packet.getPort()), p.getX(), p.getY());
					
					st.sendToAllExcept(pc, packet.getIp(), packet.getPort());
				}
				if (packet.getId() == Global.DISCONNECT)
				{
					
				}
			}
		});

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