package disconsented.anssrpg.task;

import disconsented.anssrpg.handler.PlayerHandler;
import disconsented.anssrpg.perk.Slug;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.PotionEffect;

public abstract class TaskTrackPlayer extends Task {

    public static final String tagName = "TRACKPLAYER";
    protected EntityLivingBase entity;
    protected PotionEffect effect;
    protected Slug slug;

    public TaskTrackPlayer() {
        super();
    }

    @Override
    public void onAdd() {
        NBTTagList list = entity.getEntityData().getTagList(tagName, 8);
        list.appendTag(new NBTTagString(this.slug.getSlug()));
        entity.getEntityData().setTag(tagName, list);
        PlayerHandler.getPlayer(entity.getUniqueID()).getActivePerks().add(slug);

    }

    @Override
    public void onEnd() {
        PlayerHandler.getPlayer(entity.getUniqueID()).getActivePerks().remove(slug);
        NBTTagList list = entity.getEntityData().getTagList(tagName, 8);
        for (int i = 0; i < list.tagCount(); i++) {
            if (list.getStringTagAt(i).equals(this.slug.getSlug())) {
                list.removeTag(i);
                return;
            }
        }

    }

}