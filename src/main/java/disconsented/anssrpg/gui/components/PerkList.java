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
package disconsented.anssrpg.gui.components;

import disconsented.anssrpg.common.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Disconsented
 */
public class PerkList extends ComponentBase {

    private FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
    private ResourceLocation texture = new ResourceLocation(Reference.ID, "perklist.png");
    private int width = 256;
    private int height = 256;
    private String names[] = {"1", "2", "3", "4"};

    public PerkList(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        bindAndDrawTexture(texture, x, y, this.width, this.height);
        this.drawString(fontRenderer, names[0], 11 + this.x, this.y + 9 + 2, 0xBEFF00);
        this.drawString(fontRenderer, names[1], 11 + this.x, this.y + 69 + 2, 0xBEFF00);
        this.drawString(fontRenderer, names[2], 11 + this.x, this.y + 129 + 2, 0xBEFF00);
        this.drawString(fontRenderer, names[3], 11 + this.x, this.y + 189 + 2, 0xBEFF00);
    }

    public String[] getNames() {
        return this.names;
    }
}
