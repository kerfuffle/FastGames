package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketLogin extends Packet{

	private String username;
	
	public PacketLogin(String username, float x, float y, float w, float h)
	{
		super(null, Global.LOGIN);
		data = id + "," + username + "," + x + "," + y + "," + w + "," + h + ",";
	}
	
	public PacketLogin(CharSequence data)
	{
		super(data, Global.LOGIN);
		
		String sp[] = data.toString().split(",");
		username = sp[1];
	}
	
	public String getUsername()
	{
		return username;
	}
	
}
