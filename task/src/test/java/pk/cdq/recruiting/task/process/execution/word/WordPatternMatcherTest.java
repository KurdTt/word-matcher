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

package pk.cdq.recruiting.task.process.execution.word;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import pk.cdq.recruiting.task.domain.TaskResult;

@ExtendWith(MockitoExtension.class)
public class WordPatternMatcherTest {

    @Spy
    WordPatternMatcherImpl matcher;

    @Test
    public void firstCase() {
        TaskResult result = matcher.match("ABCD", "BCD");
        Assertions.assertEquals(result.typos(), 0);
        Assertions.assertEquals(result.position(), 1);
    }

    @Test
    public void secondCase() {
        TaskResult result = matcher.match("ABCD", "BWD");
        Assertions.assertEquals(result.typos(), 1);
        Assertions.assertEquals(result.position(), 1);
    }

    @Test
    public void thirdCase() {
        TaskResult result = matcher.match("ABCDEFG", "CFG");
        Assertions.assertEquals(result.typos(), 1);
        Assertions.assertEquals(result.position(), 4);
    }

    @Test
    public void fourthCase() {
        TaskResult result = matcher.match("ABCABC", "ABC");
        Assertions.assertEquals(result.typos(), 0);
        Assertions.assertEquals(result.position(), 0);
    }

    @Test
    public void fifthCase() {
        TaskResult result = matcher.match("ABCDEFG", "TDD");
        Assertions.assertEquals(result.typos(), 2);
        Assertions.assertEquals(result.position(), 1);
    }

    @Test
    public void invalidCase() {
        TaskResult result = matcher.match("ABCDEFGH", "ABCDEFGHIJK");
        Assertions.assertEquals(result.typos(), -1);
        Assertions.assertEquals(result.position(), -1);
    }

    @Test
    public void oneLetterCase() {
        TaskResult result = matcher.match("ABCDEFGH", "C");
        Assertions.assertEquals(result.typos(), 0);
        Assertions.assertEquals(result.position(), 2);
    }
}
