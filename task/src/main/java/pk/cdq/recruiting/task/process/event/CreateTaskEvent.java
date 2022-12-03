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

package pk.cdq.recruiting.task.process.event;

import pk.cdq.recruiting.task.domain.Task;

public class CreateTaskEvent {
    private final Task task;

    private CreateTaskEvent(Task task) {
        this.task = task;
    }

    public static CreateTaskEvent of(Task task) {
        return new CreateTaskEvent(task);
    }

    public Task getTask() {
        return task;
    }
}
