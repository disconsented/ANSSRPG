package disconsented.anssrpg.network;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PerkInfo implements IMessage{    
    public String name;
    public String description;
    public int pointCost;
    public ArrayList<String> requirements;
    public int size;
	public String slug;

    public PerkInfo() {}

    public PerkInfo(String name, String description, String slug, int pointCost, ArrayList<String> requirementName, ArrayList<Integer> requirementLevel)  {
    	this.name = name;
    	this.description = description;
    	this.slug = slug;
        this.pointCost = pointCost;
        this.requirements = new ArrayList<String>();
        for (int i = 0; i < requirementName.size(); i++){
        	requirements.add(requirementName.get(i)+":"+requirementLevel.get(i));
        }
        this.size = requirements.size();
    }

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(size);
    	ByteBufUtils.writeUTF8String(buf, name);
    	ByteBufUtils.writeUTF8String(buf, description);
    	ByteBufUtils.writeUTF8String(buf, slug);
        buf.writeInt(pointCost);
        for (int i = 0; i < requirements.size(); i++){
        	ByteBufUtils.writeUTF8String(buf, requirements.get(i));
        }        
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.size = buf.readInt();
    	this.name = ByteBufUtils.readUTF8String(buf);
    	this.description = ByteBufUtils.readUTF8String(buf); 
    	this.slug = ByteBufUtils.readUTF8String(buf);
        this.pointCost = buf.readInt();
        this.requirements = new ArrayList<String>();
        for (int i = 0; i < size; i++){
        	this.requirements.add(ByteBufUtils.readUTF8String(buf));
        }
    }
}
