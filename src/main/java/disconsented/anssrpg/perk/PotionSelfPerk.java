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
package disconsented.anssrpg.perk;

import com.google.gson.annotations.Expose;
import disconsented.anssrpg.common.PotionDefinition;
import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.task.TaskApplyPotion;
import disconsented.anssrpg.task.TaskMaster;
import lombok.NoArgsConstructor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;

import java.util.ArrayList;

@NoArgsConstructor
public class PotionSelfPerk extends Perk implements ActivePerk {

    @Expose
    public ArrayList<PotionDefinition> effects = new ArrayList<PotionDefinition>();
    @Expose
    public boolean repeat = false;
    @Expose
    public int cycle = 1;
    @Expose
    public int maxCycles = 10;

    public PotionSelfPerk(String name, ArrayList<Requirement> requirements, String description, int pointCost, ArrayList<PotionDefinition> effects, boolean repeat, int cycle, int maxCycles) {
        super(name, requirements, description, pointCost);
        this.repeat = repeat;
        this.cycle = cycle;
        this.effects = effects;
        this.maxCycles = maxCycles;
    }

    @Override
    public void searchObject() {
    }

    @Override
    public void activate(EntityLivingBase target, EntityLivingBase source) {
        PlayerHandler.getPlayer(((EntityPlayerMP) target).getUniqueID());
        for (PotionDefinition effect : effects) {
            TaskMaster.getInstance().addTask(new TaskApplyPotion(
                    (EntityLivingBase) target, new PotionEffect(effect.id, effect.duration, effect.amplifier), null, repeat, cycle, maxCycles, slug));
        }

    }
}
