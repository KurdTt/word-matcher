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

package com.pksiazek.wordmatcher.execution;

import com.pksiazek.common.task.dispatcher.TaskPool;
import com.pksiazek.common.task.exception.TaskNotFoundException;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class WordMatcherTaskPool implements TaskPool<WordMatcherTask> {

    private final Map<UUID, WordMatcherTask> taskPool = new ConcurrentHashMap<>();

    @Override
    public void put(WordMatcherTask task) {
        taskPool.put(task.getId(), task);
    }

    @Override
    public WordMatcherTask get(UUID id) {
        return Optional.of(id)
                .filter(taskPool::containsKey)
                .map(taskPool::get)
                .orElseThrow(TaskNotFoundException::new);
    }
}
