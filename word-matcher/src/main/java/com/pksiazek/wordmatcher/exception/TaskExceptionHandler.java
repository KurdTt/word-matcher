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

package com.pksiazek.wordmatcher.exception;

import com.pksiazek.common.exception.ExceptionPayload;
import com.pksiazek.common.task.exception.TaskNotFoundException;
import com.pksiazek.common.task.exception.TaskResultNotFoundException;
import com.pksiazek.common.task.exception.TaskStatusNotFoundException;
import java.util.Optional;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("com.pksiazek.wordmatcher")
public class TaskExceptionHandler {

    @ExceptionHandler(
            {TaskNotFoundException.class, TaskResultNotFoundException.class, TaskStatusNotFoundException.class}
    )
    protected ResponseEntity<Object> handleException(RuntimeException exception) {
        ExceptionPayload payload = new ExceptionPayload(exception.getMessage());
        return ResponseEntity.badRequest().body(payload);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleArgumentMethodException(MethodArgumentNotValidException exception) {
        var message = Optional.of(exception)
                .map(BindException::getBindingResult)
                .map(Errors::getFieldErrors)
                .flatMap(fieldErrors -> fieldErrors.stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .reduce((error, error2) -> String.format("%s. %s", error, error2)))
                .orElse("");
        ExceptionPayload payload = new ExceptionPayload(message);
        return ResponseEntity.badRequest().body(payload);
    }
}
