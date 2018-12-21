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
package com.disconsented.anssrpg.client.gui.components;

import com.disconsented.anssrpg.server.common.Reference;
import net.minecraft.util.ResourceLocation;

/**
 * @author Disconsented
 */
public class PerkList extends ComponentBase {
    private final ResourceLocation texture = new ResourceLocation(Reference.ID, "perklist.png");
    private final int width = 256;
    private final int height = 256;
    private final String[] names = {"1", "2", "3", "4"};

    public PerkList(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        this.bindAndDrawTexture(this.texture, this.x, this.y, width, height);
        drawString(this.fontRenderer, this.names[0], 11 + x, y + 9 + 2, 0xBEFF00);
        drawString(this.fontRenderer, this.names[1], 11 + x, y + 69 + 2, 0xBEFF00);
        drawString(this.fontRenderer, this.names[2], 11 + x, y + 129 + 2, 0xBEFF00);
        drawString(this.fontRenderer, this.names[3], 11 + x, y + 189 + 2, 0xBEFF00);
    }

    public String[] getNames() {
        return names;
    }
}
