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
package disconsented.anssrpg.task;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import cpw.mods.fml.common.gameevent.TickEvent;
import disconsented.anssrpg.common.Logging;
import disconsented.anssrpg.perk.Slug;

/**
 * @author Disconsented
 *
 */
public class TaskApplyPotion extends Task{
    private EntityLivingBase entity;
    private PotionEffect effect;
    private final String tagName = "TASKAPPLYPOTION";
    private Slug slug;
    public TaskApplyPotion(EntityLivingBase entity, PotionEffect effect, TickEvent.Type type, boolean repeat, int cycle){
        this.entity = entity;
        this.effect = effect;
        this.type = type;
        this.repeat = repeat;
        this.cycle = cycle;
    }
    
    public TaskApplyPotion(EntityLivingBase entity, PotionEffect effect, TickEvent.Type type, boolean repeat, int cycle, Slug slug){
        this.entity = entity;
        this.effect = effect;
        this.type = type;
        this.repeat = repeat;
        this.cycle = cycle;
        this.slug = slug;
    }

    /* (non-Javadoc)
     * @see disconsented.anssrpg.task.Task#onAdd()
     */
    @Override
    public void onAdd() {
        NBTTagList list = entity.getEntityData().getTagList(tagName, 8);
        list.appendTag(new NBTTagString(this.slug.getSlug()));        
        entity.getEntityData().setTag(tagName, list);

    }

    /* (non-Javadoc)
     * @see disconsented.anssrpg.task.Task#onTick(cpw.mods.fml.common.gameevent.TickEvent)
     */
    @Override
    public void onTick(TickEvent event) {        
        if(entity.isDead){
            repeat = false;
        } else {
            Logging.debug("Attempting to apply potion " + effect.getEffectName() + " to " + entity.getCommandSenderName());
            entity.addPotionEffect(new PotionEffect(effect));
        }

    }

    /* (non-Javadoc)
     * @see disconsented.anssrpg.task.Task#onEnd()
     */
    @Override
    public void onEnd() {
        NBTTagList list = entity.getEntityData().getTagList(tagName, 8);
        for (int i = 0; i < list.tagCount(); i++){
            if(list.getStringTagAt(i).equals(this.slug.getSlug())){
                list.removeTag(i);
                System.out.println("ENDED");
                return;
            }
        }

    }

}
