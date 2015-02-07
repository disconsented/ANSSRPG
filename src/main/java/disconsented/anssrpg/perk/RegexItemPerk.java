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

import disconsented.anssrpg.common.Logging;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegexItemPerk extends RegexPerk {

    public ArrayList<Item> items = new ArrayList<Item>();

    public RegexItemPerk() {
    }

    @Override
    public void searchObject() {
        List<Object> keyList = Arrays.asList(Item.itemRegistry.getKeys().toArray());
        for (Object key : keyList) {
            String current = key.toString();
            int colonPos = current.indexOf(':');
            if (colonPos > -1) {
                current = current.substring(colonPos + 1);
            }
            if (current.matches(searchQuery)) {
                items.add((Item) Item.itemRegistry.getObject(current));
            }
        }
        Logging.debug(this.name + " has " + items.size() + " item's listed");
    }

}