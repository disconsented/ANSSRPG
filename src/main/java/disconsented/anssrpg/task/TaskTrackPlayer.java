package disconsented.anssrpg.task;

import disconsented.anssrpg.handler.PlayerHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;
import disconsented.anssrpg.perk.Slug;

public abstract class TaskTrackPlayer extends Task {

    protected EntityLivingBase entity;
    protected PotionEffect effect;
    public static final String tagName = "TRACKPLAYER";
    protected Slug slug;

    public TaskTrackPlayer() {
    }

    @Override
    public void onAdd() {
        NBTTagList list = this.entity.getEntityData().getTagList(TaskTrackPlayer.tagName, 8);
        list.appendTag(new NBTTagString(slug.getSlug()));
        this.entity.getEntityData().setTag(TaskTrackPlayer.tagName, list);
        PlayerHandler.getPlayer(this.entity.getUniqueID()).getActivePerks().add(this.slug);
    
    }

    @Override
    public void onEnd() {
        PlayerHandler.getPlayer(this.entity.getUniqueID()).getActivePerks().remove(this.slug);
        NBTTagList list = this.entity.getEntityData().getTagList(TaskTrackPlayer.tagName, 8);
        for (int i = 0; i < list.tagCount(); i++){
            if(list.getStringTagAt(i).equals(slug.getSlug())){
                list.removeTag(i);
                return;
            }
        }

    }

}