package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketError extends Packet{

	public PacketError(int type, String message)
	{
		super(null, Global.ERROR);
		
		data = id + "," + type + "," + message + ",";
	}
	
}
