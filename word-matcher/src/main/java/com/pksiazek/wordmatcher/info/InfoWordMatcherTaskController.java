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

package com.pksiazek.wordmatcher.info;

import com.pksiazek.common.task.dispatcher.TaskPool;
import com.pksiazek.common.task.domain.Status;
import com.pksiazek.common.task.exception.TaskNotFoundException;
import com.pksiazek.common.task.exception.TaskResultNotFoundException;
import com.pksiazek.common.task.exception.TaskStatusNotFoundException;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.domain.WordMatcherTaskResult;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Controller;

@Controller
public class InfoWordMatcherTaskController {

    private final TaskPool<WordMatcherTask> taskPool;

    public InfoWordMatcherTaskController(TaskPool<WordMatcherTask> taskPool) {
        this.taskPool = taskPool;
    }

    public String getTaskStatus(UUID id) {
        return Optional.of(id)
                .map(taskPool::get)
                .map(WordMatcherTask::getTaskStatus)
                .map(Status::name)
                .orElseThrow(() -> new TaskStatusNotFoundException(id));
    }

    public int getTaskProgress(UUID id) {
        return Optional.of(id)
                .map(taskPool::get)
                .map(WordMatcherTask::getProgress)
                .orElseThrow(TaskNotFoundException::new);
    }

    public WordMatcherTaskResult getTaskResult(UUID id) {
        return Optional.of(id)
                .map(taskPool::get)
                .map(WordMatcherTask::getResult)
                .orElseThrow(() -> new TaskResultNotFoundException(id));
    }

}
