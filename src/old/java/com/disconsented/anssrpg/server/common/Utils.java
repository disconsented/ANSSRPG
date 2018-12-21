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
package com.disconsented.anssrpg.server.common;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.properties.IProperty;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    private Utils() {
    }

    /**
     * Returns true if two storage are matched. If either Metadata is less than 0(-1) then it is treated as a wildcard (Metadata is ignored).
     *
     * @param object1   First object to match.
     * @param metadata1 First storage metadata.
     * @param object2   Second object to match.
     * @param metadata2 Second storage metadata.
     * @param <T>       Arbitrary data type.
     * @return The result.
     */
    public static <T> boolean MatchObject(T object1, int metadata1, T object2, int metadata2) {
        if (metadata1 > -1 && metadata2 > -1) {
            return object1.equals(object2) && metadata1 == metadata2;
        } else {
            return object1.equals(object2);
        }
    }

    /**
     * Returns true if two storage of the same time are the same. Uses a properties map and will use Object1 as the 'master' (checks are based off that)
     *
     * @param object1     First object to match.
     * @param properties1 Properties map of the first object.
     * @param object2     Second object to match.
     * @param properties2 Properties map of the second(game) object.
     * @param <T>         Arbitrary data type.
     * @return The result.
     */
    public static <T> boolean MatchObject(T object1, Map<String, String> properties1, T object2, ImmutableMap<IProperty<?>, Comparable<?>> properties2) {
        if (object1 != object2)
            return false;

        if (properties1 == null)
            properties1 = new HashMap<>();

        ImmutableSet<IProperty<?>> keys = properties2.keySet();

        for (IProperty<?> key : keys) {
            String value1 = properties1.get(key.getName());
            if (value1 == null) {
                Logging.debug("Could not find " + key.getName() + " ; Continuing!");
                continue;
            }

            String value2 = properties2.get(key).toString();

            if (value1.equals("*")) {
                if (value2.equals("")) {
                    Logging.debug("Failed to evaluate key:" + key + " against values: " + value1 + " & " + value2);
                    return false;
                }
            } else {
                if (!value1.equals(value2)) {
                    Logging.debug("Failed to evaluate key:" + key + " against values: " + value1 + " & " + value2);
                    return false;
                }
            }
        }

        return true;
    }

    public static <T> boolean MatchObject(ImmutableMap<IProperty<?>, Comparable<?>> properties) {
        return true;
    }

    /**
     * Simple method for matching storage.
     *
     * @param item1 First object to match.
     * @param item2 Second object to match.
     * @return The result.
     */
    public static boolean MatchObject(Object item1, Object item2) {
        return item1.equals(item2) || item1 == item2;
    }

    public static String stringToSlug(String value) {
        return value.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
    }

}
