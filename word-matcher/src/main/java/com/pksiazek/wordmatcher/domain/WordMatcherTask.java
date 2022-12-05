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

package com.pksiazek.wordmatcher.domain;

import com.pksiazek.common.task.domain.Status;
import com.pksiazek.common.task.domain.Task;
import java.io.Serial;
import java.util.UUID;

public class WordMatcherTask implements Task {
    @Serial
    private static final long serialVersionUID = 9139474382959546809L;

    private final UUID id = UUID.randomUUID();
    private final String input;
    private final String pattern;
    private Status status;
    private int progress;
    private WordMatcherTaskResult result;

    public WordMatcherTask(String input, String pattern) {
        this.input = input;
        this.pattern = pattern;
        status = Status.INITIAL;
        progress = 0;
    }

    public UUID getId() {
        return id;
    }

    public String getInput() {
        return input;
    }

    public String getPattern() {
        return pattern;
    }

    public Status getTaskStatus() {
        return status;
    }

    public void setTaskStatus(Status status) {
        this.status = status;
    }

    public int getProgress() {
        return progress;
    }

    public void progress(int progress) {
        this.progress = Math.min(this.progress + progress, 100);
    }

    public WordMatcherTaskResult getResult() {
        return result;
    }

    public void setResult(WordMatcherTaskResult result) {
        this.result = result;
    }
}
