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

package pk.cdq.recruiting.task.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pk.cdq.recruiting.common.exception.ExceptionPayload;

@ControllerAdvice("pk.cdq.recruiting.task")
public class TaskExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class, TaskResultNotFoundException.class, TaskStatusNotFoundException.class})
    protected ResponseEntity<Object> handleException(RuntimeException exception) {
        ExceptionPayload payload = new ExceptionPayload(exception.getMessage());
        return ResponseEntity.badRequest().body(payload);
    }
}
