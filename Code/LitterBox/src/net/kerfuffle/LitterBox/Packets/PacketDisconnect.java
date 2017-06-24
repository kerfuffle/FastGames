package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

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
