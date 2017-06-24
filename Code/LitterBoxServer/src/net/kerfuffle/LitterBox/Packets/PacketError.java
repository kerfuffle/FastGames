package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.LitterBox.Global;

public class PacketError extends Packet{

	public PacketError(int type, String message)
	{
		super(null, Global.ERROR);
		
		data = id + "," + type + "," + message + ",";
	}
	
}
