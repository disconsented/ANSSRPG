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
import disconsented.anssrpg.client.Data;
import disconsented.anssrpg.client.gui.components.PerkControls;
import disconsented.anssrpg.client.gui.components.PerkList;
import disconsented.anssrpg.server.network.PerkInfo;
import disconsented.anssrpg.server.network.PerkRequest;
import disconsented.anssrpg.server.network.Request;
import disconsented.anssrpg.server.network.Request.REQUEST;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

/**
 * @author Disconsented
 */
public class GUIPerk extends GuiScreen {
    private final ArrayList<PerkInfo> info = new ArrayList<>();
    private PerkList perkList;
    private PerkControls perkControls;
    private int page;
    private PerkInfo selected;
    private List<PerkInfo> subList = new ArrayList<>();


    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_) {
        //Clearing then filling the ArrayList (this is where information is pulled from)
        this.info.clear();
        for (Entry<String, PerkInfo> entry : Data.perkInfo.entrySet()) {
            this.info.add(entry.getValue());
        }

        //Putting the current info into the sublist
        int start = this.page * 4 < this.info.size() ? this.page * 4 : this.info.size() - 1;
        int end = this.page * 4 + 4 < this.info.size() ? this.page * 4 + 4 : this.info.size();

        if (this.info.size() > 0)
            this.subList = this.info.subList(start, end);

        this.drawDefaultBackground();

        for (int i = 0; i < 4; i++) {
            if (this.subList.size() - 1 >= i) {
                this.perkList.getNames()[i] = this.subList.get(i).getName();
                GuiButton button = (GuiButton) this.buttonList.get(i + 3);
                button.enabled = true;
            } else {
                GuiButton button = (GuiButton) this.buttonList.get(i + 3);
                button.enabled = false;
                this.perkList.getNames()[i] = "";
            }
        }


        if (this.selected != null) {
            this.perkControls.description = this.selected.getDescription();
        } else {
            this.perkControls.description = "";
        }

        this.perkControls.status = Data.statusMessage;

        this.perkList.draw();
        this.perkControls.draw();

        for (Object button : this.buttonList) {
            GuiButton toDraw = (GuiButton) button;
            toDraw.drawButton(Minecraft.getMinecraft(), 0, 0);
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0: //Obtain
                if (this.selected != null) {
                    Main.snw.sendToServer(new PerkRequest(this.selected.getSlug()));
                    Main.snw.sendToServer(new Request(REQUEST.PERKS));
                    GuiButton obtainButton = (GuiButton) this.buttonList.get(0);
                    obtainButton.enabled = false;
                }
                break;
            case 1: //Next
                int x = Math.round(info.size() / 4);
                if (this.page < x) {
                    this.page++;
                }
                break;
            case 2: //Prev
                if (this.page > 0) {
                    this.page--;
                }
                break;
            case 3: //List 1
                if (this.subList.size() > 0)
                    this.selected = this.subList.get(0);
                break;
            case 4: //List 2
                if (this.subList.size() > 1)
                    this.selected = this.subList.get(1);
                break;
            case 5: //List 3
                if (this.subList.size() > 2)
                    this.selected = this.subList.get(2);
                break;
            case 6: //List 4
                if (this.subList.size() > 3)
                    this.selected = this.subList.get(3);
                break;
        }

        if (button.id >= 3 && button.id <= 6) {
            boolean active = true;
            if (this.selected.isObtained()) {
                active = false;
            }
            GuiButton obtainButton = (GuiButton) this.buttonList.get(0);
            obtainButton.enabled = active;
        }
    }

    @Override
    public void initGui() {//176
        buttonList = new <GuiButton>ArrayList();

        //Clear park data on the client and request new info
        Data.perkInfo.clear();
        Data.statusMessage = "";
        Main.snw.sendToServer(new Request(REQUEST.PERKS));
        this.perkList = new PerkList(width / 2 - 176, (this.height - 240) / 2);
        //39,36
        //39,96
        //39,156
        //39,216
        this.perkControls = new PerkControls(this.width / 2, (this.height - 240) / 2);
        //id,x,y,width,height,string

        //Order of when these are added matters as I retrieve them by their index not id
        int commonX = width / 2 + 7;
        int commonY = (height - 240) / 2 + 215;
        buttonList.add(new GuiButton(0, commonX + 54, commonY, 54, 20, "Obtain"));
        buttonList.add(new GuiButton(1, commonX, commonY, 54, 20, "Next"));
        buttonList.add(new GuiButton(2, commonX + 108, commonY, 54, 20, "Prev"));

        for (int i = 0; i < 4; i++) {
            //this.buttonList.add(new GuiButton(0+3,,0,""));
            int y = (height - 240) / 2 - 25 + (i + 1) * 60;
            buttonList.add(new GuiButton(i + 3, width / 2 - 176 + 38, y, 102, 20, "Select"));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

}
