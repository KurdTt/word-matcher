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

import com.pksiazek.common.task.dispatcher.TaskDispatcher;
import com.pksiazek.wordmatcher.event.CreateMatchWordPatternTaskEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class MatchWordTaskDispatcher implements TaskDispatcher<CreateMatchWordPatternTaskEvent> {

    private final ApplicationEventPublisher publisher;

    public MatchWordTaskDispatcher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void enqueue(CreateMatchWordPatternTaskEvent createMatchWordPatternTaskEvent) {
        publisher.publishEvent(createMatchWordPatternTaskEvent);
    }

}
