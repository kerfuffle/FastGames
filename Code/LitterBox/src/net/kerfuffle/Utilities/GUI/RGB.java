package net.kerfuffle.Utilities.GUI;

public class RGB {

	private float r, g, b, a=-1;
	
	public RGB(float r, float g, float b)
	{
		this.r=r;
		this.g=g;
		this.b=b;
	}
	public RGB(float r, float g, float b, float a)
	{
		this.r=r;
		this.g=g;
		this.b=b;
		this.a=a;
	}
	
	public float R()
	{
		return r;
	}
	public float G()
	{
		return g;
	}
	public float B()
	{
		return b;
	}
	public float A()
	{
		return a;
	}
	
}
