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

package pk.cdq.recruiting.task.process.dispatcher;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.process.event.word.CreateMatchWordPatternTaskEvent;

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
