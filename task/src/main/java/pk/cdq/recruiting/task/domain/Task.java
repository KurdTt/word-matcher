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

package pk.cdq.recruiting.task.domain;

import java.util.UUID;

public class Task {

    private final UUID id;
    private final String input;
    private final String pattern;

    public Task(String input, String pattern) {
        id = UUID.randomUUID();
        this.input = input;
        this.pattern = pattern;
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
}
