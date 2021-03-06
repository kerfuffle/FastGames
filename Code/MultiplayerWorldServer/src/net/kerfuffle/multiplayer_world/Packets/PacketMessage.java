package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketMessage extends Packet{

	private String message;
	
	public PacketMessage (CharSequence data)
	{
		super(data, Global.MESSAGE);
		String sp[] = data.toString().split(",");
		message = sp[1];
	}
	
	public PacketMessage (String username, String message)
	{
		super(null, Global.MESSAGE);
		
		data = id + "," + username + "," + message + ",";
	}
	
	public String getMessage()
	{
		return message;
	}
}
