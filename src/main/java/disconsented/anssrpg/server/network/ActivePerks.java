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

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.util.ArrayList;

/**
 * Created by j on 30/08/2015.
 */
public class ActivePerks implements IMessage {
    public ArrayList<String> activePerks;

    public ActivePerks() {
    }

    public ActivePerks(ArrayList<String> activePerks) {
        this.activePerks = activePerks;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.activePerks.size());
        for (String string : this.activePerks)
            ByteBufUtils.writeUTF8String(buf, string);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        int i = buf.readInt();
        this.activePerks = new ArrayList<>();
        for (int j = 0; j < i; j++) {
            this.activePerks.add(ByteBufUtils.readUTF8String(buf));
        }

    }
}