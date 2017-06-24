package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketError extends Packet{

	private String message;
	private int type;
	
	public PacketError(CharSequence data)
	{
		super(data, Global.ERROR);
		String sp[] = data.toString().split(",");
		type = Integer.parseInt(sp[1]);
		message = sp[2];
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public int getType()
	{
		return type;
	}
	
}
