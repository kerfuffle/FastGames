package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketCurrentConfig extends Packet{

	
	public PacketCurrentConfig(float x, float y, float w, float h)
	{
		super (null, Global.CURRENT_CONFIG);
		
		data = id + "," + x + "," + y + "," + w + "," + h + ",";
	}
	
}
