package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketMove extends Packet{

	private String username;
	private int direction;
	
	public PacketMove(int direction)
	{
		super (null, Global.MOVE);
		data = id + "," + direction + ",";
	}
	
	public PacketMove (CharSequence data)
	{
		super (data, Global.MOVE);
		String sp[] = data.toString().split(",");
		username = sp[1];
		direction = Integer.parseInt(sp[2]);
	}
	
	public String getUsername()
	{
		return username;
	}
	public int getDirection()
	{
		return direction;
	}
	
}
