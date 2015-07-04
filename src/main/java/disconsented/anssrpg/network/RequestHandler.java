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
package disconsented.anssrpg.network;

import java.util.ArrayList;
import java.util.Map.Entry;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import disconsented.anssrpg.Main;
import disconsented.anssrpg.data.PerkStore;
import disconsented.anssrpg.data.SkillStore;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.handler.SkillHandler;
import disconsented.anssrpg.perk.Perk;
import disconsented.anssrpg.player.PlayerData;
import disconsented.anssrpg.skill.objects.Skill;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Handles the @link{request} packet
 * Sends back requested information
 */
public class RequestHandler implements IMessageHandler<Request, IMessage> {
    @Override
    public IMessage onMessage(Request message, MessageContext ctx) {
        EntityPlayerMP player = ctx.getServerHandler().playerEntity;
        PlayerData playerData = PlayerHandler.getPlayer(player.getUniqueID());
        switch(message.request){
		case ACTIVE_PERKS:
			break;
		case OBTAINED_PERKS:
			break;
		case PERKS:
            ArrayList<Perk> perks = PerkStore.getPerks();
            for (Perk perk : perks)
            {//String name, String description, String slug, int pointCost, ArrayList<Requirement> requirements, boolean obtained
                boolean obtained =  playerData.getPerkList().contains(perk.getSlug());
                Main.snw.sendTo(new PerkInfo(perk.name, perk.getDescription(), perk.getSlug().getSlug(), perk.getPointCost(), perk.getRequirements(),obtained),player);
            }
			break;
		case SKILLS:
			SkillStore skillStore = SkillStore.getInstance();
			for(Entry<String, Integer> entry : playerData.getSkillExp().entrySet()){
				for(Skill skill : SkillStore.getSkills()){
					if(entry.getKey().equals(skill.name)){						
						int level = (int) SkillHandler.calculateLevelForExp(skill, entry.getValue());
						int xp = (int) SkillHandler.calculateExpForLevel(skill, level + 1);
						Main.snw.sendTo(new SkillInfo(skill.name, entry.getValue(), xp, level, (int)SkillHandler.calculateExpForLevel(skill.base, level-1, skill.mod) ), player);
						break;
					}
				}
			}
			break;
		default:
			break;
       
        }
        return null;
    }

}
