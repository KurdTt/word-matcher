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

import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.converter.TaskRequestConverter;
import pk.cdq.recruiting.task.domain.Task;

@Service
public class CreateTaskConverter implements TaskRequestConverter<CreateTaskRequest> {

    @Override
    public Task convert(CreateTaskRequest request) {
        return new Task(request.input(), request.pattern());
    }

}