package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketCoord extends Packet{

	private float x,y;
	private String username;
	
	public PacketCoord(float x, float y)
	{
		super(null, Global.COORD);
		
		data = id + "," + x + "," + y + ",";
	}
	
	public PacketCoord(CharSequence data)
	{
		super(data, Global.COORD);
		
		String sp[] = data.toString().split(",");
		username = sp[1];
		x = Float.parseFloat(sp[2]);
		y = Float.parseFloat(sp[3]);
	}
	
	public String getUsername()
	{
		return username;
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
