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
import com.pksiazek.common.task.domain.Status;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.event.CreateMatchWordPatternTaskEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class MatchWordPatternProcessor {

    private final TaskPool<WordMatcherTask> taskPool;
    private final WordPatternMatcher matcher;

    public MatchWordPatternProcessor(TaskPool<WordMatcherTask> taskPool, WordPatternMatcher matcher) {
        this.taskPool = taskPool;
        this.matcher = matcher;
    }

    @Async
    @EventListener
    public void onEvent(CreateMatchWordPatternTaskEvent createMatchWordPatternTaskEvent) throws InterruptedException {
        WordMatcherTask wordMatcherTask = createTaskFromEvent(createMatchWordPatternTaskEvent);
        compute(wordMatcherTask);
        finish(wordMatcherTask);
    }

    WordMatcherTask createTaskFromEvent(CreateMatchWordPatternTaskEvent event) {
        WordMatcherTask wordMatcherTask = event.getTask();
        taskPool.put(wordMatcherTask);
        return wordMatcherTask;
    }

    void compute(WordMatcherTask wordMatcherTask) throws InterruptedException {
        Thread.sleep(5_000);
        wordMatcherTask.setTaskStatus(Status.IN_PROGRESS);
        wordMatcherTask.setResult(matcher.match(wordMatcherTask.getInput(), wordMatcherTask.getPattern()));
        wordMatcherTask.progress(1);
        taskPool.put(wordMatcherTask);
    }

    void finish(WordMatcherTask wordMatcherTask) throws InterruptedException {
        // Wait upon task "ends" with some time
        for (int i = 0; i < 99; i++) {
            Thread.sleep(100);
            wordMatcherTask.progress(1);
            taskPool.put(wordMatcherTask);
        }
        wordMatcherTask.setTaskStatus(Status.FINISHED);
        taskPool.put(wordMatcherTask);
    }
}
