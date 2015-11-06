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
package disconsented.anssrpg.perk;

import com.google.gson.annotations.Expose;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
public abstract class Perk {

    @Expose
    public String name = "default_name";
    @Expose
    public ArrayList<Requirement> requirements = new ArrayList<Requirement>();
    public Slug slug;//Not exposed as its made based on the name
    @Expose
    public String description = "default_description";
    @Expose
    public int pointCost = 0;

    public Perk(String name, ArrayList<Requirement> requirements, String description, int pointCost) {
        this.name = name;
        this.requirements = requirements;
        this.description = description;
        this.pointCost = pointCost;
    }

    public String getDescription() {
        return description;
    }

    protected void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
        slug = new Slug(name);
    }

    public int getPointCost() {
        return pointCost;
    }

    protected void setPointCost(int pointCost) {
        this.pointCost = pointCost;
    }

    public ArrayList getRequirements() {
        return requirements;
    }

    protected void setRequirements(ArrayList<Requirement> requirments) {
        requirements = requirments;
    }

    public Slug getSlug() {
        if (this.slug != null) {
            return slug;
        } else {
            this.slug = new Slug(this.name);
            return this.slug;
        }

    }

    public abstract void searchObject();

    @Override
    public String toString() {
        return name + "|" + description + "|" + pointCost + "|" + requirements.toString();
    }

    public void touchUp() {
        searchObject();
        getSlug();
        if (this.requirements == null) {
            this.requirements = new ArrayList<Requirement>();
        }
    }

}
