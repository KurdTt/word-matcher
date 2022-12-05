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

package com.pksiazek.wordmatcher.register;

import com.pksiazek.common.task.dispatcher.TaskDispatcher;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.event.CreateMatchWordPatternTaskEvent;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterWordMatcherTaskController {

    private final TaskDispatcher<CreateMatchWordPatternTaskEvent> taskDispatcher;

    public RegisterWordMatcherTaskController(TaskDispatcher<CreateMatchWordPatternTaskEvent> taskDispatcher) {
        this.taskDispatcher = taskDispatcher;
    }

    public void enqueueTask(WordMatcherTask wordMatcherTask) {
        taskDispatcher.enqueue(CreateMatchWordPatternTaskEvent.of(wordMatcherTask));
    }

}
