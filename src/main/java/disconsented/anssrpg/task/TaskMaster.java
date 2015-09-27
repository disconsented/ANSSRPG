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

import java.util.AbstractQueue;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import disconsented.anssrpg.common.Logging;

public class TaskMaster{
	protected TaskMaster(){}
	
	private static TaskMaster master = null;
	private Queue<Task> queue = new LinkedList<Task>();
	private Queue<Task> currentQueue = new LinkedList<Task>();
	
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
		    while(queue.isEmpty() == false){
		        currentQueue.offer(queue.poll());
		    }
			while(currentQueue.peek() != null){
				Task currentTask = currentQueue.poll();
				Logging.debug("Attempting to process " + currentTask.getClass().getName());
				
				if (currentTask.canProcess(event)){
					currentTask.onTick(event);
					currentTask.increaseTick();
					
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
