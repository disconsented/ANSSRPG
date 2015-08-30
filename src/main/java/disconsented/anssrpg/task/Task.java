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
package disconsented.anssrpg.task;

import cpw.mods.fml.common.gameevent.TickEvent;

public abstract class Task/* implements Comparable<TaskApplyPotion>*/{
	
	private int tick = 0;
    private int totalTicks = 0;
	protected boolean repeat = false;
	protected int cycle = 1;
	protected  int maxTicks = 1;
	protected TickEvent.Type type = null;
	/**
	 * Called when the task is first added to the master
	 */
	public abstract void onAdd();
	/**
	 * Called every tick
	 * Override canProcess to filter  
	 * @param event
	 */
	public abstract void onTick(TickEvent event);
	/**
	 * Called when the task will no longer be repeated
	 */
	public abstract void onEnd();
	/**
	 * Called to determine if the task should be processed
	 * 
	 * @param event
	 * @return
	 */
	public boolean canProcess(TickEvent event){
		if(!filter(event)){
			if(tick / cycle == 1){
				this.tick = 0;
				return true;
			} else {			
				this.tick++;
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * Allows a task to be filtered to a TickEvent type
	 * @param event
	 * @return
	 */
	private boolean filter(TickEvent event){
		if(type == null) {
			return false;
		}
		
		return !event.type.equals(type);
	}
	
	public boolean canRepeat(){
        if(totalTicks > maxTicks && maxTicks > 0){
			return this.repeat = false;
		}
			return this.repeat;
    }

    public void increaseTick(){
        this.tick++;
        this.totalTicks++;
    }
}
