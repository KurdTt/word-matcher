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

package pk.cdq.recruting.task.info;

import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/{taskId}")
public class InfoTaskResource {

    @GetMapping("/status")
    public ResponseEntity<Object> getTaskStatus(@PathVariable UUID taskId) {
        return ResponseEntity.ok().body(taskId);
    }

    @GetMapping("/result")
    public ResponseEntity<Object> getTaskResult(@PathVariable UUID taskId) {
        return ResponseEntity.ok().body(taskId);
    }
}
