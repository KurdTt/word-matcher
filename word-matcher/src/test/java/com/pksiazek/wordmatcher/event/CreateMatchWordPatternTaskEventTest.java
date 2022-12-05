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

package com.pksiazek.wordmatcher.event;

import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CreateMatchWordPatternTaskEventTest {

    @Test
    public void successfulCreateEventAndCheckTask() {
        WordMatcherTask task = Mockito.mock(WordMatcherTask.class);
        CreateMatchWordPatternTaskEvent event = CreateMatchWordPatternTaskEvent.of(task);
        Assertions.assertEquals(task, event.getTask());
    }

}
