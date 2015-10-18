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
package disconsented.anssrpg.common;

import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Utils {
    /**
     * Returns true if two objects are matched. If either Metadata is <= -1 then it is treated as a wildcard (Metadata is ignored)
     * @param object1
     * @param metadata1
     * @param object2
     * @param metadata2
     * @return
     */
    public static<T> boolean MatchObject(T object1, int metadata1, T object2, int metadata2){
        if (metadata1 > -1 && metadata2 > -1){
            return object1.equals(object2) && metadata1 == metadata2;
        } else {
            return object1.equals(object2);
        }
    }

    /**
     * Returns true if two objects of the same time are the same. Uses a properties map and will use Object1 as the 'master' (checks are based off that)
     * @param object1
     * @param properties1
     * @param object2
     * @param properties2
     * @param <T>
     * @return
     */
    public static<T> boolean MatchObject(T object1, Map<String, String> properties1, T object2, Map<String, String> properties2){
        if(object1 != object2)
            return false;

        if(properties1 == null)
            properties1 = new HashMap<>();

        if(properties2 == null)
            properties2 = new HashMap<>();

        String[] keys = properties1.keySet().toArray(new String[properties1.size()]);

        for(String key : keys){
            String value1 = properties1.get(key);
            String value2 = properties2.get(key);
            if(value1 == "*"){
                if (value2 == ""){
                    Logging.debug("Failed to evaluate key:"+key+" against values: "+value1+" & "+value2);
                    return false;
                }
            } else {
                if(!value1.equals(value2)){
                    Logging.debug("Failed to evaluate key:"+key+" against values: "+value1+" & "+value2);
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean MatchObject(Object item1, Object item2){
        return item1.equals(item2) || item1 == item2;
    }

    /**
     * @author Disconsented
     *
     */
    public static class Tools {
        public static String stringToSlug(String value){
            return value.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        }
    }
}
