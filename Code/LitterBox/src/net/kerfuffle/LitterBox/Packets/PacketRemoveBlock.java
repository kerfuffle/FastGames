package net.kerfuffle.LitterBox.Packets;

import net.kerfuffle.LitterBox.Global;
import net.kerfuffle.Utilities.Network.Packet;

public class PacketRemoveBlock extends Packet{

	private int idElement;
	
	public PacketRemoveBlock(int id)
	{
		super(null, Global.REMOVE_BLOCK);
		
		data = super.id + "," + id + ",";
	}
	
	public PacketRemoveBlock(CharSequence data)
	{
		super(data, Global.REMOVE_BLOCK);
		String sp[] = data.toString().split(",");
		idElement = Integer.parseInt(sp[1]);
	}
	
	public int getElementId()
	{
		return idElement;
	}
	
}
