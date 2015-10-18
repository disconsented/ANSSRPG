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
package disconsented.anssrpg.server.network;

import disconsented.anssrpg.server.perk.Requirement.Action;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import disconsented.anssrpg.server.perk.Requirement;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

public class PerkInfo implements IMessage {
    private String name;
    private String description;
    private int pointCost;
    private ArrayList<Requirement> requirements;
    private final ArrayList<String> names = new ArrayList<String>();
    private final ArrayList<String> extraData = new ArrayList<String>();
    private final ArrayList<Action> actions = new ArrayList<Action>();
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
            this.names.add(requirement.name);
            this.extraData.add(requirement.extraData);
            this.actions.add(requirement.action);
        }
        size = requirements.size();
        this.obtained = obtained;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.size = buf.readInt();
        this.name = ByteBufUtils.readUTF8String(buf);
        this.description = ByteBufUtils.readUTF8String(buf);
        this.slug = ByteBufUtils.readUTF8String(buf);
        this.pointCost = buf.readInt();
        this.requirements = new ArrayList<Requirement>();
        for (int i = 0; i < this.size; i++) {
            this.requirements.add(new Requirement(Action.valueOf(ByteBufUtils.readUTF8String(buf)), ByteBufUtils.readUTF8String(buf), ByteBufUtils.readUTF8String(buf)));
        }
        this.obtained = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.size);
        ByteBufUtils.writeUTF8String(buf, this.getName());
        ByteBufUtils.writeUTF8String(buf, this.getDescription());
        ByteBufUtils.writeUTF8String(buf, this.getSlug());
        buf.writeInt(this.getPointCost());
        for (int i = 0; i < this.names.size(); i++) {
            ByteBufUtils.writeUTF8String(buf, this.actions.get(i).name());
            ByteBufUtils.writeUTF8String(buf, this.names.get(i));
            ByteBufUtils.writeUTF8String(buf, this.extraData.get(i));
        }
        buf.writeBoolean(this.isObtained());
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPointCost() {
        return this.pointCost;
    }

    public ArrayList<Requirement> getRequirements() {
        ArrayList<Requirement> req = new ArrayList<Requirement>();
        for (int i = 0; i < this.names.size(); i++) {
            req.add(new Requirement(this.actions.get(i), this.names.get(i), this.extraData.get(i)));
        }
        return req;
    }

    public String getSlug() {
        return this.slug;
    }

    public boolean isObtained() {
        return this.obtained;
    }
}
