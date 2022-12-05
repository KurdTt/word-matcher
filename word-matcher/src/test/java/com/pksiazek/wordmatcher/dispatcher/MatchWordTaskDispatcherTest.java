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

package com.pksiazek.wordmatcher.dispatcher;

import com.pksiazek.wordmatcher.event.CreateMatchWordPatternTaskEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
public class MatchWordTaskDispatcherTest {

    @Mock
    ApplicationEventPublisher publisher;

    @Spy
    @InjectMocks
    MatchWordTaskDispatcher dispatcher;

    @Test
    public void successfulEnqueueCreateMatchWordPatternAndPublishEvent() {
        CreateMatchWordPatternTaskEvent event = Mockito.mock(CreateMatchWordPatternTaskEvent.class);
        dispatcher.enqueue(event);
        Mockito.verify(publisher).publishEvent(event);
    }
}
