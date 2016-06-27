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
package disconsented.anssrpg.core.server.task;

import disconsented.anssrpg.Main;
import disconsented.anssrpg.core.server.common.Reference;
import disconsented.anssrpg.core.server.handler.PlayerHandler;
import disconsented.anssrpg.core.server.network.ActivePerks;
import disconsented.anssrpg.core.server.network.PlayerStatus;
import disconsented.anssrpg.core.server.perk.Slug;
import disconsented.anssrpg.core.server.player.PlayerData;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;

/**
 * Created by Disconsented on 30/08/2015.
 */

/**
 * Handles and sends data to the player every tick concerning the status GUI
 */
public class TaskPlayerStatusTrack extends Task {
    public static final String TAG_STATUS_OPEN = Reference.ID + "STATUS_OPEN";
    private final EntityPlayerMP player;
    private PlayerData playerData;

    public TaskPlayerStatusTrack(EntityPlayerMP player) {
        this.player = player;
        repeat = true;
        maxTicks = 0;
    }


    @Override
    public void onAdd() {
        this.player.getEntityData().setBoolean(TaskPlayerStatusTrack.TAG_STATUS_OPEN, true);
    }

    @Override
    public void onTick(TickEvent event) {
        if (this.player != null && this.player.isEntityAlive() && this.player.getEntityData().getBoolean(TaskPlayerStatusTrack.TAG_STATUS_OPEN)) {
            this.playerData = PlayerHandler.getPlayer(this.player.getUniqueID());//Refreshing the playerData
            PlayerStatus status = new PlayerStatus(this.player.getHealth(), this.player.getFoodStats().getSaturationLevel(), this.getArmourValue(3), this.getArmourValue(2), this.getArmourValue(1), this.getArmourValue(0));
            Main.snw.sendTo(status, this.player);

            ArrayList<String> list = new ArrayList<>();
            for (Slug slug : this.playerData.getActivePerks()) {
                list.add(slug.getSlug());
            }
            Main.snw.sendTo(new ActivePerks(list), this.player);
        } else {
            repeat = false;
        }
    }

    @Override
    public void onEnd() {
        this.player.getEntityData().setBoolean(TaskPlayerStatusTrack.TAG_STATUS_OPEN, false);
    }

    private float getArmourValue(int slot) {
        //ItemStack itemStack = this.player.getCurrentArmor(slot);
        ItemStack itemStack = null;
        return itemStack != null ? ((ItemArmor) itemStack.getItem()).damageReduceAmount : 0;
    }
}