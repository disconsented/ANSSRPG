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
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * @author Disconsented
 */
public class PerkControls extends ComponentBase{
    private final ResourceLocation texture = new ResourceLocation(Reference.ID, "perkinfo.png");
    private final int width = 256;
    private final int height = 256;
    public String status = "Status";
    public String description = "Description";
    public PerkControls(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void draw() {
        this.bindAndDrawTexture(this.texture, this.x, this.y, width, height);
        this.fontRenderer.drawSplitString(this.status, 11 + x, 2 + 181 + y, 156, 0xBEFF00);//9,181 158x28
        this.fontRenderer.drawSplitString(this.description, 11 + x, 2 + 9 + y, 156, 0xBEFF00); //9,9 158x164
    }


}
