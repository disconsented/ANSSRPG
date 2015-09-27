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

import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import disconsented.anssrpg.perk.Requirement;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class PerkInfo implements IMessage {
    private String name;
    private String description;
    private int pointCost;
    private ArrayList<Requirement> requirements;
    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> extraData = new ArrayList<String>();
    private ArrayList<Requirement.Action> actions = new ArrayList<Requirement.Action>();
    private int size;
    private String slug;
    private boolean obtained;

    public PerkInfo() {
    }

    public PerkInfo(String name, String description, String slug, int pointCost, ArrayList<Requirement> requirements, boolean obtained) {
        this.name = name;
        this.description = description;
        this.slug = slug;
        this.pointCost = pointCost;
        for (Requirement requirement : requirements){
            names.add(requirement.name);
            extraData.add(requirement.extraData);
            actions.add(requirement.action);
        }
        this.size = requirements.size();
        this.obtained = obtained;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        size = buf.readInt();
        name = ByteBufUtils.readUTF8String(buf);
        description = ByteBufUtils.readUTF8String(buf);
        slug = ByteBufUtils.readUTF8String(buf);
        pointCost = buf.readInt();
        requirements = new ArrayList<Requirement>();
        for (int i = 0; i < size; i++) {
            requirements.add(new Requirement(Requirement.Action.valueOf(ByteBufUtils.readUTF8String(buf)), ByteBufUtils.readUTF8String(buf),ByteBufUtils.readUTF8String(buf)));
        }
        obtained = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(size);
        ByteBufUtils.writeUTF8String(buf, getName());
        ByteBufUtils.writeUTF8String(buf, getDescription());
        ByteBufUtils.writeUTF8String(buf, getSlug());
        buf.writeInt(getPointCost());
        for (int i = 0; i < names.size(); i++) {
            ByteBufUtils.writeUTF8String(buf, actions.get(i).name());
            ByteBufUtils.writeUTF8String(buf, names.get(i));
            ByteBufUtils.writeUTF8String(buf, extraData.get(i));
        }
        buf.writeBoolean(isObtained());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPointCost() {
        return pointCost;
    }

    public ArrayList<Requirement> getRequirements() {
        ArrayList<Requirement> req = new ArrayList<Requirement>();
        for (int i = 0; i < names.size(); i++) {
            req.add(new Requirement(actions.get(i),names.get(i),extraData.get(i)));
        }
        return req;
    }

    public String getSlug() {
        return slug;
    }

    public boolean isObtained() {
        return obtained;
    }
}
