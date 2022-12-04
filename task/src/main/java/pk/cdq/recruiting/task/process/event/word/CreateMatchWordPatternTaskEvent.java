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

package pk.cdq.recruiting.task.process.event.word;

import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.process.event.TaskEvent;

public class CreateMatchWordPatternTaskEvent extends TaskEvent {

    private final Task task;

    private CreateMatchWordPatternTaskEvent(Task task) {
        this.task = task;
    }

    public static CreateMatchWordPatternTaskEvent of(Task task) {
        return new CreateMatchWordPatternTaskEvent(task);
    }

    public Task getTask() {
        return task;
    }
}
