package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Block;
import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.GUI.DavisGUI;
import net.kerfuffle.Utilities.GUI.Quad;
import net.kerfuffle.Utilities.GUI.RGB;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketNewBlock extends Packet{

	private Block block;
	
	public PacketNewBlock(CharSequence data)
	{
		super(data, Global.NEW_BLOCK);
		String sp0[] = data.toString().split(",");
		String sp[] = sp0[1].split("/");
		float x = Float.parseFloat(sp[0]);
		float y = Float.parseFloat(sp[1]);
		float w = Float.parseFloat(sp[2]);
		float h = Float.parseFloat(sp[3]);
		float r = Float.parseFloat(sp[4]);
		float g = Float.parseFloat(sp[5]);
		float b = Float.parseFloat(sp[6]);
		boolean canCollide = DavisGUI.intToBool(Integer.parseInt(sp[7]));
		int id = Integer.parseInt(sp0[2]);
		
		block = new Block(new Quad(x,y,w,h,new RGB(r,g,b)), canCollide, id);
	}
	
	public PacketNewBlock(Block b)
	{
		super(null, Global.NEW_BLOCK);
		data = id + "," + b.getSendData() + ",";
	}
	
	
	public Block getBlock()
	{
		return block;
	}
	
	

}
