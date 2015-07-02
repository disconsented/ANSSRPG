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

import com.google.gson.annotations.Expose;

import disconsented.anssrpg.common.Utils;

/**
 * @author Disconsented
 *         Object for requirements
 */
public class Requirement {
    /*
     * HAVE - Has a perk
     * DONT - Doesn't have a perk
     * LEVEL_EQUALS - Level == x
     * LEVEL_GREATER - Level > x
     * LEVEL_LESS - Level < x
     */

    @Expose
    public Action action;
    @Expose
    public String name;
    @Expose
    public String extraData;
    public Requirement(Action action, String name, String extraData) {
        this.action = action;
        this.name = name;
        this.extraData = extraData;
    }

    public String getNameAsSlug() {
        return Utils.Tools.stringToSlug(name);
    }

    public enum Action {HAVE, DONT, LEVEL_EQUALS, LEVEL_GREATER, LEVEL_LESS}
}
