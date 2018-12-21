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
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

/**
 * Created by j on 30/08/2015.
 */
public class PlayerStatus implements IMessage {
    public float currentHearts;
    public float currentSaturation;
    public float currentArmourHelmet;
    public float currentArmourChest;
    public float currentArmourLeggings;
    public float currentArmourBoots;

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
        currentHearts = buf.readFloat();
        currentSaturation = buf.readFloat();
        currentArmourHelmet = buf.readFloat();
        currentArmourChest = buf.readFloat();
        currentArmourLeggings = buf.readFloat();
        currentArmourBoots = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(this.currentHearts);
        buf.writeFloat(this.currentSaturation);
        buf.writeFloat(this.currentArmourHelmet);
        buf.writeFloat(this.currentArmourChest);
        buf.writeFloat(this.currentArmourLeggings);
        buf.writeFloat(this.currentArmourBoots);
    }
}