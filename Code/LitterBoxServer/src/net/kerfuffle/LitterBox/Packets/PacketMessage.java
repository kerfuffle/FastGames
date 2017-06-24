package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketMessage extends Packet{
	
	private String message;
	
	public PacketMessage(CharSequence data)
	{
		super(data, Global.MESSAGE);
		
		String sp[] = data.toString().split(",");
		message= sp[1];
	}
	
	public PacketMessage(String username, String message)
	{
		super(null, Global.MESSAGE);
		data = id + "," + username + "," + message + ",";
	}

	public String getMessage()
	{
		return message;
	}
}
