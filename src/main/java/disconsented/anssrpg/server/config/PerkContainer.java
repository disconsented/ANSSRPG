/*The MIT License (MIT)

Copyright (c) 2015 Disconsented, James Kerr
Copyright (c) 2015 Abelistah

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
package disconsented.anssrpg.server.config;
import com.google.gson.annotations.Expose;

import disconsented.anssrpg.server.common.Logging;
import disconsented.anssrpg.server.data.PerkStore;
import disconsented.anssrpg.server.perk.*;

import java.util.ArrayList;
/**
 * Used to safely retain data for saving and loading perks before initialising them for use
 * @author Abelistah
 * @author Disconsented 
 */
public class PerkContainer {
    @Expose
    private final ArrayList<ItemPerk> items = new ArrayList<>();
    @Expose
    private final ArrayList<BlockPerk> blocks = new ArrayList<>();
    @Expose
    private final ArrayList<EntityPerk> entities = new ArrayList<>();
    @Expose
    private final ArrayList<TitlePerk> titles = new ArrayList<>();
    @Expose
    private final ArrayList<PotionSelfPerk> selfPotion = new ArrayList<>();
    

    public PerkContainer() {

    }

    public PerkContainer(Boolean fill) {
        if (fill) {
            this.items.add(new ItemPerk());
            this.blocks.add(new BlockPerk());
            this.entities.add(new EntityPerk());
            this.titles.add(new TitlePerk());
            this.selfPotion.add(new PotionSelfPerk());
        }
    }

    public void addPerk(BlockPerk perk) {
        this.blocks.add(perk);
    }

    public void addPerk(EntityPerk perk) {
        this.entities.add(perk);
    }

    public void addPerk(ItemPerk perk) {
        this.items.add(perk);
    }

    public void addPerk(TitlePerk perk) {
        this.titles.add(perk);
    }
    public void addPerk(PotionSelfPerk effect){
        this.selfPotion.add(effect);
    }
    public void touchUp() {
        for (ItemPerk item : this.items) {
            item.touchUp();
            if (item.items != null) {
                PerkStore.putPerk(item);
                PerkStore.addPerk(item);
            } else {
                Logging.error(item.name + "'s object is null. Skipping");
            }
        }
        for (BlockPerk block : this.blocks) {
            block.touchUp();
            if (block.blocks != null) {
                PerkStore.putPerk(block);
                PerkStore.addPerk(block);
            } else {
                Logging.error(block.name + "'s object is null. Skipping");
            }
        }
        for (EntityPerk entity : this.entities) {
            entity.touchUp();
            if (entity.entities != null) {
                PerkStore.putPerk(entity);
                PerkStore.addPerk(entity);
            } else {
                Logging.error(entity.name + "'s object is null. Skipping");
            }
        }
        for (TitlePerk title : this.titles) {
            if (title.getTitle() != null) {
                PerkStore.putPerk(title);
                PerkStore.addPerk(title);
            } else {
                Logging.error(title.name + "s object is null. Skipping");
            }

        }
        for (PotionSelfPerk effect : this.selfPotion) {
            effect.touchUp();
            if (effect.cycle > 0){
                PerkStore.putPerk(effect);
                PerkStore.addPerk(effect);
            } else {
                Logging.error(effect.name + "'s cycle is to low (must be above 0)");
            }
        }
    }
}