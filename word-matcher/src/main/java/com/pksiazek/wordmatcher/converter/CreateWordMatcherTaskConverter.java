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

package com.pksiazek.wordmatcher.converter;

import com.pksiazek.common.task.converter.TaskRequestConverter;
import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.dto.register.CreateWordMatcherTaskRequest;
import org.springframework.stereotype.Service;

@Service
public class CreateWordMatcherTaskConverter
        implements TaskRequestConverter<CreateWordMatcherTaskRequest, WordMatcherTask> {

    @Override
    public WordMatcherTask convert(CreateWordMatcherTaskRequest request) {
        return new WordMatcherTask(request.input(), request.pattern());
    }

}