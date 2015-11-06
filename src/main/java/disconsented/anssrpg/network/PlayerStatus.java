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

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

public class PlayerStatus implements IMessage {
    public float currentHearts = 0;
    public float currentSaturation = 0;
    public float currentArmourHelmet = 0;
    public float currentArmourChest = 0;
    public float currentArmourLeggings = 0;
    public float currentArmourBoots = 0;

    public PlayerStatus() {
    }

    public PlayerStatus(float currentHearts, float currentSaturation, float currentArmourHelmet,
                        float currentArmourChest, float currentArmourLeggings, float currentArmourBoots) {
        this.currentHearts = currentHearts;
        this.currentSaturation = currentSaturation;
        this.currentArmourHelmet = currentArmourHelmet;
        this.currentArmourChest = currentArmourChest;
        this.currentArmourLeggings = currentArmourLeggings;
        this.currentArmourBoots = currentArmourBoots;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.currentHearts = buf.readFloat();
        this.currentSaturation = buf.readFloat();
        this.currentArmourHelmet = buf.readFloat();
        this.currentArmourChest = buf.readFloat();
        this.currentArmourLeggings = buf.readFloat();
        this.currentArmourBoots = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(currentHearts);
        buf.writeFloat(currentSaturation);
        buf.writeFloat(currentArmourHelmet);
        buf.writeFloat(currentArmourChest);
        buf.writeFloat(currentArmourLeggings);
        buf.writeFloat(currentArmourBoots);
    }
}