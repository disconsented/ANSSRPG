/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
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
