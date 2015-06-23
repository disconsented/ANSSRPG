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
package disconsented.anssrpg.gui;

import disconsented.anssrpg.gui.components.PerkInfo;
import disconsented.anssrpg.gui.components.PerkList;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * @author Disconsented
 *
 */
public class GUIPerk extends GuiScreen {
    private PerkList perkList; 
    private PerkInfo perkInfo;

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        drawDefaultBackground();
        //176x295
        perkList.draw();
        perkInfo.draw();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch(button.id){
        case 0: //Obtain
            break;
        case 1: //Next
            break;
        case 2: //Prev
            break;
            
        }
    }

    @Override
    public void initGui() {//176
        perkList = new PerkList((width/2) - 176,(height - 176)/2);
        perkInfo = new PerkInfo(width/2,(height - 176)/2);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
