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

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.domain.Task;
import pk.cdq.recruiting.task.exception.TaskNotFoundException;

@Service
public class TaskPoolImpl implements TaskPool {

    private final Map<UUID, Task> taskPool = new ConcurrentHashMap<>();

    @Override
    public void put(Task task) {
        taskPool.put(task.getId(), task);
    }

    @Override
    public Task get(UUID id) {
        return Optional.of(id)
                .filter(taskPool::containsKey)
                .map(taskPool::get)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
