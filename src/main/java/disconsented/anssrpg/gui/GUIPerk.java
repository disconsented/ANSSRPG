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

import disconsented.anssrpg.Main;
import disconsented.anssrpg.client.Data;
import disconsented.anssrpg.gui.components.PerkControls;
import disconsented.anssrpg.gui.components.PerkList;
import disconsented.anssrpg.network.PerkInfo;
import disconsented.anssrpg.network.PerkRequest;
import disconsented.anssrpg.network.Request;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GUIPerk extends GuiScreen {

    private PerkList perkList;
    private PerkControls perkControls;
    private int page = 0;
    private PerkInfo selected = null;
    private ArrayList<PerkInfo> info = new ArrayList<PerkInfo>();
    private List<PerkInfo> subList = new ArrayList<>();

    @Override
    @SuppressWarnings("unchecked")
    public void initGui() {//176
        this.buttonList.clear();

        //Clear park data on the client and request new info
        Data.perkInfo.clear();
        Data.statusMessage = "";
        Main.snw.sendToServer(new Request(Request.REQUEST.PERKS));
        perkList = new PerkList((width / 2) - 176, (height - 240) / 2);
        //39,36
        //39,96
        //39,156
        //39,216
        perkControls = new PerkControls(width / 2, (height - 240) / 2);
        //id,x,y,width,height,string

        //Order of when these are added matters as I retrieve them by their index not id
        int commonX = (width / 2) + 7;
        int commonY = ((height - 240) / 2) + 215;
        this.buttonList.add(new GuiButton(0, commonX + 54, commonY, 54, 20, "Obtain"));
        this.buttonList.add(new GuiButton(1, commonX, commonY, 54, 20, "Next"));
        this.buttonList.add(new GuiButton(2, commonX + 108, commonY, 54, 20, "Prev"));

        for (int i = 0; i < 4; i++) {
            //this.buttonList.add(new GuiButton(0+3,,0,""));
            int y = ((height - 240) / 2) - 25 + (i + 1) * 60;
            this.buttonList.add(new GuiButton(i + 3, ((width / 2) - 176) + 38, y, 102, 20, "Select"));
        }
    }

    @Override
    public void drawScreen(int x, int y, float renderPartialTicks) {

        info.clear();
        buttonList.clear();

        for (Map.Entry<String, PerkInfo> entry : Data.perkInfo.entrySet())
            info.add(entry.getValue());

        //Putting the current info into the sublist
        int start = page * 4 < info.size() ? page * 4 : info.size() - 1;
        int end = page * 4 + 4 < info.size() ? page * 4 + 4 : info.size();

        if (info.size() > 0)
            subList = info.subList(start, end);

        drawDefaultBackground();

        for (int i = 0; i < 4; i++) {
            if (subList.size() - 1 >= i) {
                perkList.getNames()[i] = subList.get(i).getName();
                GuiButton button = (GuiButton) buttonList.get(i + 3);
                button.enabled = true;
            } else {
                GuiButton button = (GuiButton) buttonList.get(i + 3);
                button.enabled = false;
                perkList.getNames()[i] = "";
            }
        }


        if (selected != null) {
            perkControls.description = selected.getDescription();
        } else {
            perkControls.description = "";
        }

        perkControls.status = Data.statusMessage;

        perkList.draw();
        perkControls.draw();

        super.drawScreen(x, y, renderPartialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: //Obtain
                if (selected != null) {
                    Main.snw.sendToServer(new PerkRequest(selected.getSlug()));
                    Main.snw.sendToServer(new Request(Request.REQUEST.PERKS));
                    GuiButton obtainButton = (GuiButton) buttonList.get(0);
                    obtainButton.enabled = false;
                }
                break;
            case 1: //Next
                int x = Math.round(this.info.size() / 4);
                if (page < x) {
                    page++;
                }
                break;
            case 2: //Prev
                if (page > 0) {
                    page--;
                }
                break;
            case 3: //List 1
                if (subList.size() > 0)
                    selected = subList.get(0);
                break;
            case 4: //List 2
                if (subList.size() > 1)
                    selected = subList.get(1);
                break;
            case 5: //List 3
                if (subList.size() > 2)
                    selected = subList.get(2);
                break;
            case 6: //List 4
                if (subList.size() > 3)
                    selected = subList.get(3);
                break;
        }

        if (button.id >= 3 && button.id <= 6) {
            boolean active = true;
            if (selected.isObtained()) {
                active = false;
            }
            GuiButton obtainButton = (GuiButton) buttonList.get(0);
            obtainButton.enabled = active;
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
