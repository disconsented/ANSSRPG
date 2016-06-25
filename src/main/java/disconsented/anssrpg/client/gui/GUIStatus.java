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
package disconsented.anssrpg.client.gui;

import disconsented.anssrpg.Main;
import disconsented.anssrpg.client.gui.components.ComponentStatusBook;
import disconsented.anssrpg.server.network.Request;
import disconsented.anssrpg.server.network.Request.REQUEST;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * Created by Disconsented on 22/08/2015.
 */
public class GUIStatus extends GuiScreen {
    ComponentStatusBook status;


    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        //drawDefaultBackground();
        //(width/2) - 176, (height - 240)/2
        this.status.draw();

    }

    @Override
    protected void actionPerformed(GuiButton button) {
    }

    @Override
    public void initGui() {
        int x = this.width / 2;
        int y = this.height / 2;
        this.status = new ComponentStatusBook(x, y);
        Main.snw.sendToServer(new Request(REQUEST.START_TRACKING));
    }

    //Stops sends the stop tracking packet
    @Override
    public void onGuiClosed() {
        Main.snw.sendToServer(new Request(REQUEST.STOP_TRACKING));
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
