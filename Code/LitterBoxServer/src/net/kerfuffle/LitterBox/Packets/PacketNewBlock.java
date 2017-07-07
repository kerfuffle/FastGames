package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewBlock extends Packet{

	public PacketNewBlock (CharSequence data)
	{
		super(data, Global.NEW_BLOCK);
	}
	
	public PacketNewBlock(String data, int id)
	{
		super(null, Global.NEW_BLOCK);
		super.data = data + id + ",";
	}

}
