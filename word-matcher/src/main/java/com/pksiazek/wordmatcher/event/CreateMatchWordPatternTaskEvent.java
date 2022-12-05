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

package com.pksiazek.wordmatcher.event;

import com.pksiazek.common.event.TaskEvent;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;

public class CreateMatchWordPatternTaskEvent extends TaskEvent {

    private final WordMatcherTask task;

    private CreateMatchWordPatternTaskEvent(WordMatcherTask task) {
        this.task = task;
    }

    public static CreateMatchWordPatternTaskEvent of(WordMatcherTask task) {
        return new CreateMatchWordPatternTaskEvent(task);
    }

    public WordMatcherTask getTask() {
        return task;
    }
}
