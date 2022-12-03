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

package pk.cdq.recruiting.task.info;

import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Controller;
import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.domain.TaskResult;
import pk.cdq.recruiting.task.domain.TaskStatus;
import pk.cdq.recruiting.task.exception.TaskResultNotFoundException;
import pk.cdq.recruiting.task.exception.TaskStatusNotFoundException;
import pk.cdq.recruiting.task.process.execution.TaskPool;

@Controller
public class InfoTaskController {

    private final TaskPool taskPool;

    public InfoTaskController(TaskPool taskPool) {
        this.taskPool = taskPool;
    }

    public String getTaskStatus(UUID id) {
        return Optional.of(id)
                .map(taskPool::get)
                .map(Task::getTaskStatus)
                .map(TaskStatus::name)
                .orElseThrow(() -> new TaskStatusNotFoundException(id));
    }

    public TaskResult getTaskResult(UUID id) {
        return Optional.of(id)
                .map(taskPool::get)
                .map(Task::getResult)
                .orElseThrow(() -> new TaskResultNotFoundException(id));
    }

}
