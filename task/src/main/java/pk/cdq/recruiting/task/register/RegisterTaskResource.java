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

package pk.cdq.recruiting.task.register;

import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pk.cdq.recruiting.task.converter.TaskRequestConverter;
import pk.cdq.recruiting.task.domain.Task;

@Validated
@RestController
@RequestMapping("/task/create")
public class RegisterTaskResource {

    private final TaskRequestConverter<CreateTaskRequest> createTaskConverter;
    private final RegisterTaskController registerTaskController;

    public RegisterTaskResource(RegisterTaskController registerTaskController,
                                TaskRequestConverter<CreateTaskRequest> createTaskConverter) {
        this.registerTaskController = registerTaskController;
        this.createTaskConverter = createTaskConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody @Valid CreateTaskRequest createTaskRequest) {
        Task task = createTaskConverter.convert(createTaskRequest);
        registerTaskController.enqueueTask(task);
        CreateTaskResponse response = new CreateTaskResponse(task.getId());
        return ResponseEntity.ok().body(response);
    }


}
