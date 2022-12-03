/*
 * This code is unpublished proprietary trade secret of
 * Visiona Sp. z o.o., ul. Życzkowskiego 14, 31-864 Kraków, Poland.
 *
 * This code is protected under Act on Copyright and Related Rights
 * and may be used only under the terms of license granted by
 * Visiona Sp. z o.o., ul. Życzkowskiego 14, 31-864 Kraków, Poland.
 *
 * Above notice must be preserved in all copies of this code.
 */

package pk.cdq.recruiting.task.process.execution;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.domain.TaskResult;
import pk.cdq.recruiting.task.process.event.CreateTaskEvent;

@Service
@EnableAsync
public class TaskProcessor {

    private final TaskPool taskPool;

    public TaskProcessor(TaskPool taskPool) {
        this.taskPool = taskPool;
    }

    @Async
    @EventListener
    public void onCreateTaskEvent(CreateTaskEvent createTaskEvent) {
        Task task = createTaskEvent.getTask();
        task.setResult(new TaskResult(0, 0));
        taskPool.put(task);
    }
}
