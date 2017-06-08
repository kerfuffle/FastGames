package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketDisconnect extends Packet{

	private String message;
	
	public PacketDisconnect(CharSequence data)
	{
		super(data, Global.DISCONNECT);
		String sp[] = data.toString().split(",");
		message = sp[1];
	}
	
	public String getMessage()
	{
		return message;
	}
	
}
