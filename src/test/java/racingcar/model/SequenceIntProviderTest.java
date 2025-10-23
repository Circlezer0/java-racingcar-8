package racingcar.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SequenceIntProviderTest {

    @Test
    void 순차적_정수_제공_테스트() {
        // given
        int[] sequence = {3, 5, 2, 8, 1};
        SequenceIntProvider intProvider = new SequenceIntProvider(sequence);

        // when & then
        for (int expected : sequence) {
            int actual = intProvider.nextInt();
            assertEquals(expected, actual);
        }
    }

    @Test
    void 시퀀스_초과_호출시_IllegalArgumentException() {
        // given
        int[] sequence = {3, 5};
        SequenceIntProvider intProvider = new SequenceIntProvider(sequence);

        // when
        intProvider.nextInt();
        intProvider.nextInt();

        // then
        assertThrows(IllegalArgumentException.class, intProvider::nextInt);
    }

    @Test
    void 시퀀스_빈_값에서_호출시_IllegalArgumentException() {
        // given
        int[] sequence = {};
        SequenceIntProvider intProvider = new SequenceIntProvider(sequence);

        // then
        assertThrows(IllegalArgumentException.class, intProvider::nextInt);
    }

    @Test
    void 시퀀스_null_일_때_IllegalArgumentException() {
        // then
        assertThrows(NullPointerException.class, () -> {
            new SequenceIntProvider(null);
        });
    }
}
