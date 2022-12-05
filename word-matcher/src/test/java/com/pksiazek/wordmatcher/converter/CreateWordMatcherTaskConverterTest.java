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

import com.pksiazek.wordmatcher.domain.WordMatcherTask;
import com.pksiazek.wordmatcher.dto.register.CreateWordMatcherTaskRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CreateWordMatcherTaskConverterTest {

    @Spy
    CreateWordMatcherTaskConverter converter;

    @Test
    public void successfulConvertCreateWordMatcherRequestToWordMatcherTask() {
        String input = "ABCDEFG";
        String pattern = "ABC";
        CreateWordMatcherTaskRequest request = new CreateWordMatcherTaskRequest(input, pattern);
        WordMatcherTask task = converter.convert(request);
        Assertions.assertEquals(task.getInput(), request.input());
        Assertions.assertEquals(task.getPattern(), request.pattern());
    }

}
