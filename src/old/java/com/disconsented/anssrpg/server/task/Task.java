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
package com.disconsented.anssrpg.server.task;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;

public abstract class Task/* implements Comparable<TaskApplyPotion>*/ {

    protected boolean repeat;
    protected int cycle = 1;
    protected int maxTicks = 1;
    protected Type type;
    private int tick;
    private int totalTicks;

    /**
     * Called when the task is first added to the master
     */
    public abstract void onAdd();

    /**
     * Called every tick
     * Override canProcess to filter
     *
     * @param event TickEvent
     */
    public abstract void onTick(TickEvent event);

    /**
     * Called when the task will no longer be repeated
     */
    public abstract void onEnd();

    /**
     * Called to determine if the task should be processed
     *
     * @param event TickEvent
     * @return The result.
     */
    public boolean canProcess(TickEvent event) {
        if (!this.filter(event)) {
            if (this.tick / this.cycle == 1) {
                tick = 0;
                return true;
            } else {
                tick++;
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Allows a task to be filtered to a TickEvent type
     *
     * @param event TickEvent
     * @return The result.
     */
    private boolean filter(TickEvent event) {
        if (this.type == null) {
            return false;
        }

        return !event.type.equals(this.type);
    }

    public boolean canRepeat() {
        if (this.totalTicks > this.maxTicks && this.maxTicks > 0) {
            return repeat = false;
        }
        return repeat;
    }

    public void increaseTick() {
        tick++;
        totalTicks++;
    }
}
