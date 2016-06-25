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
package disconsented.anssrpg.server.task;

import disconsented.anssrpg.server.common.Logging;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import java.util.LinkedList;
import java.util.Queue;

public class TaskMaster {
    private static TaskMaster master;
    private final Queue<Task> queue = new LinkedList<>();
    private final Queue<Task> currentQueue = new LinkedList<>();

    protected TaskMaster() {
    }

    public static TaskMaster getInstance() {
        if (TaskMaster.master == null) {
            TaskMaster.master = new TaskMaster();
        }
        return TaskMaster.master;
    }

    public boolean addTask(Task task) {
        task.onAdd();
        return this.queue.offer(task);
    }

    public void process(TickEvent event) {
        if (event.side == Side.SERVER && event.phase == TickEvent.Phase.START && this.queue != null) {
            while (this.queue.isEmpty() == false) {
                this.currentQueue.offer(this.queue.poll());
            }
            while (this.currentQueue.peek() != null) {
                Task currentTask = this.currentQueue.poll();
                Logging.debug("Attempting to process " + currentTask.getClass().getName());

                if (currentTask.canProcess(event)) {
                    currentTask.onTick(event);
                    currentTask.increaseTick();

                    if (currentTask.canRepeat()) {
                        this.queue.offer(currentTask);
                    } else {
                        currentTask.onEnd();
                    }
                } else {
                    this.queue.offer(currentTask);
                }

            }
        }
    }
}
