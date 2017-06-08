package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketMove extends Packet{

	public PacketMove(CharSequence data)
	{
		super(data, Global.MOVE);
	}
	

}
