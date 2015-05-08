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

import java.util.Queue;

import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.relauncher.Side;

public class TaskMaster{
	protected TaskMaster(){}
	
	private static TaskMaster master = null;
	private Queue<Task> queue;
	private Queue<Task> currentQueue;
	
	public static TaskMaster getInstance(){
		if (master == null){
			master = new TaskMaster();
		} 
		return master;
	}
	
	public boolean addTask(Task task){
		task.onAdd();
		return queue.offer(task);		
	}
	
	public void process(TickEvent event){
		if(event.side == Side.SERVER && event.phase == Phase.START && queue != null){
			while(queue.peek() != null){
				currentQueue.offer(queue.element());
			}
			
			while(currentQueue.peek() != null){
				Task currentTask = currentQueue.poll();
				
				if (currentTask.canProcess(event)){
					currentTask.onTick(event);
					
					if (currentTask.canRepeat()){
						queue.offer(currentTask);
					} else {
						currentTask.onEnd();
					}
				} else {
					queue.offer(currentTask);
				}
				
			}
		}
	}
}
