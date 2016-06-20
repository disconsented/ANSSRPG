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
package disconsented.anssrpg.client.gui.components;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

/**
 * @author Disconsented
 *
 */
public abstract class ComponentBase extends Gui{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
    
    public abstract void draw();
    public void bindAndDrawTexture(ResourceLocation texture, int posX, int posY, int startX, int startY, int width, int height){
        GlStateManager.color(255,255,255);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(posX, posY, startX, startY, width, height);
    }
    public void bindAndDrawTexture(ResourceLocation texture, int posX, int posY, int width, int height){
        GlStateManager.color(255,255,255);
        FMLClientHandler.instance().getClient().renderEngine.bindTexture(texture);
        this.drawTexturedModalRect(posX, posY, 0, 0, width, height);
    }
}
