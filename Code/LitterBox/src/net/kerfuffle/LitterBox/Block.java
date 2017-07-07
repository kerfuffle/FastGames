package net.kerfuffle.LitterBox;

import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Quad;

public class Block implements GameElement{

	private Quad box;
	private boolean canCollide = false;
	private int id = -1;

	public Block(Quad quad, boolean canCollide)
	{
		this.box=fixQuad(quad);
		this.canCollide = canCollide;
	}
	public Block(Quad quad, boolean canCollide, int id)
	{
		this.box=fixQuad(quad);
		this.canCollide = canCollide;
		this.id=id;
	}
	
	private Quad fixQuad(Quad q)
	{
		Quad ret = new Quad(q.x,q.y,q.w,q.h,q.getColor());
		if (q.w < 0)
		{
			ret.x = q.x+q.w;
			ret.w = q.w*-1;
		}
		if (q.h < 0)
		{
			ret.y = q.y+q.h;
			ret.h = q.h*-1;
		}
		
		return ret;
	}
	
	public boolean canCollide()
	{
		return canCollide;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void update()
	{
		box.draw();
	}
	
	public Quad getBox()
	{
		return box;
	}

	public String getSendData()
	{
		return box.x+"/"+box.y+"/"+box.w+"/"+box.h+"/"+box.getColor().R()+"/"+box.getColor().G()+"/"+box.getColor().B()+"/"+DavisGUI.boolToInt(canCollide)+"/";
	}
	

}
