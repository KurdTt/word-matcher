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

package com.pksiazek.common.task.exception;

import java.io.Serial;

public class TaskNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -6636531449899477205L;

    public TaskNotFoundException() {
        super("Cannot find task with given ID");
    }
}
