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

package com.pksiazek.wordmatcher.info;

import com.pksiazek.wordmatcher.domain.WordMatcherTaskResult;
import com.pksiazek.wordmatcher.dto.info.GetWordMatcherTaskResultResponse;
import com.pksiazek.wordmatcher.dto.info.GetWordMatcherTaskStatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word/matcher/task/{taskId}")
@Tag(name = "Word Matcher Info API", description = "Word Matcher Info API")
public class InfoWordMatcherTaskResource {

    private final InfoWordMatcherTaskController infoWordMatcherTaskController;

    public InfoWordMatcherTaskResource(InfoWordMatcherTaskController infoWordMatcherTaskController) {
        this.infoWordMatcherTaskController = infoWordMatcherTaskController;
    }

    @GetMapping("/status")
    @Operation(summary = "Retrieve task status with progress")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Word matcher task status details",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetWordMatcherTaskStatusResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)})
    public ResponseEntity<GetWordMatcherTaskStatusResponse> getTaskStatus(@PathVariable UUID taskId) {
        String status = infoWordMatcherTaskController.getTaskStatus(taskId);
        int progress = infoWordMatcherTaskController.getTaskProgress(taskId);
        GetWordMatcherTaskStatusResponse response = new GetWordMatcherTaskStatusResponse(status, progress);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/result")
    @Operation(summary = "Retrieve task result")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Word matcher task result",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = GetWordMatcherTaskResultResponse.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)})
    public ResponseEntity<GetWordMatcherTaskResultResponse> getTaskResult(@PathVariable UUID taskId) {
        WordMatcherTaskResult wordMatcherTaskResult = infoWordMatcherTaskController.getTaskResult(taskId);
        GetWordMatcherTaskResultResponse response = new GetWordMatcherTaskResultResponse(
                wordMatcherTaskResult.position(), wordMatcherTaskResult.typos()
        );
        return ResponseEntity.ok().body(response);
    }
}
