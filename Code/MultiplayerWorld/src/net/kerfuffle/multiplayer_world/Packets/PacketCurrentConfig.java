package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketCurrentConfig extends Packet{

	// current state of yourself (where the server wants to place you)
	// other players pos will just send a bunch of PacketLogin
	
	private float x, y, w, h;
	
	public PacketCurrentConfig(CharSequence data)
	{
		super(data, Global.CURRENT_CONFIG);
		String sp[] = data.toString().split(",");
		x = Float.parseFloat(sp[1]);
		y = Float.parseFloat(sp[2]);
		w = Float.parseFloat(sp[3]);
		h = Float.parseFloat(sp[4]);
	}
	
	public float getX()
	{
		return x;
	}
	public float getY()
	{
		return y;
	}
	public float getW()
	{
		return w;
	}
	public float getH()
	{
		return h;
	}
	
}
