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
package disconsented.anssrpg.server.skill;

import java.util.ArrayList;

import disconsented.anssrpg.server.config.storage.INM;
import disconsented.anssrpg.server.config.storage.INME;
import disconsented.anssrpg.server.perk.ItemPerk;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import disconsented.anssrpg.server.common.Utils;
import disconsented.anssrpg.server.data.PerkStore;
import disconsented.anssrpg.server.data.PlayerStore;
import disconsented.anssrpg.server.data.SkillStore;
import disconsented.anssrpg.server.handler.PlayerHandler;
import disconsented.anssrpg.server.player.PlayerData;
import disconsented.anssrpg.server.skill.objects.ItemSkill;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 * @author James
 *         Handles crafting, both gaining XP and restricting use
 *         PlayerOpenContainer needs to handle blocking, checking for the output and what is in the crafting matrix (Maybe to expensive to compute/?) - For now just checking output, remember to ask about matrix gating in feedback
 *         ItemCrafted handles just giving XP
 */
public class ItemCrafting {
  public void onPlayerOpenCrafting(PlayerOpenContainerEvent event) {
      if (event.entityPlayer instanceof EntityPlayerMP){
          Container container = event.entityPlayer.openContainer;
          if ((container instanceof ContainerWorkbench || container instanceof ContainerPlayer) &&
                event.entityPlayer.openContainer.inventoryItemStacks.get(0) != null) {              
              EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;
              ItemStack stack = (ItemStack)player.openContainer.inventoryItemStacks.get(0);
              Item item = stack.getItem();             
              PlayerData playerData = PlayerStore.getPlayer(player);
              ArrayList<ItemPerk> perkList = PerkStore.getPerks(item);
              ArrayList<ItemSkill> skillStore = SkillStore.getInstance().getItemSkill();
              
              for (ItemSkill skill : skillStore){
                  for (INME entry : skill.exp){
                      if(Utils.MatchObject(entry.item, entry.metadata, item, stack.getItemDamage())){
                          if (!PlayerHandler.hasPerk(playerData, perkList) && this.requiresPerk(perkList,item, stack.getItemDamage())){
                              player.closeScreen();
                              event.setResult(Event.Result.DENY);
                              PlayerHandler.taskFail(player);
                          }
                      }
                  }
              }
          }
      }
      
  }
  
    public void onItemCraftedEvent(PlayerEvent.ItemCraftedEvent event) {
        if (event.player instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) event.player;
            ItemStack stack = event.crafting;
            Item item = stack.getItem();             
            PlayerData playerData = PlayerStore.getPlayer(player);
            ArrayList<ItemSkill> skillStore = SkillStore.getInstance().getItemSkill();
            
            for (ItemSkill skill : skillStore){
                for (INME entry : skill.exp){
                    if(Utils.MatchObject(entry.item, entry.metadata, item, stack.getItemDamage())){
                        PlayerHandler.awardXP(player, skill, entry.experience);
                    }
                }
            }
        }        
    }

    private boolean requiresPerk(ArrayList<ItemPerk> perkList, Item item, int metadata){
        if(perkList != null) {
            for (ItemPerk perk : perkList) {
                for (INM definition : perk.items)
                {
                    if(Utils.MatchObject(definition.item, definition.metadata, item, metadata)){
                        return true;
                    }
                }
            }
        }
        return  false;
    }

}
