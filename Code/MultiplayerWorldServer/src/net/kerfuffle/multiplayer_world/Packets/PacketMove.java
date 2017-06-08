package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketMove extends Packet{

	private int direction;
	
	public PacketMove(CharSequence data)
	{
		super(data, Global.MOVE);
		String sp[] = data.toString().split(",");
		direction = Integer.parseInt(sp[1]);
	}
	
	public PacketMove(String username, int direction)
	{
		super(null, Global.MOVE);
		data = id + "," + username + "," + direction + ",";
	}
	
	public int getDirection()
	{
		return direction;
	}
	

}
