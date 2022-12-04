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

import java.util.Arrays;
import org.springframework.stereotype.Service;
import pk.cdq.recruiting.task.domain.TaskResult;

@Service
public class WordPatternMatcherImpl implements WordPatternMatcher {

    private static final TaskResult EMPTY_RESULT = new TaskResult(-1, -1);

    @Override
    public TaskResult match(String input, String pattern) {
        if (areParametersProper(input, pattern)) {
            return EMPTY_RESULT;
        }
        return matchInputWithPattern(input, pattern);
    }

    boolean areParametersProper(String input, String pattern) {
        return input.length() < pattern.length();
    }

    TaskResult matchInputWithPattern(String input, String pattern) {
        int[] patternIndexes = computeCharIndexesFromPattern(input, pattern);
        int typos = getTypos(input, pattern, patternIndexes);
        int position = getStartIndex(patternIndexes);
        return new TaskResult(position, typos);
    }

    int[] computeCharIndexesFromPattern(String input, String pattern) {
        int[] patternIndexes = new int[pattern.length()];
        for (int i = 0; i < pattern.length(); i++) {
            int index = input.indexOf(pattern.charAt(i));
            patternIndexes[i] = index;
        }
        return patternIndexes;
    }

    int getStartIndex(int[] patternIndexes) {
        if (patternDoesNotMatch(patternIndexes)) {
            return -1;
        }
        if (patternIndexes.length == 1) {
            return patternIndexes[0];
        }
        if (areCharactersInSequenceOrEqual(patternIndexes, patternIndexes.length - 2)
                && !areItemsOutOfBound(patternIndexes[patternIndexes.length - 1],
                patternIndexes[patternIndexes.length - 2])) {
            return patternIndexes[patternIndexes.length - 1] - patternIndexes.length + 1;
        }
        if (patternIndexes[0] != -1) {
            return patternIndexes[0];
        }
        return -1;
    }

    boolean patternDoesNotMatch(int[] charIndexes) {
        int countAbsentItems = (int) Arrays.stream(charIndexes)
                .filter(item -> item == -1)
                .count();
        return countAbsentItems == charIndexes.length;
    }

    boolean areCharactersInSequenceOrEqual(int[] charIndexes, int firstCharacterIndex) {
        int previousCharIndex = charIndexes[firstCharacterIndex];
        int nextCharIndex = charIndexes[firstCharacterIndex + 1];
        return nextCharIndex - previousCharIndex == 1 // In Sequence
                || nextCharIndex - previousCharIndex == 0; // Equal
    }

    int getTypos(String input, String pattern, int[] charIndexes) {
        int typos = 0;
        typos += getTyposByIndexDifference(charIndexes);
        typos += getTyposByCharacterDifference(input, pattern, charIndexes);
        return typos;
    }

    int getTyposByIndexDifference(int[] charIndexes) {
        int typos = 0;
        for (int i = charIndexes.length - 1; i > 0; i--) {
            int currentItem = charIndexes[i];
            int nextItem = charIndexes[i - 1];
            if (currentItem - nextItem != 1 && !areItemsOutOfBound(currentItem, nextItem)) {
                typos += 1;
            }
        }
        return typos;
    }

    int getTyposByCharacterDifference(String input, String pattern, int[] charIndexes) {
        int typos = 0;
        for (int i = 0; i < charIndexes.length; i++) {
            int charIndex = charIndexes[i];
            if (charIndex == -1 || pattern.charAt(i) != input.charAt(charIndex)) {
                typos += 1;
            }
        }
        return typos;
    }

    boolean areItemsOutOfBound(int... items) {
        return Arrays.stream(items).anyMatch(value -> value == -1);
    }
}
