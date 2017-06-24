package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketCoord extends Packet{

	private float x,y;
	
	
	public PacketCoord(String username, float x, float y)
	{
		super(null, Global.COORD);
		
		data = id + "," + username + "," + x + "," + y + ",";
	}
	
	public PacketCoord(CharSequence data)
	{
		super(data, Global.COORD);
		
		String sp[] = data.toString().split(",");
		x = Float.parseFloat(sp[1]);
		y = Float.parseFloat(sp[2]);
	}
	
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
}
