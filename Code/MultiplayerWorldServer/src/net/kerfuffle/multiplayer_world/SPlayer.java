package net.kerfuffle.multiplayer_world;

import java.net.InetAddress;

import net.kerfuffle.Utilities.Network.User;

public class SPlayer extends User{
	
	private float x,y,w,h;
	
	public SPlayer(String username, InetAddress ip, int port, float x, float y, float w, float h)
	{
		super(username, ip, port);
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;		
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
