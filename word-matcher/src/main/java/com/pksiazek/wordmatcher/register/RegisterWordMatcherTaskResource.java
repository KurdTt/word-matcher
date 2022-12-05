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

import com.pksiazek.common.task.converter.TaskRequestConverter;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.dto.register.CreateWordMatcherTaskRequest;
import com.pksiazek.wordmatcher.dto.register.CreateWordMatcherTaskResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word/matcher/task/create")
public class RegisterWordMatcherTaskResource {

    private final TaskRequestConverter<CreateWordMatcherTaskRequest, WordMatcherTask> createTaskConverter;
    private final RegisterWordMatcherTaskController registerWordMatcherTaskController;

    public RegisterWordMatcherTaskResource(RegisterWordMatcherTaskController registerWordMatcherTaskController,
                                           TaskRequestConverter<CreateWordMatcherTaskRequest, WordMatcherTask> createTaskConverter) {
        this.registerWordMatcherTaskController = registerWordMatcherTaskController;
        this.createTaskConverter = createTaskConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CreateWordMatcherTaskResponse> createTask(
            @Valid @RequestBody CreateWordMatcherTaskRequest request) {
        WordMatcherTask wordMatcherTask = createTaskConverter.convert(request);
        registerWordMatcherTaskController.enqueueTask(wordMatcherTask);
        CreateWordMatcherTaskResponse response = new CreateWordMatcherTaskResponse(wordMatcherTask.getId());
        return ResponseEntity.ok().body(response);
    }


}
