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

import disconsented.anssrpg.server.config.objects.BNP;
import net.minecraft.block.Block;

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.server.common.Logging;

public class BlockPerk extends Perk {

    @Expose
    public ArrayList<BNP> blocks = new ArrayList<BNP>();

    public BlockPerk() {
    }

    public BlockPerk(String name, ArrayList<Requirement> requirements,
                     String description, ArrayList<BNP> blocks) {
        super(name, requirements, description);
        this.blocks = blocks;
    }

    @Override
    public void init() {
        ArrayList<BNP> initialised = new ArrayList<BNP>();
        for(BNP object : this.blocks){
            object.block = (Block)Block.blockRegistry.getObject(object.name);
            if (object.block != null){
                Logging.debug(object.name + " has been found. Passing on.");
                initialised.add(object);
            } else {
                Logging.error(object.name + " has not been found. Skipping");
            }
        }
        blocks = initialised;
    }
    
}