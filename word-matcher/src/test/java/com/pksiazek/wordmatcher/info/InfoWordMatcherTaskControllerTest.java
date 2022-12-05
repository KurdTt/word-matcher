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

import com.pksiazek.common.task.dispatcher.TaskPool;
import com.pksiazek.common.task.domain.Status;
import com.pksiazek.common.task.exception.TaskNotFoundException;
import com.pksiazek.common.task.exception.TaskResultNotFoundException;
import com.pksiazek.common.task.exception.TaskStatusNotFoundException;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.domain.WordMatcherTaskResult;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InfoWordMatcherTaskControllerTest {

    @Mock
    TaskPool<WordMatcherTask> taskPool;

    @Spy
    @InjectMocks
    InfoWordMatcherTaskController controller;

    @Test
    public void getTaskStatusFailedWhenGetTaskStatusIsNotFound() {
        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(TaskStatusNotFoundException.class, () -> controller.getTaskStatus(uuid));
    }

    @Test
    public void getTaskStatusSuccessfulReturnTaskStatusWhenTaskExists() {
        UUID uuid = UUID.randomUUID();
        Status expected = Status.INITIAL;

        WordMatcherTask task = Mockito.mock(WordMatcherTask.class);
        Mockito.doReturn(expected).when(task).getTaskStatus();
        Mockito.doReturn(task).when(taskPool).get(uuid);

        String result = controller.getTaskStatus(uuid);
        Assertions.assertEquals(expected.name(), result);
    }

    @Test
    public void getTaskProgressFailedWhenGetTaskProgressIsNotFound() {
        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(TaskNotFoundException.class, () -> controller.getTaskProgress(uuid));
    }

    @Test
    public void getTaskProgressSuccessfulReturnTaskProgressWhenTaskExists() {
        UUID uuid = UUID.randomUUID();
        int expected = 100;

        WordMatcherTask task = Mockito.mock(WordMatcherTask.class);
        Mockito.doReturn(expected).when(task).getProgress();
        Mockito.doReturn(task).when(taskPool).get(uuid);

        int result = controller.getTaskProgress(uuid);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getTaskResultFailedWhenGetTaskProgressIsNotFound() {
        UUID uuid = UUID.randomUUID();
        Assertions.assertThrows(TaskResultNotFoundException.class, () -> controller.getTaskResult(uuid));
    }

    @Test
    public void getTaskResultSuccessfulReturnTaskResultWhenTaskExists() {
        UUID uuid = UUID.randomUUID();
        int position = 0;
        int typos = 0;
        WordMatcherTaskResult expected = new WordMatcherTaskResult(position, typos);

        WordMatcherTask task = Mockito.mock(WordMatcherTask.class);
        Mockito.doReturn(expected).when(task).getResult();
        Mockito.doReturn(task).when(taskPool).get(uuid);

        WordMatcherTaskResult result = controller.getTaskResult(uuid);
        Assertions.assertEquals(result.position(), position);
        Assertions.assertEquals(result.typos(), typos);
    }
}
