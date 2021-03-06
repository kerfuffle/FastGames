package net.kerfuffle.multiplayer_world.Packets;

import net.kerfuffle.Utilities.Network.Packet;
import net.kerfuffle.multiplayer_world.Global;

public class PacketLogin extends Packet{

	private String username;
	private float x,y,w,h;
	
	public PacketLogin(String username)
	{
		super(null, Global.LOGIN);
		data = id + "," + username + ",";
	}
	
	public PacketLogin (CharSequence data)
	{
		super(data, Global.LOGIN);
		String sp[] = data.toString().split(",");
		username = sp[1];
		x = Float.parseFloat(sp[2]);
		y = Float.parseFloat(sp[3]);
		w = Float.parseFloat(sp[4]);
		h = Float.parseFloat(sp[5]);
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
	public float getW()
	{
		return w;
	}
	public float getH()
	{
		return h;
	}
	
}
