package racingcar.model.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SequenceIntProviderTest {

    @Test
    @DisplayName("순차적 정수 제공 테스트")
    void sequentialIntProvisionTest() {
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
    @DisplayName("시퀀스 초과 호출시 IllegalArgumentException")
    void sequenceLimitExceededErrorTest() {
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
    @DisplayName("시퀀스 빈 값에서 호출시 IllegalArgumentException")
    void emptySequenceErrorTest() {
        // given
        int[] sequence = {};
        SequenceIntProvider intProvider = new SequenceIntProvider(sequence);

        // when & then
        assertThrows(IllegalArgumentException.class, intProvider::nextInt);
    }

    @Test
    @DisplayName("시퀀스 null 일 때 IllegalArgumentException")
    void nullSequenceErrorTest() {
        // when & then
        assertThrows(IllegalArgumentException.class, () -> {
            new SequenceIntProvider(null);
        });
    }
}
