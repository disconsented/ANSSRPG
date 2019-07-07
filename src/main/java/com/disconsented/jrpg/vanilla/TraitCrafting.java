package com.disconsented.jrpg.vanilla;

import com.disconsented.jrpg.JRPG;
import com.disconsented.jrpg.Player;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.AdvancementToast;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashMap;

@Mod.EventBusSubscriber
public class TraitCrafting {
    @SubscribeEvent
    public static void craft(PlayerEvent.ItemCraftedEvent event) {
        JRPG.log.debug(event.player.openContainer);
        JRPG.log.debug(event.crafting);
        JRPG.log.debug(event.craftMatrix);
        event.player.inventory.setItemStack(ItemStack.EMPTY);
        for (int i = 0; i < event.craftMatrix.getSizeInventory(); i++) {
            event.player.inventory.addItemStackToInventory(
                    event.craftMatrix.removeStackFromSlot(i));
        }

        Advancement adv = new Advancement(new ResourceLocation(JRPG.MODID, "notice"), null, null, null, new HashMap<>(), new String[0][0]);
        IToast toast = new AdvancementToast(adv);
        Minecraft.getMinecraft().getToastGui().add(toast);
        JRPG.log.info("?");
        Player player = Player.getPlayer(event.player);
        player.sendMessage("I blocked crafting");
    }
}
