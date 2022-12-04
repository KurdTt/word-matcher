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

package pk.cdq.recruiting.task.register;

import org.springframework.stereotype.Controller;
import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.process.dispatcher.TaskDispatcher;
import pk.cdq.recruiting.task.process.event.word.CreateMatchWordPatternTaskEvent;

@Controller
public class RegisterTaskController {

    private final TaskDispatcher taskDispatcher;

    public RegisterTaskController(TaskDispatcher taskDispatcher) {
        this.taskDispatcher = taskDispatcher;
    }

    public void enqueueTask(Task task) {
        taskDispatcher.enqueue(CreateMatchWordPatternTaskEvent.of(task));
    }

}
