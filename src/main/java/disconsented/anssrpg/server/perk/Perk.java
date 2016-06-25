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
/**
 * Author: Disconsented
 * Supertype for perks
 */
package disconsented.anssrpg.server.perk;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public abstract class Perk {
    @Expose
    public String name = "default_name";
    @Expose
    public ArrayList<Requirement> requirements = new ArrayList<>();
    public Slug slug;//Not exposed as its made based on the resourceLocation
    @Expose
    public String description = "default_description";

    public Perk() {
    } // Blank constructor for Gson

    public Perk(String name, ArrayList<Requirement> requirements, String description) {
        this.name = name;
        this.requirements = requirements;
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
        this.slug = new Slug(name);
    }

    public ArrayList getRequirements() {
        return this.requirements;
    }

    protected void setRequirements(ArrayList<Requirement> requirments) {
        this.requirements = requirments;
    }

    public Slug getSlug() {
        if (slug != null) {
            return this.slug;
        } else {
            slug = new Slug(name);
            return slug;
        }

    }

    public abstract void init();

    @Override
    public String toString() {
        return this.name + "|" + this.description + "|" + this.requirements;
    }

    public void touchUp() {
        this.init();
        this.getSlug();
        if (requirements == null) {
            requirements = new ArrayList<>();
        }
    }

}
