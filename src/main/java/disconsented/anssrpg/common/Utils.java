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

public class Utils {
    /**
     * @return - 2 if two objects are matched. If either Metadata is <= -1 then it is treated as a wildcard (Metadata is ignored)
     */
    public static <T> boolean MatchObject(T object1, int metadata1, T object2, int metadata2) {
        if (metadata1 > -1 && metadata2 > -1) {
            if (object1.getClass().equals(object2.getClass()) && metadata1 == metadata2) {
                return true;
            } else {
                return false;
            }
        } else {
            if (object1.getClass().equals(object2.getClass())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static boolean MatchObject(Object item1, Object item2) {
        return item1.equals(item2) || item1 == item2;
    }

    /**
     * @author Disconsented
     */
    public static class Tools {
        public static String stringToSlug(String value){
            return value.toLowerCase().replaceAll("[^A-Za-z0-9]", "");
        }
    }
}
