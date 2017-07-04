package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketDisconnect extends Packet{

	private String username;
	
	public PacketDisconnect(CharSequence data)
	{
		super(data, Global.DISCONNECT);
		String sp[] = data.toString().split(",");
		username = sp[1];
	}
	
	public PacketDisconnect(String message)
	{
		super(null, Global.DISCONNECT);
		data = id + "," + message + ",";
	}
	
	public String getUsername()
	{
		return username;
	}
	
}
