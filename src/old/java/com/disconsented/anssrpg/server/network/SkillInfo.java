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
package com.disconsented.anssrpg.server.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class SkillInfo implements IMessage {
    public String name;
    public int expCurrent;
    public int expRequired;
    public int levelCurrent;
    public int expOld;

    public SkillInfo() {
    }

    public SkillInfo(String name, int expCurrent, int expRequired, int levelCurrent, int expOld) {
        this.name = name;
        this.expCurrent = expCurrent;
        this.expRequired = expRequired;
        this.levelCurrent = levelCurrent;
        this.expOld = expOld;

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        name = ByteBufUtils.readUTF8String(buf);
        expCurrent = buf.readInt();
        expRequired = buf.readInt();
        levelCurrent = buf.readInt();
        expOld = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, name);
        buf.writeInt(this.expCurrent);
        buf.writeInt(this.expRequired);
        buf.writeInt(this.levelCurrent);
        buf.writeInt(this.expOld);

    }

}
