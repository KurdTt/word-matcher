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

package pk.cdq.recruiting.task.process.execution.word;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.domain.TaskStatus;
import pk.cdq.recruiting.task.process.event.word.CreateMatchWordPatternTaskEvent;
import pk.cdq.recruiting.task.process.execution.TaskPool;

@Service
@EnableAsync
public class MatchWordPatternProcessor {

    private final TaskPool taskPool;
    private final WordPatternMatcher matcher;

    public MatchWordPatternProcessor(TaskPool taskPool, WordPatternMatcher matcher) {
        this.taskPool = taskPool;
        this.matcher = matcher;
    }

    @Async
    @EventListener
    public void onEvent(CreateMatchWordPatternTaskEvent createMatchWordPatternTaskEvent) throws InterruptedException {
        Task task = createTaskFromEvent(createMatchWordPatternTaskEvent);
        compute(task);
        finish(task);
    }

    Task createTaskFromEvent(CreateMatchWordPatternTaskEvent event) {
        Task task = event.getTask();
        taskPool.put(task);
        return task;
    }

    void compute(Task task) throws InterruptedException {
        Thread.sleep(5_000);
        task.setTaskStatus(TaskStatus.IN_PROGRESS);
        task.setResult(matcher.match(task.getInput(), task.getPattern()));
        task.progress(1);
        taskPool.put(task);
    }

    void finish(Task task) throws InterruptedException {
        // Wait upon task "ends" with some time
        for (int i = 0; i < 99; i++) {
            Thread.sleep(100);
            task.progress(1);
            taskPool.put(task);
        }
        task.setTaskStatus(TaskStatus.FINISHED);
        taskPool.put(task);
    }
}
