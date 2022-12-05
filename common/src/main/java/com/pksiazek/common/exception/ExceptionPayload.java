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

package com.pksiazek.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ExceptionPayload(@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss.SSS") LocalDateTime timestamp,
                               String message) {

    public ExceptionPayload(String message) {
        this(LocalDateTime.now(), message);
    }
}
