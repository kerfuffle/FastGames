package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketMessage extends Packet{

	private String message;
	private String username;
	
	public PacketMessage(String message)
	{
		super(null, Global.MESSAGE);
		data = id + "," + message + ",";
	}
	public PacketMessage (CharSequence data)
	{
		super(data, Global.MESSAGE);
		String sp[] = data.toString().split(",");
		username = sp[1];
		message = sp[2];
	}
	
	public String getMessage()
	{
		return message;
	}
	public String getUsername()
	{
		return username;
	}
	
}
