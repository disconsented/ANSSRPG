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
package disconsented.anssrpg.server.perk;

import java.util.ArrayList;

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.handler.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.server.config.storage.PotionDefinition;
import disconsented.anssrpg.server.task.TaskApplyPotion;
import disconsented.anssrpg.server.task.TaskMaster;

/**
 * @author Disconsented
 *
 */
public class PotionSelfPerk extends Perk implements ActivePerk{
    
    public PotionSelfPerk(String name, ArrayList<Requirement> requirements, String description,
            ArrayList<PotionDefinition> effects, boolean repeat, int cycle, int maxCycles){
        super(name, requirements, description);
        this.repeat = repeat;
        this.cycle = cycle;
        this.effects = effects;
        this.maxCycles = maxCycles;
    }
    public PotionSelfPerk(){}
    
    @Expose
    public ArrayList<PotionDefinition> effects = new ArrayList<PotionDefinition>();    
    @Expose
    public boolean repeat;
    @Expose
    public int cycle = 1;
    @Expose
    public int maxCycles = 10;
    @Override
    public void init() {
        ArrayList<PotionDefinition> initialised = new ArrayList<>();
        for(PotionDefinition object : effects){
            object.potion = (Potion) Potion.REGISTRY.getObject(object.resourceLocation);
            if (object.potion != null){
                Logging.debug(object.resourceLocation + " has been found. Passing on.");
                initialised.add(object);
            } else {
                Logging.error(object.resourceLocation + " has not been found. Skipping");
            }
        }
        effects = initialised;
    }
    @Override
    public void activate(EntityLivingBase target, EntityLivingBase source) {
        PlayerHandler.getPlayer(target.getUniqueID());
        for (PotionDefinition effect : this.effects){
            TaskMaster.getInstance().addTask(new TaskApplyPotion(
                    target, new PotionEffect(effect.potion, effect.duration, effect.amplifier), null, this.repeat, this.cycle, this.maxCycles, this.slug));
        }
        
    }
}
